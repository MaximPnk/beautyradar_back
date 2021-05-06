package ru.beautyradar.uploadservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.beautyradar.uploadservice.dao.UserRepository;
import ru.beautyradar.uploadservice.entity.UserEntity;
import ru.beautyradar.uploadservice.exc.ResourceNotFoundException;
import ru.beautyradar.uploadservice.service.inter.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователь с таким id не существует"));
    }
}
