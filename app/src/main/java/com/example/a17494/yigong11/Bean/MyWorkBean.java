package com.example.a17494.yigong11.Bean;

import java.io.Serializable;

public class MyWorkBean implements Serializable {
    /**
     * id : 50
     * studentName : 张赛
     * studentId : 162210702234
     * workId : 5
     * workName : 测试1
     * isfinished : false
     */
        private int id;
        private String studentName;
        private long studentId;
        private int workId;
        private String workName;
        private boolean isfinished;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public long getStudentId() {
            return studentId;
        }

        public void setStudentId(long studentId) {
            this.studentId = studentId;
        }

        public int getWorkId() {
            return workId;
        }

        public void setWorkId(int workId) {
            this.workId = workId;
        }

        public String getWorkName() {
            return workName;
        }

        public void setWorkName(String workName) {
            this.workName = workName;
        }

        public boolean isIsfinished() {
            return isfinished;
        }

        public void setIsfinished(boolean isfinished) {
            this.isfinished = isfinished;
        }

}
