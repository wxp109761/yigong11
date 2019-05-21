package com.example.a17494.yigong11.Bean;

public class LogInBean {


    /**
     * code : 200
     * msg : 成功
     * data : {"role":"student","JSESSIONID":"A20FB65E16964B0568F8734CDB642140"}
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
         * role : student
         * JSESSIONID : A20FB65E16964B0568F8734CDB642140
         */

        private String role;
        private String JSESSIONID;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }
    }
}
