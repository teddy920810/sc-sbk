package com.heeexy.example.repository;

import com.heeexy.example.entity.RunningData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RunningRepository extends JpaRepository<RunningData,Long> {

    Page<RunningData> findByWxUser_IdAndRunGroup_Id(Long wxUserId,Long runGroupId, Pageable pageable);

    List<RunningData> findByRunGroup_Id(Long groupId);

    List<RunningData> findByWxUser_IdAndRunGroup_Id(Long wxUserId,Long runGroupId);

    List<RunningData> findByWxUser_IdAndRunGroup_IdAndStatus(Long wxUserId,Long runGroupId,Integer status);

    List<RunningData> findTop4ByWxUser_IdAndRunGroup_IdAndStatusOrderByRunningDateTimeDesc(Long wxUserId,Long runGroupId,Integer status);

}
