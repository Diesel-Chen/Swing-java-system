package com.neuedu.frames;

import com.neuedu.controllers.QuestionController;
import com.neuedu.controllers.WorkerController;
import com.neuedu.entity.Question;
import com.neuedu.entity.Worker;
import com.neuedu.exception.IDRepeatException;

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
import java.util.Vector;

/**
 * @author
 * @Date 2021/07/16
 */
public class PreQuestionFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private String selectedTemplateId;
    private String selectedTemplateName;
    private JTable table = null;
    private JScrollPane scroll = null;

    private JPanel myPanl;
    private JLabel jlab_selectedTemplateId = new JLabel("模版id");
    private JLabel jlab_selectedTemplateName = new JLabel("模版名称");

    private QuestionController questionController = new QuestionController("Question");

    // 创建按钮对象
    private JButton jbtn_save = new JButton("保存");
    private JButton jbtn_fanhui = new JButton("返回");
    // 创建字体对象
    private Font font_01 = new Font("楷体", 1, 18);
    // 控件布局用的列坐标==x,行坐标 y == cow;
    private int col1_x = 40, col2_x = 180;
    private JButton but_cls = new JButton("关闭");
    private JButton but_ret = new JButton("返回");
    private JButton but_del = new JButton("删除");
    private JButton but_ref = new JButton("刷新");
    private JButton but_que = new JButton("查询");
    private JButton but_add = new JButton("新建");

    void delData(JTable table) {
        //界面访问控制器类
        QuestionController questionController = new QuestionController("Question");
        int sum = 0;
        int r = table.getRowCount();
        TableModel model = table.getModel();
        //String str1=null,str2=null;
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
        //确认提醒
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            if (table.isRowSelected(i)) {
                Question question = new Question();
                String str = "要删除员工号是" + model.getValueAt(i, 0) + "的员工吗?";
                question.setId((String) model.getValueAt(i, 0));
                questions.add(question);
                int y = JOptionPane.showConfirmDialog(null, str, "注意", 2);
                if (y == 2)
                    return;
            }
        }

        try {
            questionController.delQuestion(questions);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "删除失败");
        }

    }

    void fillTable(Question question) throws IOException {
        List<Question> questions = questionController.queryByTemplateId(selectedTemplateId);
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0); //设置成0行 防止数据累加
        for (Question q : questions) {
            Vector v = new Vector();
            v.add(q.getId());
            v.add(q.getName());
            dtm.addRow(v);
        }
    }

    // 初始化方法
    // 窗口一被new出来就有了各种属性
    private void init() {
        jlab_selectedTemplateId.setFont(font_01);// 黑体
        jlab_selectedTemplateId.setSize(200, 40);// width,height
        jlab_selectedTemplateId.setLocation(col1_x, 50);// x,y

        jlab_selectedTemplateName.setFont(font_01);// 黑体
        jlab_selectedTemplateName.setSize(80, 40);// width,height
        jlab_selectedTemplateName.setLocation(col1_x, 90);// x,y

        but_ret.setSize(70, 30);
        but_ret.setLocation(90, 350);


        but_del.setSize(70, 30);
        but_del.setLocation(200, 350);


        but_ref.setSize(70, 30);
        but_ref.setLocation(320, 350);


        but_cls.setSize(70, 30);
        but_cls.setLocation(450, 350);


        but_add.setSize(80, 30);
        but_add.setLocation(500, 90);

    }


    public PreQuestionFrame(String templateId, String templateName) {
        this.selectedTemplateId = templateId;
        this.selectedTemplateName = templateName;
        this.jlab_selectedTemplateId.setText(templateId);
        this.jlab_selectedTemplateName.setText(templateName);
        // 设置登录窗口大小
        this.setSize(650, 470);// width,height
        // 固定窗口大小，防止因鼠标变动而改变
        this.setResizable(false);
        // 设置窗口标题
        this.setTitle("预览问题窗口");
        // 设置窗口居中显示
        this.setLocationRelativeTo(null);
        // 调用初始化方法
        init();
        myPanl = new JPanel();
        // 设置面板布局为空，用自己的，不用默认布局
        myPanl.setLayout(null);
        // 讲所有组件添加到到面板中
        myPanl.add(jlab_selectedTemplateId);
        myPanl.add(jlab_selectedTemplateName);
        myPanl.add(but_ret);
        myPanl.add(but_del);
        myPanl.add(but_ref);
        myPanl.add(but_cls);
        myPanl.add(but_add);


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
        dtm.setColumnIdentifiers(new Object[]{"问题ID", "问题名称"});//修改表头内容

        scroll = new JScrollPane(table);
        //设置滚动条的大小和位置
        scroll.setSize(550, 160);
        scroll.setLocation(40, 150);
        myPanl.add(scroll);


        // 讲面板加入到窗口上：照片放到框里，框放到墙上
        this.getContentPane().add(myPanl);

        try {
            fillTable(new Question());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //新建按钮
        but_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddQuestionFrame addQuestionFrame = new AddQuestionFrame(selectedTemplateId,selectedTemplateName);
                addQuestionFrame.setVisible(true);

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
                close();
            }
        });

        //刷新按钮
        but_ref.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fillTable(new Question());
                    but_del.setEnabled(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        //删除按钮
        but_del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delData(table);
                try {
                    fillTable(new Question());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {

        new PreQuestionFrame(new String(), new String()).setVisible(true);
    }

    void close() {
        this.setVisible(false);
    }


}