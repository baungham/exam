package org.marker.certificate.bean;


import lombok.Data;

import java.io.Serializable;

/**
 * 成绩
 */
@Data
public class ExamSience implements Serializable{

    /** 考试Id */
    private int examId;
    /** 参与考试人数 */
    private int count;
    /** 年级 */
    private String gradeName;
    /** 班级 */
    private String className;
    /** 学期 */
    private int semesterId;
    /** 学号 */
    private String studentNo;
    /** 学生姓名 */
    private String studentName;
    /** 考试名称 */
    private String name;



    /** 语文 */
    private Integer language;
    /** 语文名次 */
    private String languageOrder;
    /** 数学 */
    private Integer mathematics;
    /** 数学名次 */
    private String mathematicsOrder;
    /** 英语 */
    private Integer english;
    /** 英名 */
    private String englishOrder;
    /** 物理 */
    private Integer physics;
    /** 物名 */
    private String physicsOrder;
    /** 化学 */
    private Integer chemistry;
    /** 化名 */
    private String chemistryOrder;
    /** 生物 */
    private Integer biology;
    /** 生名 */
    private String biologyOrder;
    /** 理6科 */
    private Integer scienceTotalAchievement;
    /** 理6科名 */
    private String scienceTotalAchievementOrder;
    /** 理6科班名 */
    private String scienceClassOrder;// 理科班名次



    /** 政治 */
    private Integer politics;
    /** 政名 */
    private String politicsOrder;
    /** 历史 */
    private Integer history;
    /** 历名 */
    private String historyOrder;
    /** 地理 */
    private Integer geography;
    /** 地名 */
    private String geographyOrder;
    /** 文6科 */
    private Integer literalTotalAchievement;
    /** 文6科名 */
    private String literalTotalAchievementOrder;
    /** 文6科班名 */
    private String literalClassOrder;

    /** 信息 */
    private Integer infomation;
    private String infomationOrder;


    /** 通用 */
    private Integer general;
    private String generalOrder;

    /** 体育 */
    private Integer sports;
    private String sportsOrder;


    /** 音乐 */
    private Integer music;
    private String musicOrder;


    /** 美术 */
    private Integer art;
    private String artOrder;

    /** 心理健康 */
    private Integer psychology;
    private String psychologyOrder;

    /** 9总 */
    private Integer nineTotal;
    /** 9总名 */
    private String nineTotalOrder;


}
