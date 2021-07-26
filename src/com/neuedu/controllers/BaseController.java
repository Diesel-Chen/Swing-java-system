package com.neuedu.controllers;

import java.io.IOException;
import java.util.List;

import com.neuedu.factory.ServiceFactory;
import com.neuedu.service.BaseService;

/**
 * 基础控制器类(设立原因:避免本包中各类的方法冗余)
 *
 * @author
 * @date 2021-7-9
 */
public class BaseController {

    //业务类基类
    private BaseService baseService;

    //利用工厂创建业务类对象
    public BaseController(String msg) {
        baseService = ServiceFactory.createService(msg);
    }

    /**
     * 保存操作
     *
     * @param obj      需要保存的对象
     * @param fileName 文件名
     * @param mode     写入模式  false为覆盖模式,true为追加模式
     * @return 无返回值
     * @throws IOException
     */
    public void save(Object obj, String fileName, boolean mode) throws IOException {
        baseService.save(obj, fileName, mode);
    }

    /**
     * 全部查询操作
     *
     * @return 对象集合
     * @throws IOException
     */
    public List<Object> queryAll() throws IOException {
        return baseService.queryAll();
    }
}
