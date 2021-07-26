package com.neuedu.controllers;

import com.neuedu.entity.Bed;
import com.neuedu.factory.ServiceFactory;
import com.neuedu.service.BedService;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 床位管理 controller
 *
 * @author
 * @Date 2021/07/16
 */
public class BedController extends BaseController {
    //MVC模式 调用关系
    //窗口类-->控制器类-->业务类-->工具类
    private BedService BedService;

    public BedController(String msg) {
        super(msg);
        BedService = (BedService) ServiceFactory.createService(msg);
    }

    /**
     * 注册方法
     *
     * @param Bed 用户对象
     * @return boolean  注册成功返回真  失败返回假
     * @throws IOException
     */
    public boolean addBed(Bed Bed) throws IOException {
        return BedService.addBed(Bed);
    }


    /**
     * 多组合条件查询
     *
     * @param Bed
     * @return List<Bed>
     * @throws IOException
     */
    public List<Bed> queryByBed(Bed Bed) throws IOException {
        return BedService.queryByBed(Bed);
    }


}