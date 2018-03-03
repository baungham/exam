package org.marker.certificate.service;

import org.marker.certificate.bean.Exam;
import org.marker.certificate.bean.ExamSience;
import org.marker.certificate.bean.Page;

import java.util.List;
import java.util.Map;

/**
 * 成绩管理
 * @author marker
 * @version 1.0
 */
public interface ExamSienceService extends ICommonService<ExamSience> {


    /**
     * 分页查询年级
     * @param currentPageNo
     * @param pageSize
     * @param params
     * @return
     */
    Page<ExamSience> queryByPage(int currentPageNo, int pageSize, Map<String, Object> params);

    /**
     * 获取所有年级
     * @return
     */
    List<ExamSience> getAll();


    /**
     * 获取班级对象
     * @param className
     * @return
     */
    ExamSience get(String className);
}
