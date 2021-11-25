package com.newland.mes.system.util;

import com.newland.mes.system.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "el")
public class ElPermissionConfig {
    public Boolean check(String ...permissions){
        // 获取当前用户的所有权限
        User principal = SecurityUtils.getCurrentUser();
        List<String> elPermissions = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        // 判断当前用户的所有权限是否包含接口上定义的权限
        return Arrays.stream(permissions).anyMatch(elPermissions::contains) | elPermissions.contains("admin");
    }
}
