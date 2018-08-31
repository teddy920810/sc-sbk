package com.heeexy.example.repository;

import com.heeexy.example.entity.TrainingCheckLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingCheckLogRepository extends JpaRepository<TrainingCheckLog, Long> {

    Page<TrainingCheckLog> findByTrainingCheck_Id(Long trainingCheckId, Pageable pageable);
}
