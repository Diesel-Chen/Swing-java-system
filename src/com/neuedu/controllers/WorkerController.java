package com.neuedu.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;

import com.neuedu.entity.Worker;
import com.neuedu.factory.ServiceFactory;
import com.neuedu.service.WorkerService;

/**
 * 工作人员管理 controller
 *
 * @author
 * @Date 2021/07/16
 */
public class WorkerController extends BaseController {
    //MVC模式 调用关系
    //窗口类-->控制器类-->业务类-->工具类
    private WorkerService workerService;

    public WorkerController(String msg) {
        super(msg);
        workerService = (WorkerService) ServiceFactory.createService(msg);
    }

    /**
     * 校验(登录)方法
     *
     * @param id       用户编号
     * @param password 用户密码
     * @return boolean  登录成功返回真  失败返回假
     * @throws IOException
     */
    public boolean validate(String id, String password) throws IOException {
        return workerService.validate(id, password);
    }

    /**
     * 用户注册方法
     *
     * @param Worker 工作员对象
     * @return boolean  注册成功返回真  失败返回假
     * @throws IOException
     */
    public boolean addWorker(Worker Worker) throws IOException {
        return workerService.addWorker(Worker);
    }

    /**
     * 删除用户方法
     *
     * @param List<Worker>
     * @return boolean  删除成功返回真  失败返回假
     * @throws IOException
     */
    public boolean delWorker(List<Worker> workers) throws IOException {//.delWorker(table);
        return workerService.delWorker(workers);
    }

    /**
     * 更新用户方法
     *
     * @param JTable对象
     * @return boolean  更新成功返回真  失败返回假
     * @throws IOException
     */
    public boolean updateWorker(JTable table) throws IOException {
        return workerService.updateWorker(table);
    }


    /**
     * 多组合条件查询
     *
     * @param Worker
     * @return List<Worker>
     * @throws IOException
     */
    public List<Worker> queryByWorker(Worker worker) throws IOException {
        return workerService.queryByWorker(worker);
    }

    /**
     * 查询全部不重复的职称类别
     *
     * @return Set
     * @throws IOException
     */
    public Set queryAllPost() throws IOException {
        return workerService.queryAllPost();
    }
}