package com.recycleviewdemo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */

public class ClassBean {
    private String className;
    private List<String> classStudents;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getClassStudents() {
        return classStudents;
    }

    public void setClassStudents(List<String> classStudents) {
        this.classStudents = classStudents;
    }
}
