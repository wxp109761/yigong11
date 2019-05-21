package com.example.a17494.yigong11.Bean;

public class HourBean {

    /**
     * code : 200
     * msg : 成功
     * data : {"sum1":4,"student_id":162210700000,"isfinished":1}
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
        /**
         * sum1 : 4
         * student_id : 162210700000
         * isfinished : 1
         */

        private int sum1;
        private long student_id;
        private int isfinished;

        public int getSum1() {
            return sum1;
        }

        public void setSum1(int sum1) {
            this.sum1 = sum1;
        }

        public long getStudent_id() {
            return student_id;
        }

        public void setStudent_id(long student_id) {
            this.student_id = student_id;
        }

        public int getIsfinished() {
            return isfinished;
        }

        public void setIsfinished(int isfinished) {
            this.isfinished = isfinished;
        }
    }
}
