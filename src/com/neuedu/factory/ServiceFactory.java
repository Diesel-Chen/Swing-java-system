package com.neuedu.factory;

import com.neuedu.service.BaseService;
import com.neuedu.service.impl.*;

/**
 * 工厂模式--用于产生Service对象
 *
 * @author 高军
 * @date 2021-7-8
 */
public class ServiceFactory {

    public static BaseService createService(String msg) {
        BaseService baseService = null;
        if ("Patient".equals(msg)) {
            baseService = new PatientServiceImpl();
        }
        if ("Admin".equals(msg)) {
            baseService = new AdminServiceImpl();
        }
        if ("Worker".equals(msg)) {
            baseService = new WorkerServiceImpl();
        }
        if ("Template".equals(msg)){
            baseService = new TemplateServiceImpl();
        }
        if ("Question".equals(msg)){
            baseService = new QuestionServiceImpl();
        }
        if ("Test".equals(msg)){
            baseService = new TestServiceImpl();
        }
        if ("Bed".equals(msg)){
            baseService = new BedServiceImpl();
        }
        return baseService;


    }


}
