package com.example.projectboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.projectboot.pojo.Menu;
import com.example.projectboot.service.MenuService;
import com.example.projectboot.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @RequestMapping("/getMenu")
    public Result getmenu(){
        QueryWrapper<Menu> wrapper = new QueryWrapper<Menu>();
        wrapper.eq("prent_id",0);
       List<Menu> fMenu= menuService.list(wrapper);
//      获取所有菜单
      List<Menu> allMenu= menuService.list();
        //将二级菜单放入一级菜单
        for (Menu menu:
            fMenu ) {
            List<Menu> list=new ArrayList<Menu>();
            //二级菜单
            for (Menu menu1:
               allMenu  ) {
                //子菜单的父id=父亲id，添加
                if(menu.getMenuId()==menu1.getPrentId()){

                   list.add(menu1);
                }
            }
            menu.setSonMenu(list);
        }
       return new Result<>(200,"返回一级菜单",fMenu);
    }

}
