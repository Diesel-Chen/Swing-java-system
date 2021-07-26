package com.neuedu.controllers;

import com.neuedu.entity.Template;
import com.neuedu.factory.ServiceFactory;
import com.neuedu.service.TemplateService;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 模版管理 controller
 *
 * @author
 * @Date 2021/07/16
 */
public class TemplateController extends BaseController {
    //MVC模式 调用关系
    //窗口类-->控制器类-->业务类-->工具类
    private TemplateService templateService;

    public TemplateController(String msg) {
        super(msg);
        templateService = (TemplateService) ServiceFactory.createService(msg);
    }

    /**
     * 注册方法
     *
     * @param Template 用户对象
     * @return boolean  注册成功返回真  失败返回假
     * @throws IOException
     */
    public boolean addTemplate(Template Template) throws IOException {
        return templateService.addTemplate(Template);
    }


    /**
     * 删除方法
     *
     * @param List<Template>
     * @return boolean  删除成功返回真  失败返回假
     * @throws IOException
     */
    public boolean delTemplate(List<Template> Templates) throws IOException {
        return templateService.delTemplate(Templates);
    }

    /**
     * 更新方法
     *
     * @param JTable对象
     * @return boolean  更新成功返回真  失败返回假
     * @throws IOException
     */
    public boolean updateTemplate(JTable table) throws IOException {
        return templateService.updateTemplate(table);
    }


    /**
     * 多组合条件查询
     *
     * @param Template
     * @return List<Template>
     * @throws IOException
     */
    public List<Template> queryByTemplate(Template Template) throws IOException {
        return templateService.queryByTemplate(Template);
    }

    /**
     * 查询全部不重复的模版类型
     *
     * @return Set  更新成功返回真  失败返回假
     * @throws IOException
     */
    public Set queryAllType() throws IOException {
        return templateService.queryAllType();
    }
}