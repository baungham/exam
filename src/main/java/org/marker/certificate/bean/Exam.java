package org.marker.certificate.bean;
/**
 * Created by marker on 2018/2/25.
 */

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 考试
 *
 * @author marker
 * @create 2018-02-25 上午10:10
 **/
@Data
public class Exam implements Serializable {


    /** ID */
    private int id;

    /** 年级 */
    private String gradeName;

    /** 学期 */
    private int semesterId;

    /** 考试名称 */
    private String name;

    /** 参与人数 */
    private int count;

    /** 排序字段 */
    private int sortNum;

    /**
     * 创建时间
     */
    private Date createTime;


}
