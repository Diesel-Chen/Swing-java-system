package com.neuedu.frames;

import com.neuedu.controllers.TemplateController;
import com.neuedu.entity.Template;
import com.neuedu.entity.Worker;

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
 * 模版管理面板类
 *
 * @author
 * @date 2021-7-10
 */
public class TemplatePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    JTable table = null;
    JScrollPane scroll = null;
    JComboBox<String> jcbx_01 = null;
    TemplateController templateController = new TemplateController("Template");

    //初始化表格
    void fillTable(Template Template) throws IOException {
        List<Template> Templates = templateController.queryByTemplate(Template);
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0); //设置成0行 防止数据累加
        for (Template p : Templates) {
            Vector v = new Vector();
            v.add(p.getId());
            v.add(p.getName());
            v.add(p.getType());

            dtm.addRow(v);
        }
    }

    //初始化下拉框
    void fillComboBox() throws IOException {
        Set posts = templateController.queryAllType();
        jcbx_01.removeAllItems();
        jcbx_01.addItem("--请选择--");
        for (Object s : posts) {
            jcbx_01.addItem((String) s);
        }

    }

    void updateData(JTable table) {
        // 界面访问控制器类
        TemplateController templateController = new TemplateController("Template");
        try {
            templateController.updateTemplate(table);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "更新失败");
        }

    }

    void delData(JTable table) {
        // 界面访问控制器类
        TemplateController TemplateController = new TemplateController("Template");
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
        List<Template> templates = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            if (table.isRowSelected(i)) {
                Template template = new Template();
                String str = "要删除员工号是" + model.getValueAt(i, 0) + "的员工吗?";
                template.setId((String) model.getValueAt(i, 0));
                templates.add(template);
                int y = JOptionPane.showConfirmDialog(null, str, "注意", 2);
                if (y == 2)
                    return;
            }
        }

        try {
            TemplateController.delTemplate(templates);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "删除失败");
        }

    }

    public TemplatePanel() {
        this.setLayout(null);
        Font font_01 = new Font("楷体", 1, 28);
        JLabel jla = new JLabel("模版管理");
        jla.setFont(font_01);
        jla.setSize(180, 40);// width, height
        jla.setLocation(350, 30);
        this.add(jla);
        JButton but_cls, but_cha, but_del, but_ret, but_ref, but_que, but_add, but_pre, but_add2;
        but_ret = new JButton("返回");
        but_cha = new JButton("修改");
        but_del = new JButton("删除");
        but_cls = new JButton("关闭");
        but_ref = new JButton("刷新");
        but_que = new JButton("查询");
        but_add = new JButton("添加模版");
        but_add2 = new JButton("添加问题");
        but_pre = new JButton("预览");
        JLabel jlab_TemplateName = new JLabel("模版名称:");
        jcbx_01 = new JComboBox<String>();

        JTextField jtext_TemplateName = new JTextField();
        jlab_TemplateName.setSize(80, 40);// width, height
        jlab_TemplateName.setLocation(160, 100);// x, y
        this.add(jlab_TemplateName);

        jtext_TemplateName.setSize(100, 25);// width, height
        jtext_TemplateName.setLocation(220, 110);
        this.add(jtext_TemplateName);

        jcbx_01.setSize(140, 25);
        jcbx_01.setLocation(350, 110);
        this.add(jcbx_01);

        but_que.setSize(70, 25);
        but_que.setLocation(520, 110);
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

        but_add2.setSize(80, 30);//长 高
        but_add2.setLocation(690, 80);//列 行
        this.add(but_add2);

        but_pre.setSize(80, 30);//长 高
        but_pre.setLocation(600, 120);//列 行
        this.add(but_pre);

        this.setSize(850, 500);

        table = new JTable(new DefaultTableModel()) {
            //ID 不可以修改
            boolean[] columnEditables = new boolean[]{
                    false, true, true
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
        dtm.setColumnIdentifiers(new Object[]{"模版ID ", "模版名称", "模版类型"});//修改表头内容

        scroll = new JScrollPane(table);
        //设置滚动条的大小和位置
        scroll.setSize(620, 160);
        scroll.setLocation(150, 150);
        this.add(scroll);

        try {
            fillTable(new Template());
            fillComboBox();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //添加模版按钮
        but_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTemplateFrame addTemplateFrame = new AddTemplateFrame();
                addTemplateFrame.setVisible(true);
            }
        });
        //添加问题按钮
        but_add2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getRowCount();
                TableModel model = table.getModel();
                String selectedTemplateId = null;
                String selectedTemplateName = null;
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
                        String str = "你将要为id为" + model.getValueAt(i, 0) + "的模版添加问题?";
                        selectedTemplateId = (String) model.getValueAt(i, 0);
                        selectedTemplateName = (String) model.getValueAt(i, 1);
                        int y = JOptionPane.showConfirmDialog(null, str, "注意", 2);
                        if (y == 2)
                            return;
                    }
                }

                AddQuestionFrame addQuestionFrame = new AddQuestionFrame(selectedTemplateId,selectedTemplateName);
                addQuestionFrame.setVisible(true);
            }
        });

        //预览
        but_pre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getRowCount();
                TableModel model = table.getModel();
                String selectedTemplateId = null;
                String selectedTemplateName = null;
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
                        String str = "你将预览模版ID为" + model.getValueAt(i, 0) + "的所有问题列表";
                        selectedTemplateId = (String) model.getValueAt(i, 0);
                        selectedTemplateName = (String) model.getValueAt(i, 1);
                        int y = JOptionPane.showConfirmDialog(null, str, "注意", 2);
                        if (y == 2)
                            return;
                    }
                }

                PreQuestionFrame preQuestionFrame = new PreQuestionFrame(selectedTemplateId,selectedTemplateName);
                preQuestionFrame.setVisible(true);
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
                    fillTable(new Template());
                    fillComboBox();
                    jtext_TemplateName.setText("");
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
                    fillTable(new Template());
                    fillComboBox();
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
                    fillTable(new Template());
                    fillComboBox();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        // 查询按钮
        but_que.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jtext_TemplateName.getText();
                String type = (String) jcbx_01.getSelectedItem();
                if (type == "--请选择--") {
                    type = "";
                }
                Template template = new Template();
                template.setName(name);
                template.setType(type);

                try {
                    fillTable(template);
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