package com.example.leave.infrastructure.db.jpa.repository;

import com.example.leave.infrastructure.db.entity.LeaveOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveOrderEntityRepository extends JpaRepository<LeaveOrderEntity,Long> {

    Page<LeaveOrderEntity> findByCreateUserId(long id, PageRequest pageRequest);
}
