package com.example.a17494.yigong11.Bean;

import java.util.List;

public class UserInfoBean {


    /**
     * code : 200
     * msg : 成功
     * data : [{"college":"计算机学院","date":"2016.09","in_year":2016,"major":"计算机科学与技术","phone":15262910090,"sex":"男","name":"位展朋","student_id":162210700000,"id":1,"worktime":0}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * college : 计算机学院
         * date : 2016.09
         * in_year : 2016
         * major : 计算机科学与技术
         * phone : 15262910090
         * sex : 男
         * name : 位展朋
         * student_id : 162210700000
         * id : 1
         * worktime : 0
         */

        private String college;
        private String date;
        private int in_year;
        private String major;
        private long phone;
        private String sex;
        private String name;
        private long student_id;
        private int id;
        private int worktime;

        public String getCollege() {
            return college;
        }

        public void setCollege(String college) {
            this.college = college;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getIn_year() {
            return in_year;
        }

        public void setIn_year(int in_year) {
            this.in_year = in_year;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public long getPhone() {
            return phone;
        }

        public void setPhone(long phone) {
            this.phone = phone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getStudent_id() {
            return student_id;
        }

        public void setStudent_id(long student_id) {
            this.student_id = student_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getWorktime() {
            return worktime;
        }

        public void setWorktime(int worktime) {
            this.worktime = worktime;
        }
    }
}
