package com.newland.mes.system.util;

import com.newland.mes.system.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
    public static User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
