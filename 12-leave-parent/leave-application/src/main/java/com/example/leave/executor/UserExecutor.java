package com.example.leave.executor;

import com.example.leave.command.UserCommand;
import com.example.leave.entity.User;
import com.example.leave.exception.ParamVerifyException;
import com.example.leave.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserExecutor {

    private final UserRepository userRepository;

    public long saveUser(UserCommand.CreateCommand command) throws ParamVerifyException {
        User user = new User(command.getUsername(),command.getPassword());
        user.verify();

        userRepository.save(user);

        return user.getId();
    }
}
