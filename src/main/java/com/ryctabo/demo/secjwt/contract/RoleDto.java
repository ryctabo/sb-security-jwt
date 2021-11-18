package com.ryctabo.demo.secjwt.contract;

import lombok.Builder;
import lombok.Value;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
@Value
@Builder
public class RoleDto {
    Long id;
    String name;
}
