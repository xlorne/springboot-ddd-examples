package com.example.leave.infrastructure.db.jpa.repository;

import com.example.leave.infrastructure.db.entity.LeaveCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveCommentEntityRepository extends JpaRepository<LeaveCommentEntity,Integer> {

    List<LeaveCommentEntity> findByLeaveId(long leaveId);
}
