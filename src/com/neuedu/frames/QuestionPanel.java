package com.neuedu.frames;

import com.neuedu.controllers.QuestionController;
import com.neuedu.entity.Question;

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
public class QuestionPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    JTable table = null;
    JScrollPane scroll = null;
    JComboBox<String> jcbx_01 = null;
    QuestionController QuestionController = new QuestionController("Question");

    //初始化表格
    void fillTable(Question Question) throws IOException {
        List<Question> Questions = QuestionController.queryByQuestion(Question);
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0); //设置成0行 防止数据累加
        for (Question p : Questions) {
            Vector v = new Vector();
            v.add(p.getId());
            v.add(p.getName());
            v.add(p.getType());
            v.add(p.getAnswer1());
            v.add(p.getAnswer2());
            v.add(p.getAnswer3());
            v.add(p.getTemplateId());
            dtm.addRow(v);
        }
    }

    //初始化下拉框
    void fillComboBox() throws IOException {
        Set posts = QuestionController.queryAllType();
        jcbx_01.removeAllItems();
        jcbx_01.addItem("--请选择--");
        for (Object s : posts) {
            jcbx_01.addItem((String) s);
        }

    }

    void updateData(JTable table) {
        // 界面访问控制器类
        QuestionController QuestionController = new QuestionController("Question");
        try {
            QuestionController.updateQuestion(table);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "更新失败");
        }

    }

    void delData(JTable table) {
        // 界面访问控制器类
        QuestionController QuestionController = new QuestionController("Question");
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
        List<Question> Questions = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            if (table.isRowSelected(i)) {
                Question Question = new Question();
                String str = "要删除员工号是" + model.getValueAt(i, 0) + "的员工吗?";
                Question.setId((String) model.getValueAt(i, 0));
                Questions.add(Question);
                int y = JOptionPane.showConfirmDialog(null, str, "注意", 2);
                if (y == 2)
                    return;
            }
        }

        try {
            QuestionController.delQuestion(Questions);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "删除失败");
        }

    }

    public QuestionPanel() {
        this.setLayout(null);
        Font font_01 = new Font("楷体", 1, 28);
        JLabel jla = new JLabel("问题管理");
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
        JLabel jlab_QuestionName = new JLabel("问题名称:");
        jcbx_01 = new JComboBox<String>();

        JTextField jtext_QuestionName = new JTextField();
        jlab_QuestionName.setSize(80, 40);// width, height
        jlab_QuestionName.setLocation(160, 100);// x, y
        this.add(jlab_QuestionName);

        jtext_QuestionName.setSize(100, 25);// width, height
        jtext_QuestionName.setLocation(220, 110);
        this.add(jtext_QuestionName);

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


        this.setSize(850, 500);

        table = new JTable(new DefaultTableModel()) {
            //ID 不可以修改
            boolean[] columnEditables = new boolean[]{
                    false, true, true, true, true, true, false
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
        dtm.setColumnIdentifiers(new Object[]{"问题ID ", "问题名称", "问题类型", "选择答案一", "选择答案二", "选择答案三", "模版ID"});//修改表头内容

        scroll = new JScrollPane(table);
        //设置滚动条的大小和位置
        scroll.setSize(620, 160);
        scroll.setLocation(150, 150);
        this.add(scroll);

        try {
            fillTable(new Question());
            fillComboBox();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
                    fillTable(new Question());
                    fillComboBox();
                    jtext_QuestionName.setText("");
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
                    fillTable(new Question());
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
                    fillTable(new Question());
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
                String name = jtext_QuestionName.getText();
                String type = (String) jcbx_01.getSelectedItem();
                if (type == "--请选择--") {
                    type = "";
                }
                Question Question = new Question();
                Question.setName(name);
                Question.setType(type);

                try {
                    fillTable(Question);
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