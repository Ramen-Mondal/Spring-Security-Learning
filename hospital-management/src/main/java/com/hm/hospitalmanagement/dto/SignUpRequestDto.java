package com.hm.hospitalmanagement.dto;


import com.hm.hospitalmanagement.entity.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private String username;
    private String password;
    private String name;
    private Set<RoleType> roles = new HashSet<>(); //this role filed for learning and understanding not for production ready code
}
