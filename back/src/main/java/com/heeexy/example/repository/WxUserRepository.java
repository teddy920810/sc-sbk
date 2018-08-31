package com.heeexy.example.repository;

import com.heeexy.example.entity.WxUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WxUserRepository extends JpaRepository<WxUser,Long>{

    WxUser findByOpenid(String openid);

    Page<WxUser> findAll(Pageable pageable);
}
