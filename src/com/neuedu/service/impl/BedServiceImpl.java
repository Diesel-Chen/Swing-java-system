package com.neuedu.service.impl;

import com.neuedu.entity.Bed;
import com.neuedu.service.BedService;
import com.neuedu.utils.CommonUtil;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.*;

public class BedServiceImpl implements BedService {

    @Override
    public void save(Object obj, String fileName, boolean mode) throws IOException {
        String json = GsonUtil.toJson(obj);
        FileUtil.writeData(json, fileName, mode);
    }

    @Override
    public List<Object> queryAll() throws IOException {
        List<Object> list = FileUtil.readData("Bed.txt", Bed.class);
        return list;
    }


    @Override
    public boolean addBed(Bed Bed) throws IOException {
        String json_str = GsonUtil.toJson(Bed);
        if (json_str.length() > 0)
            FileUtil.writeData(json_str, "Bed.txt", true);
        return true;
    }



    //多条件组合查询
    @Override
    public List<Bed> queryByBed(Bed BedReq) throws IOException {
        List<Object> list = FileUtil.readData("Bed.txt", Bed.class);
        List<Bed> returnList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Bed w = (Bed) list.get(i);
                if (CommonUtil.JudgeSearchBedIsInResList(BedReq, w)) {
                    returnList.add(w);
                }
            }
        }
        return returnList;
    }


}