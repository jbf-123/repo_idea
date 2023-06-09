package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {

    public PageInfo findAllUserByPage(UserVo userVo);

    //用户登录
    public User login(User user) throws Exception;

    //分配角色（回显）
    public List<Role> findUserRelationRoleById(Integer id);

    //用户关联角色
    public void userContextRole(UserVo userVo);

    //获取用户权限信息
    public ResponseResult getUserPermissions(Integer userid);
}
