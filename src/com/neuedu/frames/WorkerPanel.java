package com.neuedu.frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import com.neuedu.controllers.WorkerController;
import com.neuedu.entity.Worker;

/**
 * 工作人员面板类
 *
 * @author
 * @date 2021-7-10
 */
public class WorkerPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    // 指向自己实例的私有静态引用
    private static WorkerPanel workerPanel;

    JTable table = null;
    JScrollPane scroll = null;
    JComboBox<String> jcbx_01 = null;
    WorkerController workerController = new WorkerController("Worker");

    //初始化表格
    void fillTable(Worker worker) throws IOException {
        List<Worker> workers = workerController.queryByWorker(worker);
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0); //设置成0行 防止数据累加
        for (Worker w : workers) {
            Vector v = new Vector();
            v.add(w.getId());
            v.add(w.getPassword());
            v.add(w.getName());
            v.add(w.getBirthday());
            v.add(w.getZhuanchang());
            v.add(w.getPost());
            v.add(w.getTelephone());
            dtm.addRow(v);
        }
    }

    //初始化下拉框
    void fillComboBox() throws IOException {
        Set posts = workerController.queryAllPost();
        jcbx_01.removeAllItems();
        jcbx_01.addItem("--请选择--");
        for (Object s : posts) {
            jcbx_01.addItem((String) s);
        }

    }

    void updateData(JTable table) {
        //界面访问控制器类
        WorkerController workerController = new WorkerController("Worker");
        try {
            workerController.updateWorker(table);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "更新失败");
        }


    }

    void delData(JTable table) {
        //界面访问控制器类
        WorkerController workerController = new WorkerController("Worker");
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
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            if (table.isRowSelected(i)) {
                Worker worker = new Worker();
                String str = "要删除员工号是" + model.getValueAt(i, 0) + "的员工吗?";
                worker.setId((String) model.getValueAt(i, 0));
                workers.add(worker);
                int y = JOptionPane.showConfirmDialog(null, str, "注意", 2);
                if (y == 2)
                    return;
            }
        }

        try {
            workerController.delWorker(workers);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "删除失败");
        }

    }

    //构造器
    public WorkerPanel() {
        this.setLayout(null);
        Font font_01 = new Font("楷体", 1, 28);
        JLabel jla = new JLabel("工作人员管理");
        jla.setFont(font_01);
        jla.setSize(180, 40);//width, height
        jla.setLocation(350, 30);
        this.add(jla);
        JButton but_cls, but_cha, but_del, but_ret, but_ref, but_que, but_add;
        but_ret = new JButton("返回");
        but_cha = new JButton("修改");
        but_del = new JButton("删除");
        but_cls = new JButton("关闭");
        but_ref = new JButton("刷新");
        but_que = new JButton("查询");
        but_add = new JButton("新建");
        JLabel jlab_userId = new JLabel("编号:");
        JLabel jlab_userName = new JLabel("姓名:");

        jcbx_01 = new JComboBox<String>();
        JTextField jtext_userId = new JTextField();
        JTextField jtext_userName = new JTextField();


        jlab_userId.setSize(80, 40);//width, height
        jlab_userId.setLocation(150, 110);//x, y
        this.add(jlab_userId);

        jtext_userId.setSize(120, 25);//width, height
        jtext_userId.setLocation(190, 120);
        this.add(jtext_userId);

        jlab_userName.setSize(80, 25);
        jlab_userName.setLocation(340, 120);
        this.add(jlab_userName);

        jtext_userName.setSize(120, 25);//width, height
        jtext_userName.setLocation(400, 120);
        this.add(jtext_userName);

        but_que.setSize(70, 25);
        but_que.setLocation(700, 120);
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

        but_add.setSize(80, 30);
        but_add.setLocation(150, 80);
        this.add(but_add);

        jcbx_01.setSize(140, 25);
        jcbx_01.setLocation(550, 120);
        this.add(jcbx_01);

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
        dtm.setColumnIdentifiers(new Object[]{"员工编号", "密码", "姓名", "出生日期", "专长", "职称", "联系电话"});//修改表头内容

        scroll = new JScrollPane(table);
        //设置滚动条的大小和位置
        scroll.setSize(620, 160);
        scroll.setLocation(150, 150);
        this.add(scroll);
        //this.setSize(950, 700);
        try {
            fillTable(new Worker());
            fillComboBox();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //新建按钮
        but_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddWorkerFrame addWorkerFrame = new AddWorkerFrame();
                addWorkerFrame.setVisible(true);

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
                MainFrame02 mainFrame02 = MainFrame02.getFrame();
                mainFrame02.changeContentPane(MainPanel02.getPanel());
            }
        });
        //刷新按钮
        but_ref.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fillTable(new Worker());
                    fillComboBox();
                    jtext_userId.setText("");
                    jtext_userName.setText("");
                    jcbx_01.setSelectedItem("--请选择--");
                    but_cha.setEnabled(true);
                    but_del.setEnabled(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        //修改按钮
        but_cha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData(table);
                try {
                    fillTable(new Worker());
                    fillComboBox();
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
                    fillTable(new Worker());
                    fillComboBox();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        //查询按钮
        but_que.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uid = jtext_userId.getText();
                String uname = jtext_userName.getText();
                String post = (String) jcbx_01.getSelectedItem();
                if (post == "--请选择--") {
                    post = "";
                }
                Worker worker = new Worker();
                worker.setId(uid);
                worker.setName(uname);
                worker.setPost(post);
                try {
                    fillTable(worker);
                    but_cha.setEnabled(false);
                    but_del.setEnabled(false);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }

    // 以自己实例为返回值的静态的公有方法
    public static WorkerPanel getWorkerPanel() {
        // 被动创建，在真正需要使用时才去创建
        if (workerPanel == null) {
            workerPanel = new WorkerPanel();
        }
        return workerPanel;
    }
}