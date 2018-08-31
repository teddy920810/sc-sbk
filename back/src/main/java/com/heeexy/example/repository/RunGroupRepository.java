package com.heeexy.example.repository;

import com.heeexy.example.entity.RunGroup;
import com.heeexy.example.entity.RunningData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RunGroupRepository extends JpaRepository<RunGroup,Long> {

}
