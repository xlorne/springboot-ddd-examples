package com.example.leave.infrastructure.db.repository.impl;

import com.example.leave.domain.entity.Comment;
import com.example.leave.domain.entity.LeaveOrder;
import com.example.leave.domain.repository.LeaveOrderRepository;
import com.example.leave.infrastructure.db.entity.CommentEntity;
import com.example.leave.infrastructure.db.entity.LeaveOrderEntity;
import com.example.leave.infrastructure.db.jpa.repository.CommentEntityRepository;
import com.example.leave.infrastructure.db.jpa.repository.LeaveOrderEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class LeaveOrderRepositoryImpl implements LeaveOrderRepository {

    private final LeaveOrderEntityRepository leaveOrderEntityRepository;
    private final CommentEntityRepository commentEntityRepository;

    @Override
    public void save(LeaveOrder leaveOrder) {
        LeaveOrderEntity entity = new LeaveOrderEntity();
        entity.setId(leaveOrder.getId());
        entity.setContent(leaveOrder.getContent());
        entity.setCreateTime(leaveOrder.getCreateTime());
        entity.setCreateUserId(leaveOrder.getCreateUser().getId());
        entity.setApprovalUserId(leaveOrder.getApprovalUser().getId());
        entity.setState(leaveOrder.getState().getCode());

        leaveOrderEntityRepository.save(entity);

        List<CommentEntity> commentEntityList = new ArrayList<>();
        for(Comment comment:leaveOrder.getComments()){
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setId(comment.getCreateTime().getTime());
            commentEntity.setLeaveId(entity.getId());
            commentEntity.setFlag(comment.isFlag());
            commentEntity.setUserId(comment.getUser().getId());
            commentEntity.setCreateTime(comment.getCreateTime());
            commentEntity.setContent(comment.getContent());

            commentEntityList.add(commentEntity);
        }

        commentEntityRepository.saveAll(commentEntityList);
    }
}
