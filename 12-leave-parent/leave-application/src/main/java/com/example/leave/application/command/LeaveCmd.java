package com.example.leave.application.command;

import lombok.Getter;
import lombok.Setter;

public class LeaveCmd {

    @Setter
    @Getter
    public static class CreateCommand{

        private String approvalUsername;
        private String createUsername;

        private String content;
    }


    @Setter
    @Getter
    public static class ApprovalCommand{

        private long leaveId;
        private String content;
        private boolean approval;
        private String approvalUsername;


    }
}
