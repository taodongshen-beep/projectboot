package com.example.projectboot.service;

import com.example.projectboot.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author Lenovo
* @description 针对表【user】的数据库操作Service
* @createDate 2024-04-02 17:24:17
*/
public interface UserService extends IService<User> {


    List<User> test();

    User selectLogin(User user);

    Map<String, Object> selectPage(User user);
}
