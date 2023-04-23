package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();
        return  new ResponseResult(true,200,"查询所有菜单成功",allMenu);
    }

    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        if (id==-1){
            //新增操作 回显信息中不需要查询menu的值
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);
            return  new ResponseResult(true,200,"新增回显成功",map);
        }else {
            //修改操作,回显所有menu的值
            Menu menu=menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);
            return  new ResponseResult(true,200,"修改回显成功",map);
        }

    }

}
