## 1.目录结构

├── README.md
├── data
│   ├── Admin.txt
│   ├── Bed.txt
│   ├── Patient.txt
│   ├── Question.txt
│   ├── Template.txt
│   ├── Test.txt
│   └── Worker.txt
├── img
│   ├── Irno\ Man\ -(2).jpg
│   ├── bg_0.jpg
│   ├── bg_1.jpg
│   ├── bg_2.jpg
│   └── �\214\205�\233�_new02.jpg
├── lib
│   └── gson-2.3.1.jar
└── src
    └── com
        └── neuedu
            ├── controllers
            │   ├── AdminController.java
            │   ├── BaseController.java
            │   ├── BedController.java
            │   ├── PatientController.java
            │   ├── QuestionController.java
            │   ├── TemplateController.java
            │   ├── TestController.java
            │   └── WorkerController.java
            ├── entity
            │   ├── Admin.java
            │   ├── Bed.java
            │   ├── PaperInfo.java
            │   ├── Patient.java
            │   ├── Question.java
            │   ├── Template.java
            │   ├── Test.java
            │   └── Worker.java
            ├── exception
            │   └── IDRepeatException.java
            ├── factory
            │   └── ServiceFactory.java
            ├── frames
            │   ├── AddPatientFrame.java
            │   ├── AddQuestionFrame.java
            │   ├── AddTemplateFrame.java
            │   ├── AddWorkerFrame.java
            │   ├── BedPanel.java
            │   ├── LoginFrame.java
            │   ├── MainFrame.java
            │   ├── MainFrame02.java
            │   ├── OtherPanel.java
            │   ├── PatientPanel.java
            │   ├── PreQuestionFrame.java
            │   ├── QuestionPanel.java
            │   ├── TemplatePanel.java
            │   ├── TestPanel.java
            │   ├── TestProcessFrame.java
            │   └── WorkerPanel.java
            ├── service
            │   ├── AdminService.java
            │   ├── BaseService.java
            │   ├── BedService.java
            │   ├── PatientService.java
            │   ├── QuestionService.java
            │   ├── TemplateService.java
            │   ├── TestService.java
            │   ├── WorkerService.java
            │   └── impl
            │       ├── AdminServiceImpl.java
            │       ├── BedServiceImpl.java
            │       ├── PatientServiceImpl.java
            │       ├── QuestionServiceImpl.java
            │       ├── TemplateServiceImpl.java
            │       ├── TestServiceImpl.java
            │       └── WorkerServiceImpl.java
            └── utils
                ├── CommonUtil.java
                ├── FileUtil.java
                ├── GsonUtil.java
                └── Test.java

## 2.模块介绍

![java swing 管理](https://image.dieselchen.work/uPic/2021/07/26/java%20swing%20%E7%AE%A1%E7%90%86.png)

## 3.运行的图片

### 1.登录界面

![image-20210726172916076](https://image.dieselchen.work/uPic/2021/07/26/image-20210726172916076.png)

### 2.超级管理员界面

![image-20210726172951668](https://image.dieselchen.work/uPic/2021/07/26/image-20210726172951668.png)

### 3.工作人员病患管理界面

![image-20210726173044503](https://image.dieselchen.work/uPic/2021/07/26/image-20210726173044503.png)

### 4.工作人员床位管理界面

![image-20210726173102255](https://image.dieselchen.work/uPic/2021/07/26/image-20210726173102255.png)

### 5.工作人员模版管理界面

![image-20210726173127772](https://image.dieselchen.work/uPic/2021/07/26/image-20210726173127772.png)

### 6.工作人员问题预览界面

![image-20210726173201660](https://image.dieselchen.work/uPic/2021/07/26/image-20210726173201660.png)

### 7.工作人员问题管理界面

![image-20210726173243532](https://image.dieselchen.work/uPic/2021/07/26/image-20210726173243532.png)

### 8.对患者评测界面

![image-20210726173328308](https://image.dieselchen.work/uPic/2021/07/26/image-20210726173328308.png)

![image-20210726173343055](https://image.dieselchen.work/uPic/2021/07/26/image-20210726173343055.png)