package com.neuedu.frames;

import com.neuedu.controllers.PatientController;
import com.neuedu.controllers.QuestionController;
import com.neuedu.controllers.TemplateController;
import com.neuedu.controllers.TestController;
import com.neuedu.entity.Patient;
import com.neuedu.entity.Question;
import com.neuedu.entity.Template;
import com.neuedu.entity.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * 问题管理面板类
 *
 * @author
 * @date 2021-7-10
 */
public class TestPanel extends JPanel {

    public static String selectedTemplateId;
    public static String selectedTemplateType;
    public static String selectedTemplateName;

    private static final long serialVersionUID = 1L;
    JTable table = null;
    JScrollPane scroll = null;
    JComboBox jcbx_01 = null;
    TestController testController = new TestController("Test");
    TemplateController templateController = new TemplateController("Template");

    void fillTable() throws IOException {
        List<Object> tests = testController.queryAll();
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        Test test = null;
        dtm.setRowCount(0); //设置成0行 防止数据累加
        for (Object o : tests) {
            Vector v = new Vector();
            test = (Test) o;
            v.add(test.getPatientName());
            v.add(test.getPatientSex());
            v.add(test.getTemplateName());
            v.add(test.getTemplateType());
            v.add(test.getCreateTime());
            v.add(test.getOperator());
            v.add(test.getScore());
            v.add(test.getSuggestion());
            dtm.addRow(v);
        }
    }

    //初始化下拉框
    void fillComboBox() throws IOException {
        List<Object> templates = templateController.queryAll();

        jcbx_01.removeAllItems();
        jcbx_01.addItem(new Template("--请选择--"));
        for (Object t : templates) {
            jcbx_01.addItem(t);
        }

    }

    void updateData(JTable table) {
        // 界面访问控制器类
        try {
            testController.updateTest(table);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "更新失败");
        }

    }

    public TestPanel() {
        this.setLayout(null);
        Font font_01 = new Font("楷体", 1, 28);
        JLabel jla = new JLabel("评估管理");
        jla.setFont(font_01);
        jla.setSize(180, 40);// width, height
        jla.setLocation(350, 30);
        this.add(jla);
        JButton but_cls, but_ret, but_ref, but_add,but_update;
        but_ret = new JButton("返回");


        but_cls = new JButton("关闭");
        but_ref = new JButton("刷新");
        but_update = new JButton("修改");
        but_add = new JButton("开始评估");

        jcbx_01 = new JComboBox<>();


        jcbx_01.setSize(140, 25);
        jcbx_01.setLocation(550, 110);
        this.add(jcbx_01);

        but_add.setSize(120, 35);
        but_add.setLocation(130, 100);
        this.add(but_add);

        but_ret.setSize(70, 30);
        but_ret.setLocation(200, 350);
        this.add(but_ret);

        but_update.setSize(70, 30);
        but_update.setLocation(320, 350);
        this.add(but_update);

        but_ref.setSize(70, 30);
        but_ref.setLocation(440, 350);
        this.add(but_ref);

        but_cls.setSize(70, 30);
        but_cls.setLocation(600, 350);
        this.add(but_cls);


        this.setSize(850, 500);

        table = new JTable(new DefaultTableModel()) {
            //ID 不可以修改
            boolean[] columnEditables = new boolean[]{
                    false, false, false, false, false, false, false, true
            };

            //表格允许被编辑 【修改】
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        };
        JTableHeader tab_header = this.table.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        this.table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        this.table.setRowHeight(20);                                            //设置表格体的行高
        DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();        //获取表格模型
        dtm.setColumnIdentifiers(new Object[]{"姓名 ", "性别", "模版名称", "模版类型", "时间", "评估人", "分数", "建议"});//修改表头内容

        scroll = new JScrollPane(table);
        //设置滚动条的大小和位置
        scroll.setSize(680, 160);
        scroll.setLocation(120, 150);
        this.add(scroll);

        try {
            fillTable();
            fillComboBox();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //开始评估
        but_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Template template = (Template) jcbx_01.getSelectedItem();
                if (template.getName().equals("--请选择--")) {
                    JOptionPane.showMessageDialog(null, "请选择模版");
                    return;
                }

                if (PatientPanel.selectedPatientName == null) {
                    JOptionPane.showMessageDialog(null, "请选择相应的病人，或者从病患管理入口进");
                    return;
                }


                selectedTemplateType = template.getType();
                selectedTemplateName = template.getName();
                selectedTemplateId = template.getId();
                QuestionController questionController = new QuestionController("Question");
                try {
                    List<Question> questions = questionController.queryByTemplateId(TestPanel.selectedTemplateId);
                    if(questions.size()==0){
                        JOptionPane.showMessageDialog(null, "该模版低下没有试题，请先添加试题再进行评估");
                        return;
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                TestProcessFrame testProcessFrame = new TestProcessFrame();
                testProcessFrame.setVisible(true);
            }
        });


        //关闭按钮
        but_cls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //返回按钮
        but_ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = MainFrame.getFrame();
                mainFrame.changeContentPane(MainPanel.getPanel());
            }
        });

        //刷新按钮
        but_ref.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fillTable();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        // 修改按钮
        but_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData(table);
                try {
                    fillTable();
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