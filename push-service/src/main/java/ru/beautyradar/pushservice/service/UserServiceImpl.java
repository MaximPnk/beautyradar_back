package ru.beautyradar.pushservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.beautyradar.pushservice.dao.UserRepository;
import ru.beautyradar.pushservice.exc.ResourceNotFoundException;
import ru.beautyradar.pushservice.service.inter.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String getTokenById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователь с таким id не существует")).getToken();
    }
}
