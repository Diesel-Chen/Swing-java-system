package com.neuedu.entity;

/**
 * 工作人员实体类
 *
 * @author
 * @date 2021-7-10
 */
public class Worker {
    private String id;
    private String password;
    private String name;
    private String birthday;
    private String zhuanchang;
    private String post;
    private String telephone;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getZhuanchang() {
        return zhuanchang;
    }

    public void setZhuanchang(String zhuanchang) {
        this.zhuanchang = zhuanchang;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
