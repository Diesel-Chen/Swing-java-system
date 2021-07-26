package com.neuedu.entity;

import javax.swing.*;

/**
 * 抽象出的试题实体类
 *
 * @author
 * @date 2021-7-10
 */
public class PaperInfo {
    //问题名
    private JLabel label1;
    //选择一
    private JRadioButton bt1;
    //选择二
    private JRadioButton bt2;
    //选择三
    private JRadioButton bt3;

    public PaperInfo(JLabel label1, JRadioButton bt1, JRadioButton bt2, JRadioButton bt3) {
        this.label1 = label1;
        this.bt1 = bt1;
        this.bt2 = bt2;
        this.bt3 = bt3;
    }

    public JLabel getLabel1() {
        return label1;
    }

    public void setLabel1(JLabel label1) {
        this.label1 = label1;
    }

    public JRadioButton getBt1() {
        return bt1;
    }

    public void setBt1(JRadioButton bt1) {
        this.bt1 = bt1;
    }

    public JRadioButton getBt2() {
        return bt2;
    }

    public void setBt2(JRadioButton bt2) {
        this.bt2 = bt2;
    }

    public JRadioButton getBt3() {
        return bt3;
    }

    public void setBt3(JRadioButton bt3) {
        this.bt3 = bt3;
    }
}
