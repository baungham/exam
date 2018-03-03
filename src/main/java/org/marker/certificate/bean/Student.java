package org.marker.certificate.bean;/**
 * Created by marker on 2018/2/25.
 */

import java.io.Serializable;

/**
 *
 * 学生
 *
 * @author marker
 * @create 2018-02-25 上午9:13
 **/
public class Student implements Serializable{

    /** 学号 */
    private String studentNo;

    private String name;

    private String className;

    private String oldClassName;

    private String gradeName;


    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getOldClassName() {
        return oldClassName;
    }

    public void setOldClassName(String oldClassName) {
        this.oldClassName = oldClassName;
    }
}
