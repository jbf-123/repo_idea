package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> roleList = roleService.findAllRole(role);
        return  new ResponseResult(true,200,"查询成功",roleList);
    }


    //查询所有的父子菜单信息
    @RequestMapping("/findSubMenuListByPid")
    public ResponseResult findSubMenuListByPid(){
        //查询所有的父子级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);
        return  responseResult;
    }

    //根据角色id查询角色关联的菜单信息
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        return  new ResponseResult(true,200,"响应成功",menuByRoleId);

    }
    //为角色分配菜单
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
        return  responseResult;
    }

    //删除角色
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer Id){
        roleService.deleteRole(Id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", null);
        return  responseResult;

    }
}
