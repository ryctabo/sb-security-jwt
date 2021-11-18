package com.ryctabo.demo.secjwt.persistence.repository;

import java.util.Optional;
import com.ryctabo.demo.secjwt.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
