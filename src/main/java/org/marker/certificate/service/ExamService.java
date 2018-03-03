package org.marker.certificate.service;

import org.marker.certificate.bean.Class;
import org.marker.certificate.bean.Exam;
import org.marker.certificate.bean.Page;

import java.util.List;
import java.util.Map;

/**
 * 班级管理
 * @author marker
 * @version 1.0
 */
public interface ExamService extends ICommonService<Exam> {


    /**
     * 分页查询年级
     * @param currentPageNo
     * @param pageSize
     * @param params
     * @return
     */
    Page<Exam> queryByPage(int currentPageNo, int pageSize, Map<String, Object> params);

    /**
     * 获取所有年级
     * @return
     */
    List<Exam> getAll();


    /**
     * 获取班级对象
     * @param className
     * @return
     */
    Exam get(String className);
}
