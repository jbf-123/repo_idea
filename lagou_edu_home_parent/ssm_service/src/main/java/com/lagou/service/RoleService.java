package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {
    //查询所有角色信息（条件）
    public List<Role> findAllRole(Role role);

    //根据角色id查询角色关联的菜单信息
    public List<Integer> findMenuByRoleId(Integer roleid);

    //为角色分配菜单
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    //删除角色
    public void deleteRole(Integer roleid);
}
