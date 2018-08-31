package com.heeexy.example.repository;

import com.heeexy.example.entity.UserRunLogForGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRunLogForGroupRepository extends JpaRepository<UserRunLogForGroup,Long> {

    UserRunLogForGroup findByWxUser_Id(Long uid);

    UserRunLogForGroup findByWxUser_IdAndRunGroup_Id(Long wxUserId,Long runGroupId);

    List<UserRunLogForGroup> findByRunGroup_Id(Long runGroupId);

    Page<UserRunLogForGroup> findByRunGroup_Id(Long runGroupId, Pageable pageable);
}
