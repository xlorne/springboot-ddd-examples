package com.example.leave.controller;

import com.codingapi.springboot.framework.dto.request.PageRequest;
import com.codingapi.springboot.framework.dto.response.MultiResponse;
import com.codingapi.springboot.framework.dto.response.Response;
import com.codingapi.springboot.framework.exception.LocaleMessageException;
import com.example.leave.application.command.LeaveCmd;
import com.example.leave.application.executor.LeaveExecutor;
import com.example.leave.domain.exception.ParamVerifyException;
import com.example.leave.dto.LeaveDTO;
import com.example.leave.infrastructure.db.entity.LeaveOrderEntity;
import com.example.leave.infrastructure.db.jpa.repository.LeaveOrderEntityRepository;
import com.example.leave.jwt.TokenContext;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leave")
@AllArgsConstructor
public class LeaveController {

    private final LeaveExecutor leaveExecutor;

    private final LeaveOrderEntityRepository leaveOrderEntityRepository;

    @PostMapping("/createLeave")
    public Response createLeave(@RequestBody LeaveDTO.CreateRequest request){
        try {
            LeaveCmd.CreateCommand command = new LeaveCmd.CreateCommand();

            command.setCreateUsername(TokenContext.getInstance().currentUserName());
            command.setContent(request.getContent());
            command.setApprovalUsername(request.getApproval());

            leaveExecutor.createLeave(command);
        } catch (ParamVerifyException e) {
            throw new LocaleMessageException("leave.verify.error",e);
        }
        return Response.buildSuccess();
    }


    @GetMapping("/myLeave")
    public MultiResponse<LeaveOrderEntity> myLeave(PageRequest request){
        long userId = TokenContext.getInstance().currentUserId();
        return MultiResponse.of(leaveOrderEntityRepository.findByCreateUserId(userId,request));
    }

}
