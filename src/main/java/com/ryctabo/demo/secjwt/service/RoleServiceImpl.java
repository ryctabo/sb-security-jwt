package com.ryctabo.demo.secjwt.service;

import java.util.List;
import java.util.Optional;
import com.ryctabo.demo.secjwt.persistence.entity.Role;
import com.ryctabo.demo.secjwt.persistence.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAll() {
        log.info("Fetching all roles");
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getByName(String name) {
        log.info("Fetching role with name {}", name);
        return roleRepository.findByName(name);
    }
}
