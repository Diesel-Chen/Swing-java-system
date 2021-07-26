package com.neuedu.frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import com.neuedu.controllers.PatientController;
import com.neuedu.entity.Patient;
import com.neuedu.entity.Worker;

/**
 * 病患管理面板类
 *
 * @author
 * @date 2021-7-10
 */
public class PatientPanel extends JPanel {

    public static String selectedPatientName;
    public static String selectedPatientSex;

    private static final long serialVersionUID = 1L;
    JTable table = null;
    JScrollPane scroll = null;
    PatientController patientController = new PatientController("Patient");

    //初始化表格
    void fillTable(Patient patient) throws IOException {
        List<Patient> patients = patientController.queryByPatient(patient);
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0); //设置成0行 防止数据累加
        for (Patient p : patients) {
            Vector v = new Vector();
            v.add(p.getName());
            v.add(p.getBirthday());
            v.add(p.getSex());
            v.add(p.getIdentity());
            v.add(p.getTelephone());
            v.add(p.getFamily());
            v.add(p.getFamilytel());
            dtm.addRow(v);
        }
    }

    void updateData(JTable table) {
        // 界面访问控制器类
        PatientController patientController = new PatientController("Patient");
        try {
            patientController.updatePatient(table);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "更新失败");
        }

    }

    void delData(JTable table) {
        // 界面访问控制器类
        PatientController patientController = new PatientController("Patient");
        int sum = 0;
        int r = table.getRowCount();
        TableModel model = table.getModel();
        // String str1=null,str2=null;
        for (int i = 0; i < r; i++) {
            if (table.isRowSelected(i))
                sum++;
        }
        if (sum == 0) {
            JOptionPane.showMessageDialog(null, "选中行不能为0");
            return;
        }
        if (sum > 1) {
            JOptionPane.showMessageDialog(null, "你正在进行批量删除…………");
        }
        // 确认提醒
        List<Patient> patients = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            if (table.isRowSelected(i)) {
                Patient patient = new Patient();
                String str = "要删除员工号是" + model.getValueAt(i, 0) + "的员工吗?";
                patient.setName((String) model.getValueAt(i, 0));
                patients.add(patient);
                int y = JOptionPane.showConfirmDialog(null, str, "注意", 2);
                if (y == 2)
                    return;
            }
        }

        try {
            patientController.delPatient(patients);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "删除失败");
        }

    }

    public PatientPanel() {
        this.setLayout(null);
        Font font_01 = new Font("楷体", 1, 28);
        JLabel jla = new JLabel("病患管理");
        jla.setFont(font_01);
        jla.setSize(180, 40);// width, height
        jla.setLocation(350, 30);
        this.add(jla);
        JButton but_cls, but_cha, but_del, but_ret, but_ref, but_que, but_add,but_test;
        but_ret = new JButton("返回");
        but_cha = new JButton("修改");
        but_del = new JButton("删除");
        but_cls = new JButton("关闭");
        but_ref = new JButton("刷新");
        but_que = new JButton("查询");
        but_add = new JButton("添加");
        but_test = new JButton("评估");
        JLabel jlab_PatientName = new JLabel("姓名:");
        //JComboBox<String> jcbx_01 = new JComboBox<String>();
        //JComboBox<String> jcbx_02 = new JComboBox<String>();

        JTextField jtext_PatientName = new JTextField();
        jlab_PatientName.setSize(80, 40);// width, height
        jlab_PatientName.setLocation(260, 100);// x, y
        this.add(jlab_PatientName);

        jtext_PatientName.setSize(160, 25);// width, height
        jtext_PatientName.setLocation(300, 110);
        this.add(jtext_PatientName);

        but_que.setSize(70, 25);
        but_que.setLocation(480, 110);
        this.add(but_que);

        but_ret.setSize(70, 30);
        but_ret.setLocation(200, 350);
        this.add(but_ret);

        but_cha.setSize(70, 30);
        but_cha.setLocation(300, 350);
        this.add(but_cha);

        but_del.setSize(70, 30);
        but_del.setLocation(400, 350);
        this.add(but_del);

        but_ref.setSize(70, 30);
        but_ref.setLocation(500, 350);
        this.add(but_ref);

        but_cls.setSize(70, 30);
        but_cls.setLocation(600, 350);
        this.add(but_cls);

        but_add.setSize(80, 30);//长 高
        but_add.setLocation(690, 120);//列 行
        this.add(but_add);

        but_test.setSize(80, 30);//长 高
        but_test.setLocation(600, 120);//列 行
        this.add(but_test);

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
        dtm.setColumnIdentifiers(new Object[]{" 姓名", "出生日期", "性别", "身份证号", "联系电话", "紧急联系人", "紧急联系人电话"});//修改表头内容

        scroll = new JScrollPane(table);
        //设置滚动条的大小和位置
        scroll.setSize(620, 160);
        scroll.setLocation(150, 150);
        this.add(scroll);

        try {
            fillTable(new Patient());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //添加按钮
        but_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPatientFrame addPatientFrame = new AddPatientFrame();
                addPatientFrame.setVisible(true);
            }
        });

        //评估按钮
        but_test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getRowCount();
                TableModel model = table.getModel();

                int sum = 0;
                for (int i = 0; i < r; i++) {
                    if (table.isRowSelected(i))
                        sum++;
                }
                if (sum == 0) {
                    JOptionPane.showMessageDialog(null, "选中行不能为0");
                    return;
                }
                if (sum > 1) {
                    JOptionPane.showMessageDialog(null, "只能选中1行");
                    return;
                }
                for (int i = 0; i < r; i++) {
                    if (table.isRowSelected(i)) {
                        String str = "你将要为姓名为" + model.getValueAt(i, 0) + "的病人进行评估吗？";
                        selectedPatientName = (String) model.getValueAt(i, 0);
                        selectedPatientSex = (String) model.getValueAt(i, 2);
                        int y = JOptionPane.showConfirmDialog(null, str, "注意", 2);
                        if (y == 2)
                            return;
                    }
                }

                MainFrame.getFrame().changeContentPane(new TestPanel());
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
                    fillTable(new Patient());
                    jtext_PatientName.setText("");
                    but_cha.setEnabled(true);
                    but_del.setEnabled(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        // 修改按钮
        but_cha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData(table);
                try {
                    fillTable(new Patient());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        // 删除按钮
        but_del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delData(table);
                try {
                    fillTable(new Patient());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        // 查询按钮
        but_que.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jtext_PatientName.getText();
                Patient patient = new Patient();
                patient.setName(name);
                try {
                    fillTable(patient);
                    but_cha.setEnabled(false);
                    but_del.setEnabled(false);
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