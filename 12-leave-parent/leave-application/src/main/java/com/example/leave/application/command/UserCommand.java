package com.example.leave.application.command;

import lombok.Getter;
import lombok.Setter;

public class UserCommand {

    @Setter
    @Getter
    public static class CreateCommand{
        private String username;
        private String password;
    }

}
