package com.alwi.carbontracker.controller;

import com.alwi.carbontracker.dto.EmissionFactorDTO;
import com.alwi.carbontracker.dto.request.EmissionResultDTO;
import com.alwi.carbontracker.exception.ValidationException;
import com.alwi.carbontracker.model.EmissionFactor;
import com.alwi.carbontracker.service.EmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carbon/core/emission")
public class EmissionController {

    private final EmissionService emissionService;

    public EmissionController(EmissionService emissionService) {
        this.emissionService = emissionService;
    }

    @PostMapping("/save-result")
    public ResponseEntity<Object> saveEmissionResult(@RequestBody EmissionResultDTO requestBody) throws ValidationException {
        String response = emissionService.saveEmissionResult(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/getByActivity")
    public ResponseEntity<Object> getEmission(@RequestBody EmissionFactorDTO activityType) throws ValidationException {
        EmissionFactorDTO response = emissionService.getEmission(activityType.getActivityType());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/activity-list")
    public ResponseEntity<Object> getActivityList() throws ValidationException {
        List<String> response = emissionService.getAllActivityType();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllEMission() throws ValidationException {
        List<EmissionFactor> response = emissionService.getAllEmission();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
