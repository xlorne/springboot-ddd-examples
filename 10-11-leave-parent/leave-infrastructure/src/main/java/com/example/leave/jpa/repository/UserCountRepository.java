package com.example.leave.jpa.repository;

import com.example.leave.dao.UserCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCountRepository extends JpaRepository<UserCount,Integer> {

}
