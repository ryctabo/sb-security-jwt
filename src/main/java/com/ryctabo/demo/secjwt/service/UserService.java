package com.ryctabo.demo.secjwt.service;

import java.util.List;
import java.util.Optional;
import com.ryctabo.demo.secjwt.persistence.entity.User;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
public interface UserService {
    User save(User user);
    void addRoleToUser(String username, String roleName);
    Optional<User> getUser(String username);
    List<User> getUsers();
}
