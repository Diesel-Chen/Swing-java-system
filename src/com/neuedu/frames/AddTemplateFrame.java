package com.neuedu.frames;

import com.neuedu.controllers.PatientController;
import com.neuedu.controllers.QuestionController;
import com.neuedu.controllers.TemplateController;
import com.neuedu.entity.Patient;
import com.neuedu.entity.Question;
import com.neuedu.entity.Template;
import com.neuedu.exception.IDRepeatException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author
 * @Date 2021/07/16
 */
public class AddTemplateFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private LoginPanel myPanl;

    // 创建静态文本对象
    private JLabel jlab_name = new JLabel("模版名称");
    private JLabel jlab_type = new JLabel("模版类型");


    // 创建账号输入框对象
    private JTextField jtext_name = new JTextField();
    private JTextField jtext_type = new JTextField();



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

        // type
        jlab_type.setFont(font_01);// 黑体
        jlab_type.setSize(80, 40);// width,height
        jlab_type.setLocation(col1_x, 130);// x,y
        // 输入框
        jtext_type.setSize(160, 25);
        jtext_type.setLocation(col2_x, 140);



        // 保存按钮的字体，大小和位置
        jbtn_save.setFont(font_01);
        jbtn_save.setSize(85, 50);// width,height
        jbtn_save.setLocation(40, 280);

        // 返回按钮的字体，大小和位置
        jbtn_fanhui.setFont(font_01);
        jbtn_fanhui.setSize(85, 50);// width,height
        jbtn_fanhui.setLocation(180, 280);


    }

    public AddTemplateFrame() {
        // 设置登录窗口大小
        this.setSize(450, 470);// width,height
        // 固定窗口大小，防止因鼠标变动而改变
        this.setResizable(false);
        // 设置窗口标题
        this.setTitle("添加模版窗口");
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
        myPanl.add(jlab_type);
        myPanl.add(jtext_type);



        myPanl.add(jbtn_save);
        myPanl.add(jbtn_fanhui);

        // 讲面板加入到窗口上：照片放到框里，框放到墙上
        this.getContentPane().add(myPanl);

        jbtn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = jtext_name.getText();
                String type = jtext_type.getText();



                if (name == null || name.equals("")) {
                    JOptionPane.showMessageDialog(null, "模版名称不能为空");
                    // 输入框获取光点
                    jtext_name.requestFocus();
                    return;
                }
                if (name.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "模版名称不能包括空格");
                    // 输入框获取光点
                    jtext_name.requestFocus();
                    return;
                }

                if (type == null || type.equals("")) {
                    JOptionPane.showMessageDialog(null, "模版类型不能为空");
                    // 输入框获取光点
                    jtext_type.requestFocus();
                    return;
                }
                if (type.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "模版类型不能包括空格");
                    // 输入框获取光点
                    jtext_type.requestFocus();
                    return;
                }





                TemplateController templateController = new TemplateController("Template");

                try {
                    Template template = new Template(name,type);
                    boolean result = templateController.addTemplate(template);

                    if (result) {
                        jtext_name.setText("");
                        jtext_type.setText("");


                        JOptionPane.showMessageDialog(null, "添加成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
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

        new AddTemplateFrame().setVisible(true);
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