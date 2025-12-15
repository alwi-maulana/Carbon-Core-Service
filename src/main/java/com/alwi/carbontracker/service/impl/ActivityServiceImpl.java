package com.alwi.carbontracker.service.impl;

import com.alwi.carbontracker.dto.CarbonActivityResultDTO;
import com.alwi.carbontracker.dto.projection.ActivityWithResultProjection;
import com.alwi.carbontracker.dto.projection.DailyEmissionSummaryProjection;
import com.alwi.carbontracker.dto.request.ActivityFilterRequestDTO;
import com.alwi.carbontracker.dto.request.ActivityRequestDTO;
import com.alwi.carbontracker.dto.request.DateDTO;
import com.alwi.carbontracker.dto.request.EmissionResultDTO;
import com.alwi.carbontracker.dto.request.EventCalculateEmissionDTO;
import com.alwi.carbontracker.dto.response.DailyEmissionSummaryDTO;
import com.alwi.carbontracker.dto.response.SummaryResponseDTO;
import com.alwi.carbontracker.exception.ValidationException;
import com.alwi.carbontracker.model.CarbonActivity;
import com.alwi.carbontracker.model.EmissionFactor;
import com.alwi.carbontracker.repository.CarbonActivityRepository;
import com.alwi.carbontracker.repository.CarbonEmissionResultRepository;
import com.alwi.carbontracker.repository.EmissionFactorRepository;
import com.alwi.carbontracker.service.ActivityService;
import com.alwi.carbontracker.service.EmissionService;
import com.alwi.carbontracker.service.ThirdPartyService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.alwi.carbontracker.constant.CommonConstant.Default.CREATED_BY;
import static com.alwi.carbontracker.constant.CommonConstant.Default.SUCCESS_SAVE_DATA;
import static com.alwi.carbontracker.constant.CommonConstant.Default.SUCCESS_SAVE_DATA_ACTIVITY_AND_EMISSION_CALCULATION;
import static com.alwi.carbontracker.constant.CommonConstant.ErrorMessage.ACTIVITY_TYPE_IS_NOT_FOUND;
import static com.alwi.carbontracker.constant.CommonConstant.ErrorMessage.FAILED_GET_ALL_SUMMARY;
import static com.alwi.carbontracker.constant.CommonConstant.ErrorMessage.FAILED_GET_DAILY_SUMMARY;
import static com.alwi.carbontracker.constant.CommonConstant.ErrorMessage.FAILED_SAVE_CARBON_ACTIVITY;

@Service
public class ActivityServiceImpl implements ActivityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityServiceImpl.class);

    private final CarbonActivityRepository carbonActivityRepository;
    private final EmissionFactorRepository emissionFactorRepository;
    private final ThirdPartyService thirdPartyService;
    private final EmissionService emissionService;
    private final CarbonEmissionResultRepository carbonEmissionResultRepository;

    public ActivityServiceImpl(CarbonActivityRepository carbonActivityRepository, EmissionFactorRepository emissionFactorRepository, ThirdPartyService thirdPartyService, EmissionService emissionService, CarbonEmissionResultRepository carbonEmissionResultRepository) {
        this.carbonActivityRepository = carbonActivityRepository;
        this.emissionFactorRepository = emissionFactorRepository;
        this.thirdPartyService = thirdPartyService;
        this.emissionService = emissionService;
        this.carbonEmissionResultRepository = carbonEmissionResultRepository;
    }

    @Transactional
    @Override
    public String saveCarbonActivity(ActivityRequestDTO activityRequestDTO) throws ValidationException {

        try {
            EmissionFactor factor = emissionFactorRepository.findByActivityType(activityRequestDTO.getActivityType()).
                    orElseThrow(() -> new ValidationException(ACTIVITY_TYPE_IS_NOT_FOUND));

            CarbonActivity carbonActivity = new CarbonActivity();
            carbonActivity.setActivityType(activityRequestDTO.getActivityType());
            carbonActivity.setAmount(activityRequestDTO.getAmount());
            carbonActivity.setActivityTime(activityRequestDTO.getTimestamp());
            carbonActivity.setCreatedBy(CREATED_BY);

            CarbonActivity saved = carbonActivityRepository.save(carbonActivity);

            EventCalculateEmissionDTO emissionRawData = new EventCalculateEmissionDTO();
            emissionRawData.setCarbonActivityId(saved.getCarbonActivityId());
            emissionRawData.setActivityType(saved.getActivityType());
            emissionRawData.setAmount(saved.getAmount());
            emissionRawData.setUnit(factor.getUnit());
            emissionRawData.setFactorKg(factor.getFactorKg());
            emissionRawData.setTimestamp(saved.getActivityTime());

            EmissionResultDTO calculateResult = thirdPartyService.calculateEventEmission(emissionRawData);

            if (calculateResult != null) {
                String saveEmissionResult = emissionService.saveEmissionResult(calculateResult);

                if (SUCCESS_SAVE_DATA.equals(saveEmissionResult)) {
                    return SUCCESS_SAVE_DATA_ACTIVITY_AND_EMISSION_CALCULATION;
                }
            }

            return SUCCESS_SAVE_DATA;
        } catch (DataAccessException e) {
            LOGGER.error("Failed save carbon activity to Database", e);
            throw new ValidationException(FAILED_SAVE_CARBON_ACTIVITY);
        }
    }

    @Transactional
    @Override
    public SummaryResponseDTO fetchDailySummary(DateDTO date) throws ValidationException {
        try {
            List<DailyEmissionSummaryProjection> projections = carbonEmissionResultRepository.getDailySummary(date.getDate());

            List<DailyEmissionSummaryDTO> summaries =
                    projections.stream()
                            .map(p -> {
                                DailyEmissionSummaryDTO dto = new DailyEmissionSummaryDTO();
                                dto.setActivityType(p.getActivityType());
                                dto.setTotalEmissionKg(p.getTotalEmissionKg());
                                dto.setTotalEvent(p.getTotalEvent());
                                dto.setSuccessCount(p.getSuccessCount());
                                dto.setFailedCount(p.getFailedCount());
                                return dto;
                            })
                            .toList();

            BigDecimal totalEmission =
                    projections.stream()
                            .map(DailyEmissionSummaryProjection::getTotalEmissionKg)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

            Long totalEvent =
                    projections.stream()
                            .map(DailyEmissionSummaryProjection::getTotalEvent)
                            .reduce(0L, Long::sum);
            Long totalSuccess =
                    projections.stream()
                            .map(DailyEmissionSummaryProjection::getSuccessCount)
                            .reduce(0L, Long::sum);

            Long totalFailed =
                    projections.stream()
                            .map(DailyEmissionSummaryProjection::getFailedCount)
                            .reduce(0L, Long::sum);


            return new SummaryResponseDTO(
                    totalEmission,
                    totalEvent,
                    totalSuccess,
                    totalFailed,
                    date,
                    summaries
            );


        } catch (DataAccessException e) {
            LOGGER.error("Failed fetch summary carbon activity from Database", e);
            throw new ValidationException(FAILED_GET_DAILY_SUMMARY);
        }
    }

    @Transactional
    @Override
    public List<CarbonActivityResultDTO> getAllActivities(ActivityFilterRequestDTO requestBody) throws ValidationException {
        try {

            LocalDateTime from = requestBody.getActivityFrom() != null
                    ? requestBody.getActivityFrom().atStartOfDay()
                    : LocalDateTime.of(1970, 1, 1, 0, 0);
            LocalDateTime to = requestBody.getActivityTo() != null
                    ? requestBody.getActivityTo().atTime(23, 59, 59)
                    : LocalDateTime.now();


            List<ActivityWithResultProjection> projections = carbonActivityRepository.findActivitiesWithResult(from, to, requestBody.getActivityType(), requestBody.getStatus().toUpperCase());

            System.out.println("projections " + projections.toString());
            return projections.stream()
                    .map(p -> {
                        CarbonActivityResultDTO dto = new CarbonActivityResultDTO();
                        dto.setCarbonActivityId(p.getCarbonActivityId());
                        dto.setActivityType(p.getActivityType());
                        dto.setAmount(p.getAmount());
                        dto.setActivityTime(p.getActivityTime());
                        dto.setCreatedBy(p.getCreatedBy());
                        dto.setCreatedAt(p.getCreatedAt());

                        if (p.getEmissionKg() != null) {
                            EmissionResultDTO result = new EmissionResultDTO();
                            result.setEmissionKg(p.getEmissionKg());
                            result.setCalculatedAt(p.getCalculatedAt());
                            result.setCalculationStatus(p.getCalculationStatus());
                            dto.setResult(result);
                        }

                        return dto;
                    })
                    .toList();
        } catch (DataAccessException e) {
            LOGGER.error("Failed fetch all carbon activity from Database", e);
            throw new ValidationException(FAILED_GET_ALL_SUMMARY);
        }
    }
}
