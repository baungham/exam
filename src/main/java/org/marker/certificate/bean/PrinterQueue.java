package org.marker.certificate.bean;/**
 * Created by marker on 2018/2/26.
 */

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 打印队列数据
 * @author marker
 * @create 2018-02-26 下午3:56
 **/
@Data
public class PrinterQueue implements Serializable {


    private int id;

    /** 时间（2017-2018） */
    private String time;
    /** 学号 */
    private String studentNo;
    /** 学生姓名 */
    private String studentName;
    /** 年级 */
    private String gradeName;
    /** 班级 */
    private String className;
    /** 学期ID */
    private int semesterId;
    /** 类型 */
    private int type;
    /** 状态 */
    private int status = 0;
    /** 考试分数 */
    private List<ExamSience> examSiences;

    public Object getStatusStr() {
        return this.status == 0?"待打印":"已打印";
    }

    public Object getTypeStr() {
        return  this.type == 1?"通知单":"汇总单";
    }
}
