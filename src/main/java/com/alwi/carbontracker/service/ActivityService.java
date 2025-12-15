package com.alwi.carbontracker.service;

import com.alwi.carbontracker.dto.CarbonActivityResultDTO;
import com.alwi.carbontracker.dto.request.ActivityFilterRequestDTO;
import com.alwi.carbontracker.dto.request.ActivityRequestDTO;
import com.alwi.carbontracker.dto.request.DateDTO;
import com.alwi.carbontracker.dto.response.SummaryResponseDTO;
import com.alwi.carbontracker.exception.ValidationException;

import java.util.List;

public interface ActivityService {

    String saveCarbonActivity(ActivityRequestDTO activityRequestDTO) throws ValidationException;

    SummaryResponseDTO fetchDailySummary(DateDTO date) throws ValidationException;

    List<CarbonActivityResultDTO> getAllActivities(ActivityFilterRequestDTO requestBody) throws ValidationException;
}
