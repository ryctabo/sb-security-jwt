package com.ryctabo.demo.secjwt.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import com.ryctabo.demo.secjwt.contract.RoleDto;
import com.ryctabo.demo.secjwt.converter.RoleConverter;
import com.ryctabo.demo.secjwt.persistence.entity.Role;
import com.ryctabo.demo.secjwt.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("roles")
public class RoleResource {

    private final RoleService roleService;

    private final RoleConverter roleConverter;

    @GetMapping
    public ResponseEntity<List<String>> get() {
        return ResponseEntity.ok(roleService.getAll().stream()
                .map(Role::getName)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<RoleDto> post(@RequestBody RoleDto body) {
        final Role role = roleService.save(roleConverter.fromRequest(body));
        final URI location = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path(role.getName())
                .toUriString());
        return ResponseEntity.created(location)
                .body(roleConverter.fromDomain(role));
    }

    @GetMapping("{name}")
    public ResponseEntity<RoleDto> getByName(@PathVariable("name") String name) {
        return roleService.getByName(name)
                .map(roleConverter::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
