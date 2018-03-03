package org.marker.certificate.service;

import org.marker.certificate.bean.Class;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.bean.Student;

import java.util.List;
import java.util.Map;

/**
 * 学生管理
 * @author marker
 * @version 1.0
 */
public interface StudentService extends ICommonService<Student> {


    /**
     * 分页查询
     * @param currentPageNo
     * @param pageSize
     * @param params
     * @return
     */
    Page<Student> queryByPage(int currentPageNo, int pageSize, Map<String, Object> params);

    /**
     * 获取所有
     * @return
     */
    List<Student> getAll();

    Student get(String studentNo);


    /**
     * 更新班级
     * @param studentNo
     * @param className
     */
    void updateClassName(String studentNo, String className);

    ServiceMessage deleteByStudentNo(Object selectId);
}
