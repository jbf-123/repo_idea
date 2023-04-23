package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVo);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }

    @Override
    public User login(User user) throws Exception {
        User user2 = userMapper.login(user);
        if (user2!=null && Md5.verify(user.getPassword(),"lagou",user2.getPassword())){
            return user2;
        }else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> userRelationRoleById = userMapper.findUserRelationRoleById(id);
        return userRelationRoleById;
    }

    @Override
    public void userContextRole(UserVo userVo) {
        userMapper.deleteUserContextRole(userVo.getUserId());
        for (Integer roleid : userVo.getRoleIdList()) {
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleid);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.userContextRole(user_role_relation);
        }
    }

    
    @Override
    public ResponseResult getUserPermissions(Integer userid) {
        //1.获取当前用户拥有的角色
        List<Role> roleList= userMapper.findUserRelationRoleById(userid);

        //2.获取角色ID，保存到List集合中
        ArrayList<Integer> list = new ArrayList<>();
        for (Role role : roleList) {
            list.add(role.getId());
        }
        //3 根据角色id查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(list);

        //4 查询封装父菜单关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubmenuList(subMenu);
        }

        //5 获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);

        //6 封装数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);


        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }
}
