package com.newland.mes.system.service;

import com.newland.mes.system.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface RoleService {
    List<GrantedAuthority> mapToGrantedAuthorities(User user);
}
