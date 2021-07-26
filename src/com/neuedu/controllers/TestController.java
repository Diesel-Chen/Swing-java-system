package com.neuedu.controllers;

import com.neuedu.entity.Test;
import com.neuedu.factory.ServiceFactory;
import com.neuedu.service.TestService;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 评估管理 controller
 *
 * @author
 * @Date 2021/07/16
 */
public class TestController extends BaseController {
    //MVC模式 调用关系
    //窗口类-->控制器类-->业务类-->工具类
    private TestService testService;

    public TestController(String msg) {
        super(msg);
        testService = (TestService) ServiceFactory.createService(msg);
    }

    /**
     * 注册方法
     *
     * @param Test 用户对象
     * @return boolean  注册成功返回真  失败返回假
     * @throws IOException
     */
    public boolean addTest(Test Test) throws IOException {
        return testService.addTest(Test);
    }

    /**
     * 更新方法
     *
     * @param JTable对象
     * @return boolean  更新成功返回真  失败返回假
     * @throws IOException
     */
    public boolean updateTest(JTable table) throws IOException {
        return testService.updateTest(table);
    }




}