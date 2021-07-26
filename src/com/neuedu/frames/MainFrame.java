package com.neuedu.frames;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * 主菜单窗口类 单例模式
 *
 * @author
 * @date 2021-7-9
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    // 指向自己实例的私有静态引用
    private static MainFrame mainFrame;
    // 菜单条
    JMenuBar menubar;
    JMenu menu1 = null;
    JMenu menu2 = null;
    // 创建二级菜单
    JMenuItem item101 = null;
    JMenuItem item102 = null;
    JMenuItem item103 = null;
    JMenuItem item104 = null;
    JMenuItem item105 = null;
    JMenuItem item106 = null;
//    JMenuItem item107 = null;
    MainPanel mainPanel;

    public void init() {
        menubar = new JMenuBar();
        // 创建一级菜单
        menu1 = new JMenu("一级菜单01");
        menu2 = new JMenu("一级菜单02");
        // 把菜单加到菜单条上
        menubar.add(menu1);
        menubar.add(menu2);
        // 创建二级菜单
        item101 = new JMenuItem("病患管理");
        item102 = new JMenuItem("床位管理");
        item103 = new JMenuItem("回首页");
        item104 = new JMenuItem("模版管理");
        item105 = new JMenuItem("问题管理");
        item106 = new JMenuItem("评估界面");
//        item107 = new JMenuItem("二级菜单07");
        // 把二级菜单添加到 指定的一级菜单上
        menu1.add(item101);
        menu1.addSeparator();// 分割线
        menu1.add(item102);
        menu1.addSeparator();// 分割线
        menu1.add(item103);
        menu2.add(item104);
        menu2.addSeparator();// 分割线
        menu2.add(item105);
        menu2.addSeparator();// 分割线
        menu2.add(item106);
        menu2.addSeparator();// 分割线
//        menu2.add(item107);
        // menu2.addSeparator();//分割线
        // 把菜单条 加到窗口上
        this.setJMenuBar(menubar);
        // 依次把组件放入面板对象
        mainPanel = MainPanel.getPanel();
        mainPanel.setLayout(null);
        // 把面板对象放入窗口中
        this.setContentPane(mainPanel);
        this.setVisible(true);

        //床位管理
        item102.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                changeContentPane(new BedPanel());
            }
        });
        //评估界面管理
        item106.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                changeContentPane(new TestPanel());
            }
        });
        //病患管理
        item101.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                changeContentPane(new PatientPanel());
            }
        });
        // 回首页
        item103.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeContentPane(mainPanel);
            }
        });
        //模版管理
        item104.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                changeContentPane(new TemplatePanel());
            }
        });
        //问题管理
        item105.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                changeContentPane(new QuestionPanel());
            }
        });
    }

    void showPanel() {
        changeContentPane(new PatientPanel());
    }

    MainFrame() {
        this.setTitle("主菜单窗口");
        // 窗口设置大小和位置
        this.setSize(850, 500);// width, height
        // this.setSize(950, 700);// width, height
        // 设置窗口居中显示
        this.setLocationRelativeTo(null);
        // 设置不可改变窗口大小
        this.setResizable(false);
        // 关闭窗口即结束整个应用
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    // 以自己实例为返回值的静态的公有方法
    public static MainFrame getFrame() {
        // 被动创建，在真正需要使用时才去创建
        if (mainFrame == null) {
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }

    // 切换面板的方法
    public void changeContentPane(Container contentPane) {
        this.setContentPane(contentPane);
    }
}

class MainPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    // 指向自己实例的私有静态引用
    private static MainPanel mainPanel;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制一张背景图片
        String path = "img/bg_1.jpg";
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        g.drawImage(image, 0, 0, this);
    }

    // public
    private MainPanel() {
        Font font_01 = new Font("楷体", 1, 28);
        JLabel jla = new JLabel("欢迎使用本系统");
        // 给静态文本设置字体
        jla.setFont(font_01);
        // this 当前类的默认对象
        this.setLayout(null);
        jla.setSize(280, 40);// width, height
        jla.setLocation(100, 30);// 列 行
        this.add(jla);
    }

    // 以自己实例为返回值的静态的公有方法
    public static MainPanel getPanel() {
        // 被动创建，在真正需要使用时才去创建
        if (mainPanel == null) {
            mainPanel = new MainPanel();
        }
        return mainPanel;
    }
}