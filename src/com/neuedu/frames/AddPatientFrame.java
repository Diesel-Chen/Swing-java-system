package com.neuedu.frames;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.neuedu.controllers.PatientController;
import com.neuedu.entity.Patient;
import com.neuedu.exception.IDRepeatException;

/**
 * @author
 * @Date 2021/07/16
 */
public class AddPatientFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private LoginPanel myPanl;
    // 创建静态文本对象
    private JLabel jlab_name = new JLabel("姓名");
    private JLabel jlab_birthday = new JLabel("出生日期");
    private JLabel jlab_sex = new JLabel("姓别");
    private JLabel jlab_identity = new JLabel("身份证号");
    private JLabel jlab_telephone = new JLabel("联系电话");
    private JLabel jlab_family = new JLabel("紧急联系人");
    private JLabel jlab_familytel = new JLabel("紧急联系电话");
    // 创建账号输入框对象
    private JTextField jtext_name = new JTextField();
    private JTextField jtext_birthday = new JTextField();
    private JTextField jtext_sex = new JTextField();
    private JTextField jtext_identity = new JTextField();
    private JTextField jtext_telephone = new JTextField();
    private JTextField jtext_family = new JTextField();
    private JTextField jtext_familytel = new JTextField();

    // 创建按钮对象
    private JButton jbtn_save = new JButton("保存");
    private JButton jbtn_fanhui = new JButton("返回");
    // 创建字体对象
    private Font font_01 = new Font("楷体", 1, 18);
    // 控件布局用的列坐标==x,行坐标 y == cow;
    private int col1_x = 40, col2_x = 180;

    // 初始化方法
    // 窗口一被new出来就有了各种属性
    private void init() {

        // name
        jlab_name.setFont(font_01);// 黑体
        jlab_name.setSize(80, 40);// width,height
        jlab_name.setLocation(col1_x, 90);// x,y
        // 输入框
        jtext_name.setSize(160, 25);
        jtext_name.setLocation(col2_x, 100);

        // birthday
        jlab_birthday.setFont(font_01);// 黑体
        jlab_birthday.setSize(80, 40);// width,height
        jlab_birthday.setLocation(col1_x, 130);// x,y
        // 输入框
        jtext_birthday.setSize(160, 25);
        jtext_birthday.setLocation(col2_x, 140);

        // zhuanchang
        jlab_sex.setFont(font_01);// 黑体
        jlab_sex.setSize(80, 40);// width,height
        jlab_sex.setLocation(col1_x, 170);// x,y
        // 输入框
        jtext_sex.setSize(160, 25);
        jtext_sex.setLocation(col2_x, 180);

        // pose
        jlab_identity.setFont(font_01);// 黑体
        jlab_identity.setSize(80, 40);// width,height
        jlab_identity.setLocation(col1_x, 210);// x,y
        // 输入框
        jtext_identity.setSize(160, 25);
        jtext_identity.setLocation(col2_x, 220);

        // telephone
        jlab_telephone.setFont(font_01);// 黑体
        jlab_telephone.setSize(80, 40);// width,height
        jlab_telephone.setLocation(col1_x, 250);// x,y
        // 输入框
        jtext_telephone.setSize(160, 25);
        jtext_telephone.setLocation(col2_x, 260);

        //family
        jlab_family.setFont(font_01);// 黑体
        jlab_family.setSize(100, 40);// width,height
        jlab_family.setLocation(col1_x, 290);// x,y
        // 输入框
        jtext_family.setSize(160, 25);
        jtext_family.setLocation(col2_x, 300);

        //familytel
        jlab_familytel.setFont(font_01);// 黑体
        jlab_familytel.setSize(120, 25);// width,height
        jlab_familytel.setLocation(col1_x, 340);// x,y
        // 输入框
        jtext_familytel.setSize(160, 25);
        jtext_familytel.setLocation(col2_x, 340);


        // 保存按钮的字体，大小和位置
        jbtn_save.setFont(font_01);
        jbtn_save.setSize(85, 50);// width,height
        jbtn_save.setLocation(40, 380);

        // 返回按钮的字体，大小和位置
        jbtn_fanhui.setFont(font_01);
        jbtn_fanhui.setSize(85, 50);// width,height
        jbtn_fanhui.setLocation(180, 380);
    }

    public AddPatientFrame() {
        // 设置登录窗口大小
        this.setSize(450, 470);// width,height
        // 固定窗口大小，防止因鼠标变动而改变
        this.setResizable(false);
        // 设置窗口标题
        this.setTitle("添加病患窗口");
        // 设置窗口居中显示
        this.setLocationRelativeTo(null);
        // 调用初始化方法
        init();
        myPanl = new LoginPanel();
        // 设置面板布局为空，用自己的，不用默认布局
        myPanl.setLayout(null);
        // 讲所有组件添加到到面板中
        myPanl.add(jlab_name);
        myPanl.add(jtext_name);
        myPanl.add(jlab_birthday);
        myPanl.add(jtext_birthday);
        myPanl.add(jlab_sex);
        myPanl.add(jtext_sex);
        myPanl.add(jlab_identity);
        myPanl.add(jtext_identity);
        myPanl.add(jlab_telephone);
        myPanl.add(jtext_telephone);
        myPanl.add(jlab_family);
        myPanl.add(jtext_family);
        myPanl.add(jlab_familytel);
        myPanl.add(jtext_familytel);
        myPanl.add(jbtn_save);
        myPanl.add(jbtn_fanhui);

        // 讲面板加入到窗口上：照片放到框里，框放到墙上
        this.getContentPane().add(myPanl);

        jbtn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = jtext_name.getText();
                String birdy = jtext_birthday.getText();
                String sex = jtext_sex.getText();
                String idcard = jtext_identity.getText();
                String tele = jtext_telephone.getText();
                String family = jtext_family.getText();
                String familytel = jtext_familytel.getText();

                if (name == null || name.equals("")) {
                    JOptionPane.showMessageDialog(null, "姓名不能为空");
                    // 输入框获取光点
                    jtext_name.requestFocus();
                    return;
                }
                if (name.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "姓名不能包括空格");
                    // 输入框获取光点
                    jtext_name.requestFocus();
                    return;
                }

                if (birdy == null || birdy.equals("")) {
                    JOptionPane.showMessageDialog(null, "出生日期不能为空");
                    // 输入框获取光点
                    jtext_birthday.requestFocus();
                    return;
                }
                if (birdy.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "出生日期不能包括空格");
                    // 输入框获取光点
                    jtext_birthday.requestFocus();
                    return;
                }

                if (sex == null || sex.equals("")) {
                    JOptionPane.showMessageDialog(null, "性别不能为空");
                    // 输入框获取光点
                    jtext_sex.requestFocus();
                    return;
                }
                if (sex.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "性别不能包括空格");
                    // 输入框获取光点
                    jtext_sex.requestFocus();
                    return;
                }
                if (idcard == null || idcard.equals("")) {
                    JOptionPane.showMessageDialog(null, "身份证不能为空");
                    // 输入框获取光点
                    jtext_identity.requestFocus();
                    return;
                }
                if (idcard.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "身份证不能包括空格");
                    // 输入框获取光点
                    jtext_identity.requestFocus();
                    return;
                }
                if (tele == null || tele.equals("")) {
                    JOptionPane.showMessageDialog(null, "电话号码不能为空");
                    // 输入框获取光点
                    jtext_telephone.requestFocus();
                    return;
                }
                if (tele.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "电话号码不能包括空格");
                    // 输入框获取光点
                    jtext_telephone.requestFocus();
                    return;
                }
                if (family == null || family.equals("")) {
                    JOptionPane.showMessageDialog(null, "紧急联系人不能为空");
                    // 输入框获取光点
                    jtext_family.requestFocus();
                    return;
                }
                if (family.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "紧急联系人不能包括空格");
                    // 输入框获取光点
                    jtext_family.requestFocus();
                    return;
                }
                if (familytel == null || familytel.equals("")) {
                    JOptionPane.showMessageDialog(null, "紧急联系人电话不能为空");
                    // 输入框获取光点
                    jtext_familytel.requestFocus();
                    return;
                }
                if (family.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "紧急联系人电话不能包括空格");
                    // 输入框获取光点
                    jtext_familytel.requestFocus();
                    return;
                }

                PatientController patientController = new PatientController("Patient");
                try {
                    Patient patient = new Patient();
                    patient.setName(name);
                    patient.setBirthday(birdy);
                    patient.setSex(sex);
                    patient.setIdentity(idcard);
                    patient.setTelephone(tele);
                    patient.setFamily(family);
                    patient.setFamilytel(familytel);
                    boolean result = patientController.addPatient(patient);
                    if (result) {
                        jtext_name.setText("");
                        jtext_birthday.setText("");
                        jtext_sex.setText("");
                        jtext_identity.setText("");
                        jtext_telephone.setText("");
                        jtext_family.setText("");
                        jtext_familytel.setText("");
                        JOptionPane.showMessageDialog(null, "添加成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (IDRepeatException e2) {
                    JOptionPane.showMessageDialog(null, "用户姓名重复,注册失败,请更换后再次尝试");
                }
            }

        });
        jbtn_fanhui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
    }

    public static void main(String[] args) {

        new AddPatientFrame().setVisible(true);
    }

    void close() {
        this.setVisible(false);
    }

    class LoginPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 绘制一张背景图片
            String path = "img/bg_0.jpg";
            ImageIcon icon = new ImageIcon(path);
            Image image = icon.getImage();
            g.drawImage(image, 0, 0, this);
        }

    }
}