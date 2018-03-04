package org.marker.certificate.service;


import org.marker.certificate.bean.PrinterQueue;

import java.util.List;

/**
 * 工具业务处理
 * @author marker
 * @version 1.0
 */
public interface ToolsService {

	
	void importExcel();
	
	
	 
	void exportExcel();


    /**
     * 获取打印队列的数据
     * @return
     */
    public PrinterQueue fetch();



    /**
     * 提交到打印队列
     * @param time
     * @param studentNo 学号
     * @param gradeName 年级
     * @param className 班级
     * @param semesterId 学期
     */
    void submitPrintToQueue(String time, String studentNo, String gradeName, String className, int semesterId);


    /**
     * 更新为已打印装填
     * @param id
     */
    void updateComplete(int id);

    List<PrinterQueue> queryCurrentQueue();

    /**
     * 提交汇总打印队列
     * @param studentNo
     * @param gradeName
     * @param className
     */
    void submitPrintCollectToQueue(String studentNo, String gradeName, String className);

    /**
     * 只为学号错误的学生生成表格
     * @return
     */
    List<PrinterQueue> fetchTmpQueue();
}
