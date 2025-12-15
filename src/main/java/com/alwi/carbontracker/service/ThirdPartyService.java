package com.alwi.carbontracker.service;

import com.alwi.carbontracker.dto.request.EmissionResultDTO;
import com.alwi.carbontracker.dto.request.EventCalculateEmissionDTO;
import com.alwi.carbontracker.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ThirdPartyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPartyService.class);
    private final RestTemplate restTemplate;

    @Value("${emission.calculation.api.endpoint}")
    private String emissionCalculationUrl;

    public ThirdPartyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EmissionResultDTO calculateEventEmission(EventCalculateEmissionDTO requestBody) throws ValidationException {
        try {
            LOGGER.info("Send Event Calculate URL: {}", emissionCalculationUrl);

            System.out.println("request body " + requestBody.toString());

            ResponseEntity<EmissionResultDTO> response = restTemplate.postForEntity(
                    emissionCalculationUrl,
                    requestBody,
                    EmissionResultDTO.class
            );

            System.out.println("response: " + response);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed to post weather request: " + response.getStatusCode());
            }
        } catch (Exception e) {
            LOGGER.error("Failed send data calculation {}", e);
            throw new ValidationException("FAILED SEND DATA TO THIRD PARTY");
        }

    }
}
