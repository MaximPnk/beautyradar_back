package ru.beautyradar.frontgateway.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.beautyradar.frontgateway.dao.RoleRepository;
import ru.beautyradar.frontgateway.dto.UserDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.entity.RoleEntity;
import ru.beautyradar.frontgateway.entity.UserEntity;
import ru.beautyradar.frontgateway.map.RoleMapper;
import ru.beautyradar.frontgateway.service.inter.RoleService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;


    @Override
    public List<RoleEntity> findRoles(Long id) {
        return repository.findAllById(Collections.singleton(id));
    }
}
