package com.example.leave.domain.repository;

import com.example.leave.domain.entity.LeaveOrder;

public interface LeaveOrderRepository {

    void save(LeaveOrder leaveOrder);

    LeaveOrder getById(long leaveId);

}
