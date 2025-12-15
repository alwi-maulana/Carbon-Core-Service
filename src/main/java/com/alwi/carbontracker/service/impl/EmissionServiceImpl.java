package com.alwi.carbontracker.service.impl;

import com.alwi.carbontracker.dto.EmissionFactorDTO;
import com.alwi.carbontracker.dto.request.EmissionResultDTO;
import com.alwi.carbontracker.exception.ValidationException;
import com.alwi.carbontracker.model.CarbonActivity;
import com.alwi.carbontracker.model.CarbonEmissionResult;
import com.alwi.carbontracker.model.EmissionFactor;
import com.alwi.carbontracker.repository.CarbonActivityRepository;
import com.alwi.carbontracker.repository.CarbonEmissionResultRepository;
import com.alwi.carbontracker.repository.EmissionFactorRepository;
import com.alwi.carbontracker.service.EmissionService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.alwi.carbontracker.constant.CommonConstant.CalculationType.EVENT;
import static com.alwi.carbontracker.constant.CommonConstant.Default.SUCCESS_SAVE_DATA;
import static com.alwi.carbontracker.constant.CommonConstant.ErrorMessage.ACTIVITY_IS_NOT_FOUND;
import static com.alwi.carbontracker.constant.CommonConstant.ErrorMessage.ACTIVITY_TYPE_IS_NOT_FOUND;
import static com.alwi.carbontracker.constant.CommonConstant.ErrorMessage.FAILED_GET_EMISSION_INFO;
import static com.alwi.carbontracker.constant.CommonConstant.ErrorMessage.FAILED_SAVE_EMISSION_RESULT;
import static com.alwi.carbontracker.constant.CommonConstant.ErrorMessage.FAILED_TO_GET_ALL_EMISSION_FACTOR;

@Service
public class EmissionServiceImpl implements EmissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmissionServiceImpl.class);

    private final EmissionFactorRepository emissionFactorRepository;

    private final CarbonActivityRepository carbonActivityRepository;

    private final CarbonEmissionResultRepository carbonEmissionResultRepository;

    public EmissionServiceImpl(EmissionFactorRepository emissionFactorRepository, CarbonActivityRepository carbonActivityRepository, CarbonEmissionResultRepository carbonEmissionResultRepository) {
        this.emissionFactorRepository = emissionFactorRepository;
        this.carbonActivityRepository = carbonActivityRepository;
        this.carbonEmissionResultRepository = carbonEmissionResultRepository;
    }

    @Transactional
    public EmissionFactorDTO getEmission(String activityType) throws ValidationException {
        try {
            EmissionFactor factor = emissionFactorRepository.findByActivityType(activityType)
                    .orElseThrow(() -> new ValidationException(ACTIVITY_TYPE_IS_NOT_FOUND));

            return new EmissionFactorDTO(
                    factor.getActivityType(),
                    factor.getUnit(),
                    factor.getFactorKg(),
                    String.valueOf(factor.getEffectiveFrom())
            );
        } catch (DataAccessException e) {
            LOGGER.error(FAILED_GET_EMISSION_INFO, e);
            throw new ValidationException(FAILED_GET_EMISSION_INFO);
        }
    }

    @Transactional
    @Override
    public String saveEmissionResult(EmissionResultDTO requestBody) throws ValidationException {
        try {
            CarbonEmissionResult carbonEmissionResult = new CarbonEmissionResult();

            if (EVENT.equals(requestBody.getCalculationType())) {
                UUID activityId;
                try {
                    activityId = UUID.fromString(requestBody.getCarbonActivityId());
                } catch (IllegalArgumentException ex) {
                    throw new ValidationException("Invalid CarbonActivity ID format");
                }

                CarbonActivity activity = carbonActivityRepository.findById(activityId)
                        .orElseThrow(() -> new ValidationException(ACTIVITY_IS_NOT_FOUND));
                carbonEmissionResult.setCarbonActivity(activity);
            }

            carbonEmissionResult.setEmissionKg(requestBody.getEmissionKg());
            carbonEmissionResult.setCalculatedAt(requestBody.getCalculatedAt());
            carbonEmissionResult.setCalculationStatus(requestBody.getCalculationStatus());

            carbonEmissionResultRepository.save(carbonEmissionResult);
        } catch (DataAccessException e) {
            LOGGER.error("Failed save emission result to Database", e);
            throw new ValidationException(FAILED_SAVE_EMISSION_RESULT);
        }

        return SUCCESS_SAVE_DATA;
    }

    @Override
    public List<String> getAllActivityType() throws ValidationException {
        try {
            return emissionFactorRepository.findAllActivityTypes();
        } catch (DataAccessException e) {
            LOGGER.error("Failed save carbon activity to Database", e);
            throw new ValidationException(ACTIVITY_TYPE_IS_NOT_FOUND);
        }
    }

    @Override
    public List<EmissionFactor> getAllEmission() throws ValidationException {
        try {
            return emissionFactorRepository.findAll();
        } catch (DataAccessException e) {
            LOGGER.error(FAILED_TO_GET_ALL_EMISSION_FACTOR, e);
            throw new ValidationException(FAILED_TO_GET_ALL_EMISSION_FACTOR);
        }
    }

}
