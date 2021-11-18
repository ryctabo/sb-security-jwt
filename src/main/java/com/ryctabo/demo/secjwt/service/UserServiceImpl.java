package com.ryctabo.demo.secjwt.service;

import java.util.List;
import java.util.Optional;
import com.ryctabo.demo.secjwt.converter.UserDetailsConverter;
import com.ryctabo.demo.secjwt.exception.RoleNotFoundException;
import com.ryctabo.demo.secjwt.exception.UserNotFoundException;
import com.ryctabo.demo.secjwt.persistence.entity.Role;
import com.ryctabo.demo.secjwt.persistence.entity.User;
import com.ryctabo.demo.secjwt.persistence.repository.RoleRepository;
import com.ryctabo.demo.secjwt.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserDetailsConverter userDetailsConverter;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        log.info("Saving a new user {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Assign role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(RoleNotFoundException::new);

        user.getRoles().add(role);
    }

    @Override
    public Optional<User> getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(userDetailsConverter::convert)
                .orElseThrow(() -> {
                    log.error("User {} not found in the database", username);
                    return new UsernameNotFoundException("The user was not found!");
                });
    }
}
