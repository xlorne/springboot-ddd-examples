package com.example.leave.repository.impl;

import com.codingapi.springboot.framework.convert.BeanConvertor;
import com.example.leave.dao.UserCount;
import com.example.leave.dao.UserEntity;
import com.example.leave.entity.User;
import com.example.leave.jpa.repository.UserCountRepository;
import com.example.leave.jpa.repository.UserEntityRepository;
import com.example.leave.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository repository;

    private final UserCountRepository userCountRepository;

    @Override
    public void save(User user) {
        UserEntity entity = BeanConvertor.convert(user,UserEntity.class);

        repository.save(entity);

        //模拟数据拓展更改.
        int count = repository.findAll().size();
        UserCount userCount = new UserCount(1,count);
        userCountRepository.save(userCount);
    }
}
