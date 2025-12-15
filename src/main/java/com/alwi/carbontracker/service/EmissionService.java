package com.alwi.carbontracker.service;

import com.alwi.carbontracker.dto.EmissionFactorDTO;
import com.alwi.carbontracker.dto.request.EmissionResultDTO;
import com.alwi.carbontracker.exception.ValidationException;
import com.alwi.carbontracker.model.EmissionFactor;

import java.util.List;

public interface EmissionService {

    EmissionFactorDTO getEmission(String activityType) throws ValidationException;

    String saveEmissionResult(EmissionResultDTO requestBody) throws ValidationException;

    List<String> getAllActivityType() throws ValidationException;

    List<EmissionFactor> getAllEmission() throws ValidationException;
}
