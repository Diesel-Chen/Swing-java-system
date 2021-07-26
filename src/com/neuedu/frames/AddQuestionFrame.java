package com.neuedu.frames;

import com.neuedu.controllers.PatientController;
import com.neuedu.controllers.QuestionController;
import com.neuedu.entity.Patient;
import com.neuedu.entity.Question;
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
public class AddQuestionFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private LoginPanel myPanl;
    // 创建静态文本对象
    private JLabel jlab_selectedTemplateId = new JLabel("模版id");
    private JLabel jlab_selectedTemplateName = new JLabel("模版名称");
    private JLabel jlab_question_name = new JLabel("问题名称");
    private JLabel jlab_question_type = new JLabel("问题类型");
    private JLabel jlab_question_answer1 = new JLabel("选择一");
    private JLabel jlab_question_answer2 = new JLabel("选择二");
    private JLabel jlab_question_answer3 = new JLabel("选择三");
    // 创建账号输入框对象
    private JTextField jtext_selectedTemplateId = new JTextField();
    private JTextField jtext_selectedTemplateName = new JTextField();
    private JTextField jtext_question_name = new JTextField();
    private JTextField jtext_question_type = new JTextField();
    private JTextField jtext_question_answer1 = new JTextField();
    private JTextField jtext_question_answer2 = new JTextField();
    private JTextField jtext_question_answer3 = new JTextField();

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

        jlab_selectedTemplateId.setFont(font_01);// 黑体
        jlab_selectedTemplateId.setSize(80, 40);// width,height
        jlab_selectedTemplateId.setLocation(col1_x, 90);// x,y
        // 输入框
        jtext_selectedTemplateId.setSize(160, 25);
        jtext_selectedTemplateId.setLocation(col2_x, 100);

        //不可编辑
        jtext_selectedTemplateId.setEditable(false);


        jlab_selectedTemplateName.setFont(font_01);// 黑体
        jlab_selectedTemplateName.setSize(80, 40);// width,height
        jlab_selectedTemplateName.setLocation(col1_x, 130);// x,y
        // 输入框
        jtext_selectedTemplateName.setSize(160, 25);
        jtext_selectedTemplateName.setLocation(col2_x, 140);
        //不可编辑
        jtext_selectedTemplateName.setEditable(false);

        // zhuanchang
        jlab_question_name.setFont(font_01);// 黑体
        jlab_question_name.setSize(80, 40);// width,height
        jlab_question_name.setLocation(col1_x, 170);// x,y
        // 输入框
        jtext_question_name.setSize(160, 25);
        jtext_question_name.setLocation(col2_x, 180);

        // pose
        jlab_question_type.setFont(font_01);// 黑体
        jlab_question_type.setSize(80, 40);// width,height
        jlab_question_type.setLocation(col1_x, 210);// x,y
        // 输入框
        jtext_question_type.setSize(160, 25);
        jtext_question_type.setLocation(col2_x, 220);

        // question_answer1phone
        jlab_question_answer1.setFont(font_01);// 黑体
        jlab_question_answer1.setSize(80, 40);// width,height
        jlab_question_answer1.setLocation(col1_x, 250);// x,y
        // 输入框
        jtext_question_answer1.setSize(160, 25);
        jtext_question_answer1.setLocation(col2_x, 260);

        //question_answer2
        jlab_question_answer2.setFont(font_01);// 黑体
        jlab_question_answer2.setSize(100, 40);// width,height
        jlab_question_answer2.setLocation(col1_x, 290);// x,y
        // 输入框
        jtext_question_answer2.setSize(160, 25);
        jtext_question_answer2.setLocation(col2_x, 300);

        //question_answer3
        jlab_question_answer3.setFont(font_01);// 黑体
        jlab_question_answer3.setSize(120, 25);// width,height
        jlab_question_answer3.setLocation(col1_x, 340);// x,y
        // 输入框
        jtext_question_answer3.setSize(160, 25);
        jtext_question_answer3.setLocation(col2_x, 340);


        // 保存按钮的字体，大小和位置
        jbtn_save.setFont(font_01);
        jbtn_save.setSize(85, 50);// width,height
        jbtn_save.setLocation(40, 380);

        // 返回按钮的字体，大小和位置
        jbtn_fanhui.setFont(font_01);
        jbtn_fanhui.setSize(85, 50);// width,height
        jbtn_fanhui.setLocation(180, 380);
    }

    public AddQuestionFrame(String templateId,String templateName) {
        this.jtext_selectedTemplateId.setText(templateId);
        this.jtext_selectedTemplateName.setText(templateName);
        // 设置登录窗口大小
        this.setSize(450, 470);// width,height
        // 固定窗口大小，防止因鼠标变动而改变
        this.setResizable(false);
        // 设置窗口标题
        this.setTitle("添加问题窗口");
        // 设置窗口居中显示
        this.setLocationRelativeTo(null);
        // 调用初始化方法
        init();
        myPanl = new LoginPanel();
        // 设置面板布局为空，用自己的，不用默认布局
        myPanl.setLayout(null);
        // 讲所有组件添加到到面板中
        myPanl.add(jlab_selectedTemplateId);
        myPanl.add(jlab_selectedTemplateName);
        myPanl.add(jtext_selectedTemplateId);
        myPanl.add(jtext_selectedTemplateName);
        myPanl.add(jlab_question_name);
        myPanl.add(jtext_question_name);
        myPanl.add(jlab_question_type);
        myPanl.add(jtext_question_type);
        myPanl.add(jlab_question_answer1);
        myPanl.add(jtext_question_answer1);
        myPanl.add(jlab_question_answer2);
        myPanl.add(jtext_question_answer2);
        myPanl.add(jlab_question_answer3);
        myPanl.add(jtext_question_answer3);
        myPanl.add(jbtn_save);
        myPanl.add(jbtn_fanhui);
        // 讲面板加入到窗口上：照片放到框里，框放到墙上
        this.getContentPane().add(myPanl);

        jbtn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String question_name = jtext_question_name.getText();
                String question_type = jtext_question_type.getText();
                String question_answer1 = jtext_question_answer1.getText();
                String question_answer2 = jtext_question_answer2.getText();
                String question_answer3 = jtext_question_answer3.getText();

                if (question_name == null || question_name.equals("")) {
                    JOptionPane.showMessageDialog(null, "问题名称不能为空");
                    // 输入框获取光点
                    jtext_question_name.requestFocus();
                    return;
                }
                if (question_name.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "问题名称不能包括空格");
                    // 输入框获取光点
                    jtext_question_name.requestFocus();
                    return;
                }
                if (question_type == null || question_type.equals("")) {
                    JOptionPane.showMessageDialog(null, "问题类型不能为空");
                    // 输入框获取光点
                    jtext_question_type.requestFocus();
                    return;
                }
                if (question_type.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "问题类型不能包括空格");
                    // 输入框获取光点
                    jtext_question_type.requestFocus();
                    return;
                }
                if (question_answer1 == null || question_answer1.equals("")) {
                    JOptionPane.showMessageDialog(null, "选择一不能为空");
                    // 输入框获取光点
                    jtext_question_answer1.requestFocus();
                    return;
                }
                if (question_answer1.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "选择一不能包括空格");
                    // 输入框获取光点
                    jtext_question_answer1.requestFocus();
                    return;
                }
                if (question_answer2 == null || question_answer2.equals("")) {
                    JOptionPane.showMessageDialog(null, "选择二不能为空");
                    // 输入框获取光点
                    jtext_question_answer2.requestFocus();
                    return;
                }
                if (question_answer2.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "选择二不能包括空格");
                    // 输入框获取光点
                    jtext_question_answer2.requestFocus();
                    return;
                }
                if (question_answer3 == null || question_answer3.equals("")) {
                    JOptionPane.showMessageDialog(null, "选择三不能为空");
                    // 输入框获取光点
                    jtext_question_answer3.requestFocus();
                    return;
                }
                if (question_answer2.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "选择三不能包括空格");
                    // 输入框获取光点
                    jtext_question_answer3.requestFocus();
                    return;
                }

                QuestionController questionController = new QuestionController("Question");
                try {
                    Question question = new Question(question_name,question_type,question_answer1,question_answer2,question_answer3,templateId);
                    boolean result = questionController.addQuestion(question);
                    if (result) {
                        jtext_question_name.setText("");
                        jtext_question_type.setText("");
                        jtext_question_answer1.setText("");
                        jtext_question_answer2.setText("");
                        jtext_question_answer3.setText("");
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

        new AddQuestionFrame(new String(),new String()).setVisible(true);
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