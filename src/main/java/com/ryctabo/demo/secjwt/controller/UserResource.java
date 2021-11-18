package com.ryctabo.demo.secjwt.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import com.ryctabo.demo.secjwt.contract.UserDto;
import com.ryctabo.demo.secjwt.converter.UserConverter;
import com.ryctabo.demo.secjwt.exception.RoleNotFoundException;
import com.ryctabo.demo.secjwt.exception.UserNotFoundException;
import com.ryctabo.demo.secjwt.persistence.entity.User;
import com.ryctabo.demo.secjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserResource {

    private final UserService userService;

    private final UserConverter userConverter;

    @GetMapping
    public ResponseEntity<List<UserDto>> get() {
        return ResponseEntity.ok(userService.getUsers().stream()
                .map(userConverter::fromDomain)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UserDto> post(@RequestBody UserDto body) {
        final User user = userService.save(userConverter.fromRequest(body));
        final URI location = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path(user.getId().toString())
                .toUriString());
        return ResponseEntity.created(location)
                .body(userConverter.fromDomain(user));
    }

    @GetMapping("{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable("username") String username) {
        return userService.getUser(username)
                .map(userConverter::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("{username}/assign/{role}")
    public ResponseEntity<?> addRoleToUser(
            @PathVariable("username") String username,
            @PathVariable("role") String roleName) {
        try {
            userService.addRoleToUser(username, roleName);
        } catch (UserNotFoundException | RoleNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
        return ResponseEntity.ok().build();
    }
}
