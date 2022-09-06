package com.example.leave.infrastructure.db.repository.impl;

import com.codingapi.springboot.framework.convert.BeanConvertor;
import com.example.leave.domain.entity.User;
import com.example.leave.domain.repository.UserRepository;
import com.example.leave.infrastructure.db.entity.UserEntity;
import com.example.leave.infrastructure.db.jpa.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository repository;

    @Override
    public void save(User user) {
        UserEntity entity = BeanConvertor.convert(user,UserEntity.class);

        repository.save(entity);
    }
}
