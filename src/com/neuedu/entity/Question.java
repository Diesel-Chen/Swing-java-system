package com.neuedu.entity;

import com.neuedu.utils.CommonUtil;

public class Question {
    private String id;
    private String name;
    private String type;
    private String answer1;
    private String answer2;
    private String answer3;
    private String templateId;


    public Question( String name, String type, String answer1, String answer2, String answer3, String templateId) {
        this.id = CommonUtil.GenerateId();
        this.name = name;
        this.type = type;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.templateId = templateId;
    }

    public Question(){}

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

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
