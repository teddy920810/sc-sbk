package com.heeexy.example.repository;

import com.heeexy.example.entity.TrainingCheck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingCheckRepository extends JpaRepository<TrainingCheck,Long> {

    Page<TrainingCheck> findAll(Pageable pageable);
}
