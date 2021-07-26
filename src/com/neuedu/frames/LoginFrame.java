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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.neuedu.controllers.WorkerController;
import com.neuedu.controllers.AdminController;
import com.neuedu.entity.Admin;
import com.neuedu.entity.Worker;
import com.neuedu.exception.IDRepeatException;

/**
 * 登录界面
 *
 * @author
 * @date 2021-7-12
 */
public class LoginFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    public static String Operator = null;
    private LoginPanel myPanl;
    //创建静态文本对象
    private JLabel jlab_userName = new JLabel("账号:");
    private JLabel jlab_password = new JLabel("密码:");
    //创建账号输入框对象
    private JTextField jtext_userName = new JTextField();
    //创建密码输入框对象
    private JPasswordField jtext_password = new JPasswordField();
    //创建字体对象
    private Font font_01 = new Font("楷体", 1, 18);
    //创建按钮对象
    private JButton jbtn_register = new JButton("工作人员注册");
    private JButton jbtn_login = new JButton("工作人员登录");
    private JButton jbtn_register02 = new JButton("超级管理员注册");
    private JButton jbtn_login02 = new JButton("超级管理员登录");
    //控件布局用的列坐标==x
    private int col1_x = 90, col2_x = 140;
    //控件布局用的行坐标==y
    private int row1_y = 60, row2_y = 100, row3_y = 205;


    //初始化方法
    //窗口一被new出来就有了各种属性
    private void init() {
        //第一步，给静态文本设置字体
        jlab_userName.setFont(font_01);//楷体
        //第二步，设置静态文本大小,宽，高
        jlab_userName.setSize(80, 40);//width,height
        jlab_userName.setLocation(col1_x, row1_y);//x,y

        //第一步，给静态文本设置字体
        jlab_password.setFont(font_01);//黑体
        //第二步，设置静态文本大小,宽，高
        jlab_password.setSize(80, 40);//width,height
        jlab_password.setLocation(col1_x, row2_y);//x,y

        //账号输入框大小
        jtext_userName.setSize(160, 25);
        jtext_userName.setLocation(col2_x, row1_y + 10);

        //密码输入框
        jtext_password.setSize(160, 25);
        jtext_password.setLocation(col2_x, row2_y + 10);

        //工作人员注册按钮的字体，大小和位置
        //jbtn_register.setFont(font_02);
        jbtn_register.setSize(125, 50);//width,height
        jbtn_register.setLocation(105, row3_y);


        //工作人员登录按钮的设置字体，大小和位置
        //jbtn_login.setFont(font_02);
        jbtn_login.setSize(125, 50);
        jbtn_login.setLocation(105, row3_y + 80);

        //超级管理员注册按钮的字体，大小和位置
        //jbtn_register.setFont(font_02);
        jbtn_register02.setSize(125, 50);//width,height
        jbtn_register02.setLocation(285, row3_y);

        //超级管理员登录按钮的设置字体，大小和位置
        //jbtn_login.setFont(font_02);
        jbtn_login02.setSize(125, 50);
        jbtn_login02.setLocation(285, row3_y + 0 + 80);


    }

    public LoginFrame() {
        //设置登录窗口大小
        this.setSize(850, 480);//width,height
        //固定窗口大小，防止因鼠标变动而改变
        this.setResizable(false);
        //设置窗口标题
        this.setTitle("登录窗口");
        //设置窗口居中显示
        this.setLocationRelativeTo(null);
        //调用初始化方法
        init();
        myPanl = new LoginPanel();
        //设置面板布局为空，用自己的，不用默认布局
        myPanl.setLayout(null);
        //讲所有组件添加到到面板中
        myPanl.add(jlab_userName);
        myPanl.add(jlab_password);
        myPanl.add(jtext_userName);
        myPanl.add(jtext_password);
        myPanl.add(jbtn_register);
        myPanl.add(jbtn_login);
        myPanl.add(jbtn_login02);
        myPanl.add(jbtn_register02);

        //讲面板加入到窗口上：照片放到框里，框放到墙上
        this.getContentPane().add(myPanl);
        //给按钮添加动作监听器
        //匿名对象

        //工作人员注册按钮
        jbtn_register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = jtext_userName.getText();
                String pwd = new String(jtext_password.getPassword());
                if (uname == null || uname.equals("")) {
                    JOptionPane.showMessageDialog(null, "用户名不能为空");
                    //输入框获取光点
                    jtext_userName.requestFocus();
                    return;
                }
                if (uname.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "用户名不能包括空格");
                    //输入框获取光点
                    jtext_userName.requestFocus();
                    return;
                }
                if (pwd == null || pwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不能为空");
                    //输入框获取光点
                    jtext_password.requestFocus();
                    return;
                }
                if (pwd.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "密码不能包括空格");
                    //输入框获取光点
                    jtext_password.requestFocus();
                    return;
                }
                WorkerController workerController = new WorkerController("Worker");
                try {
                    Worker worker = new Worker();
                    worker.setId(uname);
                    worker.setPassword(pwd);
                    boolean result = workerController.addWorker(worker);
                    if (result) {
                        jtext_userName.setText("");
                        jtext_password.setText("");
                        JOptionPane.showMessageDialog(null, "注册成功\n\n您的用户名是:" + uname + "   密码是:" + pwd);
                    } else {
                        JOptionPane.showMessageDialog(null, "注册失败");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (IDRepeatException e2) {
                    JOptionPane.showMessageDialog(null, "ID号重复,注册失败,请更换后再次尝试");
                }
            }
        });

        //工作人员登录页面
        jbtn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = jtext_userName.getText();
                String pwd = new String(jtext_password.getPassword());
                if (uname == null || uname.equals("")) {//输入为空格或者没输入
                    JOptionPane.showMessageDialog(null, "用户名不能为空");
                    //输入框获取光点
                    jtext_userName.requestFocus();
                    return;//作用：停止方法，否则会依次执行语句
                }
                if (uname.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "用户名不能包括空格");
                    //输入框获取光点
                    jtext_userName.requestFocus();
                    return;
                }
                if (pwd == null || pwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不能为空");
                    //输入框获取光点
                    jtext_password.requestFocus();
                    return;
                }
                if (pwd.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "密码不能包括空格");
                    //输入框获取光点
                    jtext_password.requestFocus();
                    return;
                }

                //
                WorkerController workerController = new WorkerController("Worker");
                try {
                    boolean result = workerController.validate(uname, pwd);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "登录成功");
                        MainFrame mainFrame = MainFrame.getFrame();
                        mainFrame.setVisible(true);
                        Operator = uname;
                        close();
                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        //超级管理人员注册按钮
        jbtn_register02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = jtext_userName.getText();
                String pwd = new String(jtext_password.getPassword());
                if (uname == null || uname.equals("")) {
                    JOptionPane.showMessageDialog(null, "用户名不能为空");
                    //输入框获取光点
                    jtext_userName.requestFocus();
                    return;
                }
                if (uname.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "用户名不能包括空格");
                    //输入框获取光点
                    jtext_userName.requestFocus();
                    return;
                }
                if (pwd == null || pwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不能为空");
                    //输入框获取光点
                    jtext_password.requestFocus();
                    return;
                }
                if (pwd.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "密码不能包括空格");
                    //输入框获取光点
                    jtext_password.requestFocus();
                    return;
                }
                //
                AdminController adminController = new AdminController("Admin");
                try {
                    Admin Admin = new Admin();
                    Admin.setId(uname);
                    Admin.setPassword(pwd);
                    boolean result = adminController.addAdmin(Admin);
                    if (result) {
                        jtext_userName.setText("");
                        jtext_password.setText("");
                        JOptionPane.showMessageDialog(null, "注册成功\n\n您的用户名是:" + uname + "   密码是:" + pwd);
                    } else {
                        JOptionPane.showMessageDialog(null, "注册失败");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (IDRepeatException e2) {
                    JOptionPane.showMessageDialog(null, "ID号重复,注册失败,请更换后再次尝试");
                }
            }
        });

        //超级管理员登录页面
        jbtn_login02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = jtext_userName.getText();
                String pwd = new String(jtext_password.getPassword());
                if (uname == null || uname.equals("")) {//输入为空格或者没输入
                    JOptionPane.showMessageDialog(null, "用户名不能为空");
                    //输入框获取光点
                    jtext_userName.requestFocus();
                    return;//作用：停止方法，否则会依次执行语句
                }
                if (uname.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "用户名不能包括空格");
                    //输入框获取光点
                    jtext_userName.requestFocus();
                    return;
                }
                if (pwd == null || pwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不能为空");
                    //输入框获取光点
                    jtext_password.requestFocus();
                    return;
                }
                if (pwd.indexOf(" ") >= 0) {
                    JOptionPane.showMessageDialog(null, "密码不能包括空格");
                    //输入框获取光点
                    jtext_password.requestFocus();
                    return;
                }

                AdminController adminController = new AdminController("Admin");
                try {
                    boolean result = adminController.validate(uname, pwd);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "登录成功");
                        MainFrame02 mainFrame02 = MainFrame02.getFrame();
                        mainFrame02.setVisible(true);
                        close();
                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    void close() {
        this.setVisible(false);
    }

    public static void main(String[] args) {

        new LoginFrame().setVisible(true);
    }
}

/**
 * 绘制背景图片
 *
 * @author
 * @date 2021-7-12
 */
class LoginPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制一张背景图片
        String path = "img/Irno Man -(2).jpg";
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        g.drawImage(image, 0, 0, this);
    }

    public void dispose() {
        // TODO Auto-generated method stub

    }
}
