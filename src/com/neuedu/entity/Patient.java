package com.neuedu.entity;

/**
 * 病患实体类
 *
 * @author
 * @date 2021-7-10
 */
public class Patient {
    private String name;
    private String birthday;
    private String sex;
    private String identity;
    private String telephone;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFamilytel() {
        return familytel;
    }

    public void setFamilytel(String familytel) {
        this.familytel = familytel;
    }

    private String family;
    private String familytel;

}