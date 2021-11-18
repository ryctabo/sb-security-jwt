package com.ryctabo.demo.secjwt.converter;

import java.util.stream.Collectors;
import com.ryctabo.demo.secjwt.contract.UserDto;
import com.ryctabo.demo.secjwt.persistence.entity.Role;
import com.ryctabo.demo.secjwt.persistence.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
@Component
public class UserConverter implements DtoConverter<User, UserDto, UserDto> {

    @Override
    public UserDto fromDomain(User source) {
        return UserDto.builder()
                .id(source.getId())
                .name(source.getName())
                .username(source.getUsername())
                .roles(source.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public User fromRequest(UserDto source) {
        User user = new User();

        user.setName(source.getName().strip());
        user.setUsername(source.getUsername().strip().replace(' ', '_'));
        user.setPassword(source.getPassword());

        return user;
    }
}
