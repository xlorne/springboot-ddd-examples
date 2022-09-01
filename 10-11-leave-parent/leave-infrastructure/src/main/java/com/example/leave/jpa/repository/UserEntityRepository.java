package com.example.leave.jpa.repository;

import com.example.leave.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {


}
