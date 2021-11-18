package com.ryctabo.demo.secjwt.service;

import java.util.List;
import java.util.Optional;
import com.ryctabo.demo.secjwt.persistence.entity.Role;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
public interface RoleService {
    Role save(Role role);

    List<Role> getAll();

    Optional<Role> getByName(String name);
}
