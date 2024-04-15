package com.example.projectboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.projectboot.pojo.Menu;
import com.example.projectboot.service.MenuService;
import com.example.projectboot.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Lenovo
* @description 针对表【menu】的数据库操作Service实现
* @createDate 2024-04-02 17:57:03
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{


}




