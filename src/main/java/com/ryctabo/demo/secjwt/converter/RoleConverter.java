package com.ryctabo.demo.secjwt.converter;

import com.ryctabo.demo.secjwt.contract.RoleDto;
import com.ryctabo.demo.secjwt.persistence.entity.Role;
import org.springframework.stereotype.Component;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
@Component
public class RoleConverter implements DtoConverter<Role, RoleDto, RoleDto> {
    @Override
    public RoleDto fromDomain(Role source) {
        return RoleDto.builder().name(source.getName()).build();
    }

    @Override
    public Role fromRequest(RoleDto source) {
        return new Role(source.getName().strip().replace(' ', '_').toUpperCase());
    }
}
