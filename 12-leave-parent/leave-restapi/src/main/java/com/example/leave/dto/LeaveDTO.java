package com.example.leave.dto;

import lombok.Getter;
import lombok.Setter;

public class LeaveDTO {

    @Setter
    @Getter
    public static class CreateRequest{

        private String content;
        private String approval;
    }

    @Setter
    @Getter
    public static class ApprovalRequest{
        private long leaveId;
        private String content;
        private boolean approval;
    }
}
