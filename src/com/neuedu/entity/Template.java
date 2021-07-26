package com.neuedu.entity;

import com.neuedu.utils.CommonUtil;

/**
 * 模版实体类
 *
 * @author
 * @date 2021-7-10
 */
public class Template {
    private String id;
    private String name;
    private String type;

    public Template(String name, String type) {
        this.id = CommonUtil.GenerateId();
        this.name = name;
        this.type = type;
    }
    public Template(String name){
        this.name = name;
    }

    public Template(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
