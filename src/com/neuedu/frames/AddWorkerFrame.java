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

import com.neuedu.controllers.WorkerController;
import com.neuedu.entity.Worker;
import com.neuedu.exception.IDRepeatException;

/**
 * @author
 * @Date 2021/07/16
 */
public class AddWorkerFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private LoginPanel myPanl;
    // 创建静态文本对象
    private JLabel jlab_userName = new JLabel("员工编号");
    private JLabel jlab_password = new JLabel("密码");
    private JLabel jlab_name = new JLabel("姓名");
    private JLabel jlab_birthday = new JLabel("出生日期");
    private JLabel jlab_zhuanchang = new JLabel("专长");
    private JLabel jlab_post = new JLabel("职称");
    private JLabel jlab_telephone = new JLabel("联系电话");
    // 创建账号输入框对象
    private JTextField jtext_userName = new JTextField();
    private JTextField jtext_password = new JTextField();
    private JTextField jtext_name = new JTextField();
    private JTextField jtext_birthday = new JTextField();
    private JTextField jtext_zhuanchang = new JTextField();
    private JTextField jtext_post = new JTextField();
    private JTextField jtext_telephone = new JTextField();
    // 创建按钮对象
    private JButton jbtn_save = new JButton("保存");
    private JButton jbtn_fanhui = new JButton("返回");
    // 创建字体对象
    private Font font_01 = new Font("楷体", 1, 18);
    // 控件布局用的列坐标==x
    private int col1_x = 40, col2_x = 140;
    // 控件布局用的行坐标==y
    private int row1_y = 60, row2_y = 100;

    // 初始化方法
    // 窗口一被new出来就有了各种属性
    private void init() {
        // 给静态文本username设置字体,宽，高
        jlab_userName.setFont(font_01);// 楷体
        jlab_userName.setSize(80, 40);// width,height
        jlab_userName.setLocation(col1_x, row1_y - 50);// x,y
        // 账号输入框大小
        jtext_userName.setSize(160, 25);
        jtext_userName.setLocation(col2_x, row1_y - 40);

        // password
        jlab_password.setFont(font_01);// 黑体
        jlab_password.setSize(80, 40);// width,height
        jlab_password.setLocation(col1_x, row2_y - 50);// x,y
        // 输入框
        jtext_password.setSize(160, 25);
        jtext_password.setLocation(col2_x, row2_y - 40);

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
        jlab_zhuanchang.setFont(font_01);// 黑体
        jlab_zhuanchang.setSize(80, 40);// width,height
        jlab_zhuanchang.setLocation(col1_x, 170);// x,y
        // 输入框
        jtext_zhuanchang.setSize(160, 25);
        jtext_zhuanchang.setLocation(col2_x, 180);

        // pose
        jlab_post.setFont(font_01);// 黑体
        jlab_post.setSize(80, 40);// width,height
        jlab_post.setLocation(col1_x, 210);// x,y
        // 输入框
        jtext_post.setSize(160, 25);
        jtext_post.setLocation(col2_x, 220);

        // telephone
        jlab_telephone.setFont(font_01);// 黑体
        jlab_telephone.setSize(80, 40);// width,height
        jlab_telephone.setLocation(col1_x, 260);// x,y
        // 输入框
        jtext_telephone.setSize(160, 25);
        jtext_telephone.setLocation(col2_x, 270);

        // 保存按钮的字体，大小和位置
        jbtn_save.setFont(font_01);
        jbtn_save.setSize(85, 50);// width,height
        jbtn_save.setLocation(40, 350);

        // 返回按钮的字体，大小和位置
        jbtn_fanhui.setFont(font_01);
        jbtn_fanhui.setSize(85, 50);// width,height
        jbtn_fanhui.setLocation(180, 350);
    }

    public AddWorkerFrame() {
        // 设置登录窗口大小
        this.setSize(450, 450);// width,height
        // 固定窗口大小，防止因鼠标变动而改变
        this.setResizable(false);
        // 设置窗口标题
        this.setTitle("添加工作人员窗口");
        // 设置窗口居中显示
        this.setLocationRelativeTo(null);
        // 调用初始化方法
        init();
        myPanl = new LoginPanel();
        // 设置面板布局为空，用自己的，不用默认布局
        myPanl.setLayout(null);
        // 讲所有组件添加到到面板中
        myPanl.add(jlab_userName);
        myPanl.add(jtext_userName);
        myPanl.add(jlab_password);
        myPanl.add(jtext_password);
        myPanl.add(jlab_name);
        myPanl.add(jtext_name);
        myPanl.add(jlab_birthday);
        myPanl.add(jtext_birthday);
        myPanl.add(jlab_zhuanchang);
        myPanl.add(jtext_zhuanchang);
        myPanl.add(jlab_post);
        myPanl.add(jtext_post);
        myPanl.add(jlab_telephone);
        myPanl.add(jtext_telephone);
        myPanl.add(jbtn_save);
        myPanl.add(jbtn_fanhui);

        // 讲面板加入到窗口上：照片放到框里，框放到墙上
        this.getContentPane().add(myPanl);

        jbtn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = jtext_userName.getText();
                String pwd = jtext_password.getText();
                String name = jtext_name.getText();
                String birdy = jtext_birthday.getText();
                String zc = jtext_zhuanchang.getText();
                String pose = jtext_post.getText();
                String tele = jtext_telephone.getText();

                if (uname == null || uname.equals("")) {
                    JOptionPane.showMessageDialog(null, "用户名不能为空");
                    // 输入框获取光点
                    jtext_userName.requestFocus();
                    return;
                }
                if (uname.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "用户名不能包括空格");
                    // 输入框获取光点
                    jtext_userName.requestFocus();
                    return;
                }

                if (pwd == null || pwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不能为空");
                    // 输入框获取光点
                    jtext_password.requestFocus();
                    return;
                }
                if (pwd.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "密码不能包括空格");
                    // 输入框获取光点
                    jtext_password.requestFocus();
                    return;
                }
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

                if (zc == null || zc.equals("")) {
                    JOptionPane.showMessageDialog(null, "专长不能为空");
                    // 输入框获取光点
                    jtext_zhuanchang.requestFocus();
                    return;
                }
                if (zc.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "专长不能包括空格");
                    // 输入框获取光点
                    jtext_zhuanchang.requestFocus();
                    return;
                }
                if (pose == null || pose.equals("")) {
                    JOptionPane.showMessageDialog(null, "职位不能为空");
                    // 输入框获取光点
                    jtext_post.requestFocus();
                    return;
                }
                if (pose.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "职位不能包括空格");
                    // 输入框获取光点
                    jtext_post.requestFocus();
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

                WorkerController workerController = new WorkerController("Worker");
                try {
                    Worker Worker = new Worker();
                    Worker.setId(uname);
                    Worker.setPassword(pwd);
                    Worker.setName(name);
                    Worker.setBirthday(birdy);
                    Worker.setZhuanchang(zc);
                    Worker.setPost(pose);
                    Worker.setTelephone(tele);
                    boolean result = workerController.addWorker(Worker);
                    if (result) {
                        jtext_userName.setText("");
                        jtext_password.setText("");
                        jtext_name.setText("");
                        jtext_birthday.setText("");
                        jtext_zhuanchang.setText("");
                        jtext_post.setText("");
                        jtext_telephone.setText("");

                        //刷新表格
                        WorkerPanel.getWorkerPanel().fillTable(new Worker());

                        JOptionPane.showMessageDialog(null, "添加成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (IDRepeatException e2) {
                    JOptionPane.showMessageDialog(null, "用户编号重复,注册失败,请更换后再次尝试");
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

        new AddWorkerFrame().setVisible(true);
    }

    void close() {
        this.setVisible(false);
    }

    class LoginPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 绘制一张背景图片
            String path = "img/Irno Man -(2).jpg";
            ImageIcon icon = new ImageIcon(path);
            Image image = icon.getImage();
            g.drawImage(image, 0, 0, this);
        }

    }
}