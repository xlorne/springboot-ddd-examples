package com.example.leave.application.executor;

import com.example.leave.application.command.UserCommand;
import com.example.leave.domain.entity.User;
import com.example.leave.domain.exception.ParamVerifyException;
import com.example.leave.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserExecutor {

    private final UserRepository userRepository;

    public long createUser(UserCommand.CreateCommand command) throws ParamVerifyException {
        User user = new User(command.getUsername(),command.getPassword());
        user.verify();

        userRepository.save(user);

        return user.getId();
    }
}
