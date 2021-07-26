package com.neuedu.frames;

import com.neuedu.controllers.QuestionController;
import com.neuedu.controllers.TestController;
import com.neuedu.entity.PaperInfo;
import com.neuedu.entity.Question;
import com.neuedu.entity.Test;
import com.neuedu.utils.CommonUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @Date 2021/07/16
 */
public class TestProcessFrame extends JFrame {


    public TestProcessFrame() {


        // 设置登录窗口大小
        this.setSize(500, 600);// width,height
        // 固定窗口大小，防止因鼠标变动而改变
        this.setResizable(false);
        // 设置窗口标题
        this.setTitle("评测界面窗口");
        // 设置窗口居中显示
        this.setLocationRelativeTo(null);
        // 调用初始化方法


        QuestionController questionController = new QuestionController("Question");
        TestController testController = new TestController("Test");

        List<Question> questions = null;
        ArrayList<PaperInfo> list = new ArrayList<>();
        System.out.printf("操作者%s\n", LoginFrame.Operator);
        System.out.printf("病患名%s\n", PatientPanel.selectedPatientName);
        System.out.printf("病患性别%s\n", PatientPanel.selectedPatientSex);
        System.out.printf("模版名称%s\n", TestPanel.selectedTemplateName);
        System.out.printf("模版类别%s\n", TestPanel.selectedTemplateType);
        try {
            questions = questionController.queryByTemplateId(TestPanel.selectedTemplateId);
        } catch (IOException e) {
            e.printStackTrace();
        }


        int cnt = questions.size();


        Container container = this.getContentPane();

        container.setLayout(new GridLayout(cnt + 1, 1));

        for (int i = 0; i < questions.size(); i++) {
            JLabel jLabel1 = new JLabel(questions.get(i).getName());
            JRadioButton bt1 = new JRadioButton(questions.get(i).getAnswer1());
            JRadioButton bt2 = new JRadioButton(questions.get(i).getAnswer2());
            JRadioButton bt3 = new JRadioButton(questions.get(i).getAnswer3());

            ButtonGroup buttonGroup1 = new ButtonGroup();
            buttonGroup1.add(bt1);
            buttonGroup1.add(bt2);
            buttonGroup1.add(bt3);

            PaperInfo paperInfo = new PaperInfo(jLabel1, bt1, bt2, bt3);
            list.add(paperInfo);

            container.add(jLabel1);
            container.add(bt1);
            container.add(bt2);
            container.add(bt3);
        }
        JButton btn_add = new JButton("提交");
        JButton btn_res = new JButton("返回");

        container.add(btn_add);
        container.add(btn_res);


        //新建按钮
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int totalScore = 0;
                for (int i = 0; i < list.size(); i++) {

                    if (list.get(i).getBt1().isSelected()) {
                        totalScore += 5;
                        continue;
                    }
                    if (list.get(i).getBt2().isSelected()) {
                        totalScore += 3;
                        continue;
                    }
                    if (list.get(i).getBt3().isSelected()) {
                        totalScore += 1;
                        continue;
                    }

                    JOptionPane.showMessageDialog(null, "你的第"+(i+1)+"题还没做。");
                    return;
                }



                Test test = new Test(PatientPanel.selectedPatientName,
                        PatientPanel.selectedPatientSex,
                        TestPanel.selectedTemplateName,
                        TestPanel.selectedTemplateType,
                        CommonUtil.GetCurrentTime(),
                        LoginFrame.Operator,
                        totalScore,
                        "");
                try {
                    boolean res = testController.addTest(test);
                    if (res) {
                        JOptionPane.showMessageDialog(null, "评测完毕，获得"+totalScore+"分,请到评估界面进行查看详情");
                        close();
                    } else {
                        JOptionPane.showMessageDialog(null, "服务器异常，稍后再试");
                        close();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });


        //返回按钮
        btn_res.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });


    }

    public static void main(String[] args) {

        new TestProcessFrame().setVisible(true);
    }

    void close() {
        this.setVisible(false);
    }


}