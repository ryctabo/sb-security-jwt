package com.ryctabo.demo.secjwt.persistence.repository;

import java.util.Optional;
import com.ryctabo.demo.secjwt.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
