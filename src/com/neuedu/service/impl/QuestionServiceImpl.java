package com.neuedu.service.impl;

import com.neuedu.entity.Question;
import com.neuedu.service.QuestionService;
import com.neuedu.utils.CommonUtil;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.*;

public class QuestionServiceImpl implements QuestionService {

    @Override
    public void save(Object obj, String fileName, boolean mode) throws IOException {
        String json = GsonUtil.toJson(obj);
        FileUtil.writeData(json, fileName, mode);
    }

    @Override
    public List<Object> queryAll() throws IOException {
        List<Object> list = FileUtil.readData("Question.txt", Question.class);
        return list;
    }


    @Override
    public boolean addQuestion(Question Question) throws IOException {
        String json_str = GsonUtil.toJson(Question);
        if (json_str.length() > 0)
            FileUtil.writeData(json_str, "Question.txt", true);
        return true;
    }

    //优化后新增方法如下
    @Override
    public boolean delQuestion(List<Question> Questions) throws IOException {
        List<Object> list = FileUtil.readData("Question.txt", Question.class);
        List cpList = new ArrayList();
        //纯赋值
        if (list != null) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Question Question02 = (Question) iterator.next();
                cpList.add(Question02);
            }
        }
        if (list != null) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Question Question02 = (Question) iterator.next();
                System.out.println(Question02);
                for (int j = 0; j < Questions.size(); j++) {
                    if (Questions.get(j).getId().equals(Question02.getId())) {
                        cpList.remove(Question02);
                    }
                }
            }
        }
        if (cpList.size() == 0) {
            FileUtil.ClearFile("Question.txt");
        }
        for (int i = 0; i < cpList.size(); i++) {
            if (i == 0) {
                save(cpList.get(i), "Question.txt", false);
            } else {
                save(cpList.get(i), "Question.txt", true);
            }
        }
        return true;
    }

    @Override
    public boolean updateQuestion(JTable table) throws IOException {
        int r = table.getRowCount();
        TableModel model = table.getModel();
        String str1 = null, str2 = null, str3 = null, str4 = null, str5 = null, str6 = null, str7 = null;
        int n = 0;
        for (int i = 0; i < r; i++) {
            str1 = (String) model.getValueAt(i, 0);
            str2 = (String) model.getValueAt(i, 1);
            str3 = (String) model.getValueAt(i, 2);
            str4 = (String) model.getValueAt(i, 3);
            str5 = (String) model.getValueAt(i, 4);
            str6 = (String) model.getValueAt(i, 5);
            str7 = (String) model.getValueAt(i, 6);
            if ((str1 == null || str1.equals("")) || (str2 == null || str2.equals("")))
                continue;
            n++;
            Question Question = new Question();

            Question.setId(str1);
            Question.setName(str2);
            Question.setType(str3);
            Question.setAnswer1(str4);
            Question.setAnswer2(str5);
            Question.setAnswer3(str6);
            Question.setTemplateId(str7);

            if (n == 1) {
                save(Question, "Question.txt", false);
            } else {
                save(Question, "Question.txt", true);
            }
        }
        return true;
    }

    //多条件组合查询
    @Override
    public List<Question> queryByQuestion(Question QuestionReq) throws IOException {
        List<Object> list = FileUtil.readData("Question.txt", Question.class);
        List<Question> returnList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Question w = (Question) list.get(i);
                if (CommonUtil.JudgeSearchQuestionIsInResList(QuestionReq, w)) {
                    returnList.add(w);
                }
            }
        }
        return returnList;
    }

    //查询模版类别
    @Override
    public Set queryAllType() throws IOException {
        Set resSet = new HashSet();
        List<Object> list = FileUtil.readData("Question.txt", Question.class);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Question w = (Question) list.get(i);
                resSet.add(w.getType());
            }
        }
        return resSet;
    }

    //根据模版id查询问题列表
    @Override
    public List<Question> queryByTemplateId(String templateId) throws IOException {
        List<Object> list = FileUtil.readData("Question.txt", Question.class);
        List<Question> returnList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Question w = (Question) list.get(i);
                if (w.getTemplateId().equals(templateId)){
                    returnList.add(w);
                }
            }
        }
        return returnList;
    }
}