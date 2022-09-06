package com.example.leave.infrastructure.db.jpa.repository;

import com.example.leave.infrastructure.db.entity.UserCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCountRepository extends JpaRepository<UserCount,Integer> {

}
