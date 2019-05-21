package com.example.a17494.yigong11.Bean;

import java.io.Serializable;
import java.util.List;

public class AllWorkBean {

    /**
     * code : 200
     * msg : 成功
     * data : {"ison":[{"id":5,"name":"测试1","publisherId":15751512041,"publishTime":"2019-04-08 15:33:27","workCampus":"东校区","workDepartment":"图书馆","workAddr":"图书馆二楼","workTip":"附加","workHour":2,"needNum":1,"attendNum":0,"startTime":"2019-04-18 15:33:25"},{"id":8,"name":"整理实验室垃圾","publisherId":15751512041,"publishTime":"2019-04-08 15:45:43","workCampus":"东校区","workDepartment":"计算机学院四楼实验室","workAddr":"计算机学院","workTip":"附加","workHour":1.5,"needNum":12,"attendNum":0,"startTime":"2019-04-20 23:44:00"},{"id":16,"name":"测试1","publisherId":15751512041,"publishTime":"2019-04-12 17:55:37","workCampus":"东校区","workDepartment":"图书馆","workAddr":"图书馆二楼","workTip":"附加","workHour":0.5,"needNum":1,"attendNum":0,"startTime":"2019-04-30 00:00:00"}],"overtime":[{"id":12,"name":"这次整理桌子","publisherId":15751512041,"publishTime":"2019-04-10 16:50:17","workCampus":"东校区","workDepartment":"图书馆","workAddr":"图书馆二楼","workTip":"附加","workHour":2,"needNum":4,"attendNum":0,"startTime":"2019-04-10 23:00:00"}]}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<IsonBean> ison;
        private List<OvertimeBean> overtime;

        public List<IsonBean> getIson() {
            return ison;
        }

        public void setIson(List<IsonBean> ison) {
            this.ison = ison;
        }

        public List<OvertimeBean> getOvertime() {
            return overtime;
        }

        public void setOvertime(List<OvertimeBean> overtime) {
            this.overtime = overtime;
        }

        public static class IsonBean implements Serializable {
            /**
             * id : 5
             * name : 测试1
             * publisherId : 15751512041
             * publishTime : 2019-04-08 15:33:27
             * workCampus : 东校区
             * workDepartment : 图书馆
             * workAddr : 图书馆二楼
             * workTip : 附加
             * workHour : 2.0
             * needNum : 1
             * attendNum : 0
             * startTime : 2019-04-18 15:33:25
             */

            private int id;
            private String name;
            private long publisherId;
            private String publishTime;
            private String workCampus;
            private String workDepartment;
            private String workAddr;
            private String workTip;
            private double workHour;
            private int needNum;
            private int attendNum;
            private String startTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getPublisherId() {
                return publisherId;
            }

            public void setPublisherId(long publisherId) {
                this.publisherId = publisherId;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getWorkCampus() {
                return workCampus;
            }

            public void setWorkCampus(String workCampus) {
                this.workCampus = workCampus;
            }

            public String getWorkDepartment() {
                return workDepartment;
            }

            public void setWorkDepartment(String workDepartment) {
                this.workDepartment = workDepartment;
            }

            public String getWorkAddr() {
                return workAddr;
            }

            public void setWorkAddr(String workAddr) {
                this.workAddr = workAddr;
            }

            public String getWorkTip() {
                return workTip;
            }

            public void setWorkTip(String workTip) {
                this.workTip = workTip;
            }

            public double getWorkHour() {
                return workHour;
            }

            public void setWorkHour(double workHour) {
                this.workHour = workHour;
            }

            public int getNeedNum() {
                return needNum;
            }

            public void setNeedNum(int needNum) {
                this.needNum = needNum;
            }

            public int getAttendNum() {
                return attendNum;
            }

            public void setAttendNum(int attendNum) {
                this.attendNum = attendNum;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }
        }

        public static class OvertimeBean {
            /**
             * id : 12
             * name : 这次整理桌子
             * publisherId : 15751512041
             * publishTime : 2019-04-10 16:50:17
             * workCampus : 东校区
             * workDepartment : 图书馆
             * workAddr : 图书馆二楼
             * workTip : 附加
             * workHour : 2.0
             * needNum : 4
             * attendNum : 0
             * startTime : 2019-04-10 23:00:00
             */

            private int id;
            private String name;
            private long publisherId;
            private String publishTime;
            private String workCampus;
            private String workDepartment;
            private String workAddr;
            private String workTip;
            private double workHour;
            private int needNum;
            private int attendNum;
            private String startTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getPublisherId() {
                return publisherId;
            }

            public void setPublisherId(long publisherId) {
                this.publisherId = publisherId;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getWorkCampus() {
                return workCampus;
            }

            public void setWorkCampus(String workCampus) {
                this.workCampus = workCampus;
            }

            public String getWorkDepartment() {
                return workDepartment;
            }

            public void setWorkDepartment(String workDepartment) {
                this.workDepartment = workDepartment;
            }

            public String getWorkAddr() {
                return workAddr;
            }

            public void setWorkAddr(String workAddr) {
                this.workAddr = workAddr;
            }

            public String getWorkTip() {
                return workTip;
            }

            public void setWorkTip(String workTip) {
                this.workTip = workTip;
            }

            public double getWorkHour() {
                return workHour;
            }

            public void setWorkHour(double workHour) {
                this.workHour = workHour;
            }

            public int getNeedNum() {
                return needNum;
            }

            public void setNeedNum(int needNum) {
                this.needNum = needNum;
            }

            public int getAttendNum() {
                return attendNum;
            }

            public void setAttendNum(int attendNum) {
                this.attendNum = attendNum;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }
        }
    }
}
