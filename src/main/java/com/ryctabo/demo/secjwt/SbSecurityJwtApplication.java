package com.ryctabo.demo.secjwt;

import java.util.HashSet;
import com.ryctabo.demo.secjwt.persistence.entity.Role;
import com.ryctabo.demo.secjwt.persistence.entity.User;
import com.ryctabo.demo.secjwt.service.RoleService;
import com.ryctabo.demo.secjwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SbSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbSecurityJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService) {
        return args -> {
            roleService.save(new Role("ROLE_USER"));
            roleService.save(new Role("ROLE_MANAGER"));
            roleService.save(new Role("ROLE_ADMIN"));
            roleService.save(new Role("ROLE_SUPER_ADMIN"));

            userService.save(new User(null, "Gustavo Pacheco", "gustavo", "1234", new HashSet<>()));
            userService.save(new User(null, "Carina Milena", "carina", "1234", new HashSet<>()));
            userService.save(new User(null, "Lina Marcela", "lina", "1234", new HashSet<>()));
            userService.save(new User(null, "Kevin Pacheco", "kevin", "1234", new HashSet<>()));

            userService.addRoleToUser("gustavo", "ROLE_USER");
            userService.addRoleToUser("gustavo", "ROLE_MANAGER");
            userService.addRoleToUser("gustavo", "ROLE_ADMIN");
            userService.addRoleToUser("gustavo", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("carina", "ROLE_ADMIN");
            userService.addRoleToUser("lina", "ROLE_USER");
            userService.addRoleToUser("kevin", "ROLE_USER");
            userService.addRoleToUser("kevin", "ROLE_MANAGER");
        };
    }

}
