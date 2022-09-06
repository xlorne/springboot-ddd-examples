package com.example.leave.infrastructure.db.jpa.repository;

import com.example.leave.infrastructure.db.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentEntityRepository extends JpaRepository<CommentEntity,Integer> {

}
