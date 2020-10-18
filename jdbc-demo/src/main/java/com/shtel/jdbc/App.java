package com.shtel.jdbc;

import com.shtel.jdbc.entity.User;
import com.shtel.jdbc.service.UserService;
import com.shtel.jdbc.service.UserServiceImpl;

import java.util.List;

/**
 * @author 王坤
 * @version 1.0.0
 * @Description
 * @date 2018/9/3
 * 版权所有 (c) 2018
 */
public class App {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        System.out.println("连接成功");
        userService.insertBatch();//插入学生


        List<User> all = userService.findAll();

        for (User user : all) {
            System.out.println(user);
        }
        
       
      /*  userService.insertBatch();*/
    }
}
