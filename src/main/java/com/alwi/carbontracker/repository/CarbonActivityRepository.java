package com.alwi.carbontracker.repository;

import com.alwi.carbontracker.dto.projection.ActivityWithResultProjection;
import com.alwi.carbontracker.model.CarbonActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CarbonActivityRepository extends JpaRepository<CarbonActivity, UUID> {

    @Query(
            value = """
                    SELECT
                        ca.id              AS carbonActivityId,
                        ca.activity_type   AS activityType,
                        ca.amount          AS amount,
                        ca.activity_time   AS activityTime,
                        ca.created_by      AS createdBy,
                        ca.created_at      AS createdAt,
                    
                        cer.emission_kg        AS emissionKg,
                        cer.calculated_at      AS calculatedAt,
                        cer.calculation_status AS calculationStatus
                    FROM carbon_tracker.carbon_activity ca
                    LEFT JOIN carbon_tracker.carbon_emission_result cer
                        ON cer.carbon_activity_id = ca.id
                    WHERE
                    
                        (CAST(:from AS timestamp) IS NULL OR ca.activity_time >= :from)
                        AND (CAST(:to AS timestamp) IS NULL OR ca.activity_time <= :to)
                    
                    
                        AND (CAST(:activityType AS text) IS NULL OR ca.activity_type = :activityType)
                    
                    
                        AND (CAST(:status AS text) IS NULL OR cer.calculation_status = :status)
                    """,
            nativeQuery = true
    )
    List<ActivityWithResultProjection> findActivitiesWithResult(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            @Param("activityType") String activityType,
            @Param("status") String status
    );


}
