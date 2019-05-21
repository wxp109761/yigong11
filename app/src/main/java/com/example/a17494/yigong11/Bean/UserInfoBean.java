package com.example.a17494.yigong11.Bean;

import java.util.List;

public class UserInfoBean {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":1,"studentId":162210702234,"name":"张赛","sex":"男","phone":15757575757,"college":"计算机学院","major":"计算机科学与技术","date":"2019-03-30","inYear":2016},{"id":2,"studentId":162210702235,"name":"bigsai","sex":"女","phone":15757575757,"college":"计算机学院","major":"软件工程","date":"2019-03-30","inYear":2016},{"id":3,"studentId":162210702236,"name":"杨晟","sex":"男","phone":15757575757,"college":"计算机学院","major":"计算机科学与技术","date":"2019-04-15","inYear":2017},{"id":6,"studentId":162210700000,"name":"bigsai2","sex":"男","phone":15751512222,"college":"计算机学院","major":"软件工程","date":"2019-04-11","inYear":2016},{"id":10,"studentId":162210700002,"name":"bigsai2","sex":"男","phone":15751512222,"college":"外国语学院","major":"外语专业","date":"2019-04-11","inYear":2016}]
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
         * id : 1
         * studentId : 162210702234
         * name : 张赛
         * sex : 男
         * phone : 15757575757
         * college : 计算机学院
         * major : 计算机科学与技术
         * date : 2019-03-30
         * inYear : 2016
         */

        private int id;
        private long studentId;
        private String name;
        private String sex;
        private long phone;
        private String college;
        private String major;
        private String date;
        private int inYear;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getStudentId() {
            return studentId;
        }

        public void setStudentId(long studentId) {
            this.studentId = studentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public long getPhone() {
            return phone;
        }

        public void setPhone(long phone) {
            this.phone = phone;
        }

        public String getCollege() {
            return college;
        }

        public void setCollege(String college) {
            this.college = college;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getInYear() {
            return inYear;
        }

        public void setInYear(int inYear) {
            this.inYear = inYear;
        }
    }
}
