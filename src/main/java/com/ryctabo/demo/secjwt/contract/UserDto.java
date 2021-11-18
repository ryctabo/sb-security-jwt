package com.ryctabo.demo.secjwt.contract;

import java.util.List;
import lombok.Builder;
import lombok.Value;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
@Value
@Builder
public class UserDto {
    Long id;
    String name;
    String username;
    String password;
    List<String> roles;
}
