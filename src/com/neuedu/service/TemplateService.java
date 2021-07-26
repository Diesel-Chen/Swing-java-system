package com.neuedu.service;

import com.neuedu.entity.Template;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 模版管理业务接口
 *
 * @author 高军
 * @date 2021-7-9
 */
public interface TemplateService extends BaseService {


    /**
     * 注册方法
     *
     * @param Template 对象
     * @return boolean  注册成功返回真  失败返回假
     * @throws IOException
     */
    public boolean addTemplate(Template Template) throws IOException;


    /**
     * 删除方法
     *
     * @param List<Template>
     * @return boolean  删除成功返回真  失败返回假
     * @throws IOException
     */
    public boolean delTemplate(List<Template> Templates) throws IOException;

    /**
     * 更新方法
     *
     * @param JTable对象
     * @return boolean  更新成功返回真  失败返回假
     * @throws IOException
     */
    public boolean updateTemplate(JTable table) throws IOException;

    /**
     * 多组合条件查询
     *
     * @param Template
     * @return List<Template>
     * @throws IOException
     */
    public List<Template> queryByTemplate(Template Template) throws IOException;

    /**
     * 查询全部不重复的职称类别
     *
     * @return Set
     * @throws IOException
     */
    public Set queryAllType() throws  IOException;
}