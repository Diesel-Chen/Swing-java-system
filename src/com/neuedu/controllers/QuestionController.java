package com.neuedu.controllers;

import com.neuedu.entity.Question;
import com.neuedu.factory.ServiceFactory;
import com.neuedu.service.QuestionService;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 问题管理 controller
 *
 * @author
 * @Date 2021/07/16
 */
public class QuestionController extends BaseController {
    //MVC模式 调用关系
    //窗口类-->控制器类-->业务类-->工具类
    private QuestionService questionService;

    public QuestionController(String msg) {
        super(msg);
        questionService = (QuestionService) ServiceFactory.createService(msg);
    }

    /**
     * 注册方法
     *
     * @param Question 用户对象
     * @return boolean  注册成功返回真  失败返回假
     * @throws IOException
     */
    public boolean addQuestion(Question Question) throws IOException {
        return questionService.addQuestion(Question);
    }


    /**
     * 删除方法
     *
     * @param List<Question>
     * @return boolean  删除成功返回真  失败返回假
     * @throws IOException
     */
    public boolean delQuestion(List<Question> Questions) throws IOException {
        return questionService.delQuestion(Questions);
    }

    /**
     * 更新方法
     *
     * @param JTable对象
     * @return boolean  更新成功返回真  失败返回假
     * @throws IOException
     */
    public boolean updateQuestion(JTable table) throws IOException {
        return questionService.updateQuestion(table);
    }


    /**
     * 多组合条件查询
     *
     * @param Question
     * @return List<Question>
     * @throws IOException
     */
    public List<Question> queryByQuestion(Question Question) throws IOException {
        return questionService.queryByQuestion(Question);
    }

    /**
     * 查询全部不重复的问题类型
     *
     * @return Set  更新成功返回真  失败返回假
     * @throws IOException
     */
    public Set queryAllType() throws IOException {
        return questionService.queryAllType();
    }

    /**
     * 根据模版ID查询问题列表
     *
     * @param String 模版id
     * @return Set  更新成功返回真  失败返回假
     * @throws IOException
     */
    public List<Question> queryByTemplateId(String templateId) throws IOException {
        return questionService.queryByTemplateId(templateId);
    }
}