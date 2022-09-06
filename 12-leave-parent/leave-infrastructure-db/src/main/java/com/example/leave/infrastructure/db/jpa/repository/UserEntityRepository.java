package com.example.leave.infrastructure.db.jpa.repository;

import com.example.leave.infrastructure.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {

    UserEntity getByUsername(String username);

}
