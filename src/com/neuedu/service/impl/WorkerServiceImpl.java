package com.neuedu.service.impl;

import java.io.IOException;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.neuedu.entity.Worker;
import com.neuedu.exception.IDRepeatException;
import com.neuedu.service.WorkerService;
import com.neuedu.utils.CommonUtil;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

public class WorkerServiceImpl implements WorkerService {
    @Override
    public void save(Object obj, String fileName, boolean mode) throws IOException {
        String json = GsonUtil.toJson(obj);
        FileUtil.writeData(json, fileName, mode);
    }

    @Override
    public List<Object> queryAll() throws IOException {
        List<Object> list = FileUtil.readData("Worker.txt", Worker.class);
        return list;
    }

    //登录
    @Override
    public boolean validate(String id, String password) throws IOException {
        List<Object> list = FileUtil.readData("Worker.txt", Worker.class);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Worker Worker02 = (Worker) list.get(i);
                String uid = Worker02.getId();
                String pwd = Worker02.getPassword();
                if (uid.equals(id) && pwd.equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    //注册
    @Override
    public boolean addWorker(Worker Worker) throws IOException {
        List<Object> list = FileUtil.readData("Worker.txt", Worker.class);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Worker Worker02 = (Worker) list.get(i);
                String uid = Worker02.getId();
                if (uid.equals(Worker.getId())) {
                    throw new IDRepeatException("ID重复请核对");
                }
            }
        }
        String json_str = GsonUtil.toJson(Worker);
        if (json_str.length() > 0)
            FileUtil.writeData(json_str, "Worker.txt", true);
        return true;
    }

    //删除worker
    @Override
    public boolean delWorker(List<Worker> workers) throws IOException {
        List<Object> list = FileUtil.readData("Worker.txt", Worker.class);
        List cpList = new ArrayList();
        //纯赋值
        if (list != null) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Worker Worker02 = (Worker) iterator.next();
                cpList.add(Worker02);
            }
        }
        if (list != null) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Worker Worker02 = (Worker) iterator.next();
                System.out.println(Worker02);
                for (int j = 0; j < workers.size(); j++) {
                    if (workers.get(j).getId().equals(Worker02.getId())) {
                        cpList.remove(Worker02);
                    }
                }
            }
        }
        if (cpList.size() == 0) {
            FileUtil.ClearFile("Worker.txt");
        }
        for (int i = 0; i < cpList.size(); i++) {
            if (i == 0) {
                save(cpList.get(i), "Worker.txt", false);
            } else {
                save(cpList.get(i), "Worker.txt", true);
            }
        }

        return true;
    }

    //更新全表格
    @Override
    public boolean updateWorker(JTable table) throws IOException {
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
            Worker Worker = new Worker();
            Worker.setId(str1);
            Worker.setPassword(str2);
            Worker.setName(str3);
            Worker.setBirthday(str4);
            Worker.setZhuanchang(str5);
            Worker.setPost(str6);
            Worker.setTelephone(str7);
            if (n == 1) {
                save(Worker, "Worker.txt", false);
            } else {
                save(Worker, "Worker.txt", true);
            }
        }
        return true;
    }

    //多条件组合查询
    @Override
    public List<Worker> queryByWorker(Worker workerReq) throws IOException {
        List<Object> list = FileUtil.readData("Worker.txt", Worker.class);
        List<Worker> returnList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Worker w = (Worker) list.get(i);
                if (CommonUtil.JudgeSearchWorkerIsInResList(workerReq, w)) {
                    returnList.add(w);
                }
            }
        }
        return returnList;
    }

    //查询职称
    @Override
    public Set queryAllPost() throws IOException {
        Set resSet = new HashSet();
        List<Object> list = FileUtil.readData("Worker.txt", Worker.class);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Worker w = (Worker) list.get(i);
                resSet.add(w.getPost());
            }
        }
        return resSet;
    }
}