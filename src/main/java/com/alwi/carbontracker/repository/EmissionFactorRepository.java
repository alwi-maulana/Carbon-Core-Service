package com.alwi.carbontracker.repository;

import com.alwi.carbontracker.model.EmissionFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmissionFactorRepository extends JpaRepository<EmissionFactor, String> {

    Optional<EmissionFactor> findByActivityType(String activityType);

    @Query("SELECT e.activityType FROM EmissionFactor e")
    List<String> findAllActivityTypes();

    @Query("SELECT e.activityType, e.unit, e.factorKg, e.effectiveFrom FROM EmissionFactor e")
    List<Object[]> findAllWithoutIdRaw();

}
