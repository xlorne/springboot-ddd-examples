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
}
