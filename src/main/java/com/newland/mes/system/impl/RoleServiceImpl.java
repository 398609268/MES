package com.newland.mes.system.impl;



import com.newland.mes.system.entity.Menu;
import com.newland.mes.system.entity.Role;
import com.newland.mes.system.entity.User;
import com.newland.mes.system.mapper.MenuMapper;
import com.newland.mes.system.mapper.RoleMapper;
import com.newland.mes.system.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(User user) {
        Set<String> permissions = new HashSet<>();
        Set<Role> roles=roleMapper.findByUserId(user.getId());
        permissions=roles.stream().flatMap(role -> menuMapper.getByMenuIds(role.getMenuIds()).stream())
                .filter(menu -> !StringUtils.isEmpty(menu.getPermission()))
                .map(Menu::getPermission).collect(Collectors.toSet());

        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
