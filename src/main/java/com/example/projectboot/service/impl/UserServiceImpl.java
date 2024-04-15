package com.example.projectboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.projectboot.pojo.User;
import com.example.projectboot.service.UserService;
import com.example.projectboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Lenovo
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-04-02 17:24:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> test() {
        return userMapper.selectList(null);
    }

    @Override
    public User selectLogin(User user) {
     QueryWrapper<User> queryWrapper = new QueryWrapper<>();
     queryWrapper
             .eq("user_account",user.getUserAccount())
             .eq("user_password",user.getUserPassword());
     return userMapper.selectOne(queryWrapper);
    }

    @Override
    public Map<String, Object> selectPage(User user) {
//        创建分页对象
        Page<User> page=new Page<User>(user.getPage(),user.getSize());
//        条件查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("user_name",user.getUserName());
//        分页查询
        userMapper.selectPage(page,queryWrapper);
//        查询结果在page对象中
        List<User> list = page.getRecords();
//        获取总行数
        long total = page.getTotal();
//        创建map
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list",list);
        map.put("total",total);
        return map;
    }
}




