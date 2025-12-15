package com.alwi.carbontracker.controller;

import com.alwi.carbontracker.dto.CarbonActivityResultDTO;
import com.alwi.carbontracker.dto.request.ActivityFilterRequestDTO;
import com.alwi.carbontracker.dto.request.ActivityRequestDTO;
import com.alwi.carbontracker.dto.request.DateDTO;
import com.alwi.carbontracker.dto.response.SummaryResponseDTO;
import com.alwi.carbontracker.exception.ValidationException;
import com.alwi.carbontracker.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("carbon/core")
public class CarbonActivityController {

    private final ActivityService activityService;


    public CarbonActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/input")
    public ResponseEntity<Object> inputData(@RequestBody ActivityRequestDTO requestBody) throws ValidationException {
        String response = activityService.saveCarbonActivity(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/summary")
    public ResponseEntity<Object> getSummary(@RequestBody DateDTO requestBody) throws ValidationException {
        SummaryResponseDTO response = activityService.fetchDailySummary(requestBody);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/all-activities")
    public ResponseEntity<Object> getAllActivities(@RequestBody ActivityFilterRequestDTO requestBody) throws ValidationException {
        List<CarbonActivityResultDTO> result = activityService.getAllActivities(requestBody);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
