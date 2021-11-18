package com.ryctabo.demo.secjwt.converter;

import java.util.stream.Collectors;
import com.ryctabo.demo.secjwt.persistence.entity.Role;
import com.ryctabo.demo.secjwt.persistence.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
@Component
public class UserDetailsConverter implements Converter<User, org.springframework.security.core.userdetails.User> {
    @Override
    public org.springframework.security.core.userdetails.User convert(User source) {
        return new org.springframework.security.core.userdetails.User(
                source.getUsername(),
                source.getPassword(),
                source.getRoles().stream()
                        .map(Role::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
    }
}
