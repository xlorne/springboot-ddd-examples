package com.example.leave.infrastructure.db.repository.impl;

import com.example.leave.domain.entity.Comment;
import com.example.leave.domain.entity.LeaveOrder;
import com.example.leave.domain.entity.User;
import com.example.leave.domain.repository.LeaveOrderRepository;
import com.example.leave.infrastructure.db.entity.LeaveCommentEntity;
import com.example.leave.infrastructure.db.entity.LeaveOrderEntity;
import com.example.leave.infrastructure.db.jpa.repository.LeaveCommentEntityRepository;
import com.example.leave.infrastructure.db.jpa.repository.LeaveOrderEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class LeaveOrderRepositoryImpl implements LeaveOrderRepository {

    private final LeaveOrderEntityRepository leaveOrderEntityRepository;
    private final LeaveCommentEntityRepository leaveCommentEntityRepository;

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

        List<LeaveCommentEntity> leaveCommentEntityList = new ArrayList<>();
        for(Comment comment:leaveOrder.getComments()){
            LeaveCommentEntity leaveCommentEntity = new LeaveCommentEntity();
            leaveCommentEntity.setId(comment.getCreateTime().getTime());
            leaveCommentEntity.setLeaveId(entity.getId());
            leaveCommentEntity.setFlag(comment.isFlag());
            leaveCommentEntity.setUserId(comment.getUser().getId());
            leaveCommentEntity.setCreateTime(comment.getCreateTime());
            leaveCommentEntity.setContent(comment.getContent());

            leaveCommentEntityList.add(leaveCommentEntity);
        }

        leaveCommentEntityRepository.saveAll(leaveCommentEntityList);
    }

    @Override
    public LeaveOrder getById(long leaveId) {
        LeaveOrderEntity entity = leaveOrderEntityRepository.findById(leaveId).orElse(null);
        if(entity==null){
            return null;
        }

        List<LeaveCommentEntity> leaveCommentEntityList = leaveCommentEntityRepository.findByLeaveId(leaveId);

        return new LeaveOrder(entity.getId(),
                entity.getContent(),
                entity.getCreateTime(),
                new User(entity.getCreateUserId()),
                new User(entity.getApprovalUserId()),
                LeaveOrder.State.parse(entity.getState()),
                leaveCommentEntityList.stream().map((item)->
                        new Comment(item.getContent(),
                            item.getCreateTime(),
                            new User(item.getUserId()),
                            item.isFlag()))
                        .collect(Collectors.toList())
                );
    }
}
