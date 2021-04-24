package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.beautyradar.frontgateway.dao.UserRepository;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.map.UserMapper;
import ru.beautyradar.frontgateway.service.inter.UserService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto getUserByUpn(String upn) {
        Optional<UserEntity> userEntity = userRepository.findFirstByUpn(upn);
        return userEntity.map(userMapper::map).orElse(null);
    }

    @Override
    @Async
    @Transactional
    public void saveUser(UserDto userDto) {
        userRepository.save(userMapper.map(userDto));
    }
}
