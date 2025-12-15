package com.alwi.carbontracker.repository;

import com.alwi.carbontracker.model.CarbonActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarbonActivityRepository extends JpaRepository<CarbonActivity, UUID> {

}
