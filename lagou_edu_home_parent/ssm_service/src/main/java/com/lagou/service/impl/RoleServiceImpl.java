package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> roleList = roleMapper.findAllRole(role);
        return roleList;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {

        List<Integer> integerList = roleMapper.findMenuByRoleId(roleid);
        return integerList;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        //为角色分配菜单
        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedBy("system");
            roleMapper.RoleContextMenu(role_menu_relation);
        }

    }

    @Override
    public void deleteRole(Integer roleid) {
        //根据roleid清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleid);

        roleMapper.deleteRole(roleid);
    }
}
