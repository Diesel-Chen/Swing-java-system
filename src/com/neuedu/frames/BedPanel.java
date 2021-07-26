package com.neuedu.frames;

import com.neuedu.controllers.BedController;
import com.neuedu.entity.Bed;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 病患管理面板类
 *
 * @author
 * @date 2021-7-10
 */
public class BedPanel extends JPanel {

    public static String selectedBedName;
    public static String selectedBedSex;

    private static final long serialVersionUID = 1L;
    JTable table = null;
    JScrollPane scroll = null;
    BedController BedController = new BedController("Bed");

    //初始化表格
    void fillTable(Bed Bed) throws IOException {
        List<Bed> Beds = BedController.queryByBed(Bed);
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0); //设置成0行 防止数据累加
        for (Bed p : Beds) {
            Vector v = new Vector();
            v.add(p.getId());
            v.add(p.getCheckInTime());
            v.add(p.getCheckOutTime());
            v.add(p.getStatus());
            v.add(p.getPatientName());
            dtm.addRow(v);
        }
    }




    public BedPanel() {
        this.setLayout(null);
        Font font_01 = new Font("楷体", 1, 28);
        JLabel jla = new JLabel("床位管理");
        jla.setFont(font_01);
        jla.setSize(180, 40);// width, height
        jla.setLocation(350, 30);
        this.add(jla);
        JButton but_cls,  but_ret, but_ref, but_que, but_add;
        but_ret = new JButton("返回");

        but_cls = new JButton("关闭");
        but_ref = new JButton("刷新");
        but_que = new JButton("查询");
        but_add = new JButton("入住");

        JLabel jlab_BedName = new JLabel("病人姓名:");
        //JComboBox<String> jcbx_01 = new JComboBox<String>();
        //JComboBox<String> jcbx_02 = new JComboBox<String>();

        JTextField jtext_BedName = new JTextField();
        jlab_BedName.setSize(80, 40);// width, height
        jlab_BedName.setLocation(230, 100);// x, y
        this.add(jlab_BedName);

        jtext_BedName.setSize(160, 25);// width, height
        jtext_BedName.setLocation(300, 110);
        this.add(jtext_BedName);

        but_que.setSize(70, 25);
        but_que.setLocation(480, 110);
        this.add(but_que);

        but_ret.setSize(70, 30);
        but_ret.setLocation(200, 400);
        this.add(but_ret);



        but_ref.setSize(70, 30);
        but_ref.setLocation(400, 400);
        this.add(but_ref);

        but_cls.setSize(70, 30);
        but_cls.setLocation(600, 400);
        this.add(but_cls);



        JLabel l_name = new JLabel("入住人");
        JTextField tf_name = new JTextField();

        l_name.setSize(80,40);
        l_name.setLocation(150,350);
        this.add(l_name);

        tf_name.setSize(80,25);
        tf_name.setLocation(190,360);
        this.add(tf_name);

        JLabel l_status = new JLabel("状态");
        JTextField tf_status = new JTextField();

        l_status.setSize(80,40);
        l_status.setLocation(280,350);
        this.add(l_status);

        tf_status.setSize(80,25);
        tf_status.setLocation(310,360);
        this.add(tf_status);

        JLabel l_checkinTime = new JLabel("入住时间");
        JTextField tf_checkinTime = new JTextField();

        l_checkinTime.setSize(80,40);
        l_checkinTime.setLocation(400,350);
        this.add(l_checkinTime);

        tf_checkinTime.setSize(100,25);
        tf_checkinTime.setLocation(460,360);
        this.add(tf_checkinTime);

        JLabel l_checkOutTime = new JLabel("结束时间");
        JTextField tf_checkOutTime = new JTextField();

        l_checkOutTime.setSize(80,40);
        l_checkOutTime.setLocation(570,350);
        this.add(l_checkOutTime);

        tf_checkOutTime.setSize(100,25);
        tf_checkOutTime.setLocation(630,360);
        this.add(tf_checkOutTime);

        but_add.setSize(80, 30);//长 高
        but_add.setLocation(740, 360);//列 行
        this.add(but_add);

        this.setSize(850, 500);

        table = new JTable(new DefaultTableModel()) {
            //表格允许被编辑 【修改】
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        JTableHeader tab_header = this.table.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        this.table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        this.table.setRowHeight(20);                                            //设置表格体的行高
        DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();        //获取表格模型
        dtm.setColumnIdentifiers(new Object[]{" Id", "入住开始时间", "入住结束时间", "状态", "姓名"});//修改表头内容

        scroll = new JScrollPane(table);
        //设置滚动条的大小和位置
        scroll.setSize(620, 160);
        scroll.setLocation(150, 150);
        this.add(scroll);

        try {
            fillTable(new Bed());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //添加按钮
        but_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = tf_name.getText();
                String status = tf_status.getText();
                String checkinTime = tf_checkinTime.getText();
                String checkOutTime = tf_checkOutTime.getText();
                Bed bed = new Bed(checkinTime, checkOutTime, status, patientName);
                try {
                    boolean res = BedController.addBed(bed);
                    if(res){
                        tf_name.setText("");
                        tf_status.setText("");
                        tf_checkinTime.setText("");
                        tf_checkOutTime.setText("");
                        JOptionPane.showMessageDialog(null, "添加入住成功");
                        fillTable(new Bed());
                    }else{
                        JOptionPane.showMessageDialog(null, "服务器出现异常");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });



        // 关闭按钮
        but_cls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // 返回按钮
        but_ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = MainFrame.getFrame();
                mainFrame.changeContentPane(MainPanel.getPanel());
            }
        });
        // 刷新按钮
        but_ref.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fillTable(new Bed());
                    jtext_BedName.setText("");

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // 查询按钮
        but_que.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jtext_BedName.getText();
                Bed Bed = new Bed();
                Bed.setPatientName(name);
                try {
                    fillTable(Bed);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制一张背景图片
        String path = "img/bg_1.jpg";
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        g.drawImage(image, 0, 0, this);
    }
}