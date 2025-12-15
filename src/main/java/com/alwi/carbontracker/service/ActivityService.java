package com.alwi.carbontracker.service;

import com.alwi.carbontracker.dto.request.ActivityRequestDTO;
import com.alwi.carbontracker.dto.request.DateDTO;
import com.alwi.carbontracker.dto.response.DailyEmissionSummaryDTO;
import com.alwi.carbontracker.exception.ValidationException;

import java.util.List;

public interface ActivityService {

    String saveCarbonActivity(ActivityRequestDTO activityRequestDTO) throws ValidationException;

    List<DailyEmissionSummaryDTO> fetchDailySummary(DateDTO date) throws ValidationException;
}
