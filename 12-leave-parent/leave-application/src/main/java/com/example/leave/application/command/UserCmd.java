package com.example.leave.application.command;

import lombok.Getter;
import lombok.Setter;

public class UserCmd {

    @Setter
    @Getter
    public static class CreateCommand{
        private String username;
        private String password;
    }


    @Setter
    @Getter
    public static class LoginCommand{
        private String username;
        private String password;
    }

}
