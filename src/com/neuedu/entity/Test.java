package com.neuedu.entity;

/**
 * 评估实体类
 *
 * @author
 * @date 2021-7-10
 */
public class Test {
    private String patientName;
    private String patientSex;
    private String templateName;
    private String templateType;
    private String createTime;
    private String operator;
    private Integer score;
    private String suggestion;

    public Test(String patientName, String patientSex, String templateName, String templateType, String createTime, String operator, Integer score, String suggestion) {
        this.patientName = patientName;
        this.patientSex = patientSex;
        this.templateName = templateName;
        this.templateType = templateType;
        this.createTime = createTime;
        this.operator = operator;
        this.score = score;
        this.suggestion = suggestion;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
