package com.shtel.jdbc.entity;

/**
 * @author 王坤
 * @version 1.0.0
 * @Description
 * @date 2018/9/3
 * 版权所有 (c) 2018
 */
public class User {
    private Long sno;
    private String name;
    private Integer age;

    public Long getId() {
        return sno;
    }

    public void setId(Long sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "sno=" + sno +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
