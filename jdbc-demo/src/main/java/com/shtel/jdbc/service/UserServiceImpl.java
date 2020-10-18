package com.shtel.jdbc.service;

import com.shtel.jdbc.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王坤
 * @version 1.0.0
 * @Description
 * @date 2018/9/3
 * 版权所有 (c) 2018
 */
public class UserServiceImpl implements UserService {
    private String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private String username = "root";
    private String password = "";


    private static String sql = "select * from student";

    private static String sql1 = "insert into student(sno,name,age) values (?,?,?)";

    private static String sql2 = "select name,age from student where sno='110'";

    private static String sql3 = "delete from student where sno='1'";
    private static String sql4 = "update student set age = '25' where sno='2'";


    static {
        try {
            // 1 加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<User>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;

        try {
            // 2 获得数据库链接
            conn = DriverManager.getConnection(url, username, password);
            // 3
            pstmt = conn.prepareStatement(sql);
            // 4
            rst = pstmt.executeQuery();
            while (rst.next()) {
                User user = new User();
                user.setId(rst.getLong("sno"));
                user.setName(rst.getString("name"));
                user.setAge(rst.getInt("age"));

                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rst);
        }

        return userList;
    }

    public void insertBatch() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 2 获得数据库链接
            conn = DriverManager.getConnection(url, username, password);
            // 3
            pstmt = conn.prepareStatement(sql1);
            // 4
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 1000; j++) {
                    pstmt.setString(1, "0" + i  + j);
                    pstmt.setString(2, "SXJ" + i + "_" + j);
                    pstmt.setString(3, "22");
                    pstmt.addBatch();
                }
            }

            pstmt.executeBatch();
            //pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    pstmt = null;
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    conn = null;
                    e.printStackTrace();
                }
            }
        }
    }



    private void close(Connection conn, PreparedStatement pstmt, ResultSet rst) {
        if (rst != null) {
            try {
                rst.close();
            } catch (SQLException e) {
                rst = null;
                e.printStackTrace();
            }
        }

        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                pstmt = null;
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                conn = null;
                e.printStackTrace();
            }
        }
    }
}
