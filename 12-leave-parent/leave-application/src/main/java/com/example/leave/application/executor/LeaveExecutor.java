package com.example.leave.application.executor;

import com.example.leave.application.command.LeaveCmd;
import com.example.leave.domain.entity.Comment;
import com.example.leave.domain.entity.LeaveOrder;
import com.example.leave.domain.entity.User;
import com.example.leave.domain.exception.LeaveApprovalNotMatchException;
import com.example.leave.domain.exception.ParamVerifyException;
import com.example.leave.domain.repository.LeaveOrderRepository;
import com.example.leave.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LeaveExecutor {

    private final UserRepository userRepository;

    private final LeaveOrderRepository leaveOrderRepository;


    public void createLeave(LeaveCmd.CreateCommand command) throws ParamVerifyException {

        User createUser = userRepository.getUserByUsername(command.getCreateUsername());

        User approvalUser = userRepository.getUserByUsername(command.getApprovalUsername());

        LeaveOrder leaveOrder = new LeaveOrder(command.getContent(),createUser,approvalUser);
        leaveOrder.verify();
        leaveOrder.createFinish();

        leaveOrderRepository.save(leaveOrder);
    }

    public void approval(LeaveCmd.ApprovalCommand command) throws LeaveApprovalNotMatchException, ParamVerifyException {

        User approvalUser = userRepository.getUserByUsername(command.getApprovalUsername());
        LeaveOrder leaveOrder = leaveOrderRepository.getById(command.getLeaveId());

        Comment comment;
        if(command.isApproval()){
            comment = new Comment(approvalUser);
        }else{
            comment = new Comment(command.getContent(),approvalUser);
        }

        leaveOrder.approval(comment);

        leaveOrderRepository.save(leaveOrder);
    }
}
