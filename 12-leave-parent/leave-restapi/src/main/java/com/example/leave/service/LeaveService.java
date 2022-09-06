package com.example.leave.service;

import com.codingapi.springboot.framework.dto.response.Response;
import com.codingapi.springboot.framework.exception.LocaleMessageException;
import com.example.leave.application.command.LeaveCmd;
import com.example.leave.application.executor.LeaveExecutor;
import com.example.leave.domain.exception.LeaveApprovalNotMatchException;
import com.example.leave.domain.exception.ParamVerifyException;
import com.example.leave.dto.LeaveDTO;
import com.example.leave.jwt.TokenContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeaveService {

    private final LeaveExecutor leaveExecutor;

    public Response approval(LeaveDTO.ApprovalRequest request){
        try {
            LeaveCmd.ApprovalCommand command = new LeaveCmd.ApprovalCommand();
            command.setApproval(request.isApproval());
            command.setContent(request.getContent());
            command.setLeaveId(request.getLeaveId());
            command.setApprovalUsername(TokenContext.getInstance().currentUserName());

            leaveExecutor.approval(command);
        } catch (LeaveApprovalNotMatchException e) {
            throw new LocaleMessageException("leave.approval.error",e);
        } catch (ParamVerifyException e) {
            throw new LocaleMessageException("leave.verify.error",e);
        }
        return Response.buildSuccess();
    }
}
