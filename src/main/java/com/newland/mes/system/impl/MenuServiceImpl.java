package com.newland.mes.system.impl;

import com.newland.mes.system.entity.Menu;
import com.newland.mes.system.entity.Role;
import com.newland.mes.system.entity.User;
import com.newland.mes.system.mapper.MenuMapper;
import com.newland.mes.system.mapper.RoleMapper;
import com.newland.mes.system.service.MenuService;
import com.newland.mes.system.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RoleMapper roleMapper;
    private List<Menu> getMenuByUser(){
        User user= SecurityUtils.getCurrentUser();
        Set<Role> role=roleMapper.findByUserId(user.getId());
        if(role.size()==0) return null;
        String menuIds=role.iterator().next().getMenuIds();

        return menuMapper.getByMenuIds(menuIds);
    }
   @Override
    public List<Menu> buildTrees() {
       List<Menu> list=getMenuByUser();
        List<Menu> trees=new ArrayList<>();

        for (Menu menu:list) {
            if(menu.getParentId()==0)
            {
                trees.add(menu);
                for(Menu it:list)
                {
                    if(it.getParentId()==menu.getId())
                    {
                        if(menu.getChild()==null)
                            menu.setChild(new ArrayList<>());
                        menu.getChild().add(it);
                    }
                }
            }
        }
        return trees;
    }
}
