package com.neuedu.entity;

/**
 * 超级管理员实体类
 *
 * @author
 * @date 2021-7-10
 */
public class Admin {
    private String id;
    private String password;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}