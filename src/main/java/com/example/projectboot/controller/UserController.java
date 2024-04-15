package com.example.projectboot.controller;


import com.example.projectboot.pojo.User;
import com.example.projectboot.service.UserService;
import com.example.projectboot.util.Result;
import com.example.projectboot.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/test")
 public List<User> test()
 {
     return userService.test();
 }

    @RequestMapping("/login")
    public Result select(@RequestBody User user){
        System.out.println("-----------"+user);
        User user1= userService.selectLogin(user);
        if(user1!=null){

//            登录成功
            TokenUtil tokenUtil = new TokenUtil();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("token",tokenUtil.createToken(user1));
            map.put("id",user1.getUserId());
            map.put("name",user1.getUserName());
            map.put("userImg",user1.getUserImg());
            return new Result(200,"登录成功",map);
        }else{
            //登录失败
            return new Result(500,"登录失败","err");
        }
    }

//    用户详情
    @RequestMapping("/selectPage")
    public Result selectPage(User user){
         Map<String,Object> map = userService.selectPage(user);

       return new Result(200,"分页查询效果",map);
    }

//    头像上传
   @RequestMapping ("/upload")
//    上传的文件信息
    public Result upload(MultipartFile file) throws IOException {
     //判断图片是否为空，
        if(file.isEmpty()){
            return new Result(500,"上传失败","err");
        }
        //2.获取图片的名称和后缀,保存的图片不重复
        String originalFilename = file.getOriginalFilename();
        //3.拆分
        String ext = "."+ originalFilename.split("\\.")[1];
        //生成一个新的文件名
        Long uuid = new Date().getTime();
        //生成文件名称
        String newName = uuid + ext;
        //将图片保存到对应的本地文件夹
        file.transferTo(new File("D:\\img\\"+newName));
        //返回图片的路径
        return new Result(200,"图片地址","http://localhost:8888/uploads/"+newName);
    }

//    新增
    @RequestMapping("/add")
    public Result add(@RequestBody User user){
       boolean bool = userService.save(user);  //新增方法
        return new Result(200,"新增成功",bool);
    }

//    修改
    @RequestMapping("/update")
    public Result update(@RequestBody User user){
        boolean bool = userService.updateById(user);
        return new Result(200,"修改成功",bool);
    }

//    删除
    @RequestMapping("/delete")
    public Result delete(int id){
        boolean bool = userService.removeById(id);
        return new Result(200,"删除成功",bool);
    }
//    详情
    @RequestMapping("/detail")
    public Result selectById(int id){
        User user = userService.getById(id);
        return new Result(200,"详情",user);
    }
}
