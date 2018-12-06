package org.marker.certificate.util;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.marker.certificate.bean.*;
import org.marker.certificate.bean.Class;
import org.marker.certificate.service.*;
import org.marker.certificate.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 解析工具类
 * 
 * @author marker
 * @version 1.0
 */
public class AnalysisDataUtil {
	// 日志记录器
	protected static Logger log = LoggerFactory.getLogger(AnalysisDataUtil.class);
	
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");



    static StudentService studentService = new StudentServiceImpl();
	static ClassService classService = new ClassServiceImpl();
	static ExamSienceService examSienceService = new ExamSienceServiceImpl();

	/**
	 * 解析数据
     * @param grade 年级
     * @param semester
     * @param examName 考试名称
     * @param data 数据[]
     */
	public static boolean process(Grade grade, Semester semester, Exam examName, Vector<?> data){

		
		//
		String studentNo   = (String) data.get(0);
		String studentName = (String) data.get(1);
		String className   = (String) data.get(2);

        // TODO 检查是否有班级，没有就写入。
        Class classE = classService.get(className);
        if(classE == null){
            classE = new Class();
            classE.setGradeName(grade.getName());
            classE.setName(className);
            classE.setDesc("");
            classService.save(classE);
        }




        // TODO 如果有学生获取学生的班级与新班级对比是否分班，更新学生为最新的班级（保留原班级）。
        Student student = studentService.get(studentNo);
        if(student == null){
            student = new Student();
            student.setOldClassName("");
            student.setClassName(className);
            student.setGradeName(grade.getName());
            student.setName(studentName);
            student.setStudentNo(studentNo);
            studentService.save(student);
        }else{
            // 判断是否换班级
            if(!student.getClassName().equals(className)){
                studentService.updateClassName(studentNo, className);
            }
        }


        // 写入成绩
        ExamSience entity = new ExamSience();
        entity.setExamId(examName.getId());
        entity.setGradeName(grade.getName());
        entity.setClassName(className);
        entity.setSemesterId(semester.getId());
        entity.setStudentNo(studentNo);
        entity.setStudentName(studentName);
        entity.setCount(examName.getCount());// 参与人数

        entity.setLanguage(getInt(data.get(3)));
        entity.setLanguageOrder(getString(data.get(4)));

        entity.setMathematics(getInt(data.get(5)));
        entity.setMathematicsOrder(getString(data.get(6)));
        entity.setEnglish(getInt(data.get(7)));
        entity.setEnglishOrder(getString(data.get(8)));
        entity.setPhysics(getInt(data.get(9)));
        entity.setPhysicsOrder(getString(data.get(10)));
        entity.setChemistry(getInt(data.get(11)));
        entity.setChemistryOrder(getString(data.get(12)));
        entity.setBiology(getInt(data.get(13)));
        entity.setBiologyOrder(getString(data.get(14)));
        entity.setScienceTotalAchievement(getInt(data.get(15)));
        entity.setScienceTotalAchievementOrder(getString(data.get(16)));
        entity.setScienceClassOrder(getString(data.get(17)));

        entity.setPolitics(getInt(data.get(18)));
        entity.setPoliticsOrder(getString(data.get(19)));
        entity.setHistory(getInt(data.get(20)));
        entity.setHistoryOrder(getString(data.get(21)));
        entity.setGeography(getInt(data.get(22)));
        entity.setGeographyOrder(getString(data.get(23)));
        entity.setLiteralTotalAchievement(getInt(data.get(24)));
        entity.setLiteralTotalAchievementOrder(getString(data.get(25)));
        entity.setLiteralClassOrder(getString(data.get(26)));

        entity.setArt(getInt(data.get(27)));
        entity.setArtOrder(getString(data.get(28)));
        entity.setMusic(getInt(data.get(29)));
        entity.setMusicOrder(getString(data.get(30)));
        entity.setSports(getInt(data.get(31)));
        entity.setSportsOrder(getString(data.get(32)));

        entity.setInfomation(getInt(data.get(33)));
        entity.setInfomationOrder(getString(data.get(34)));
        entity.setPsychology(getInt(data.get(35)));
        entity.setPsychologyOrder(getString(data.get(36)));
        entity.setGeneral(getInt(data.get(37)));
        entity.setGeneralOrder(getString(data.get(38)));

        entity.setNineTotal(getInt(data.get(39)));
        entity.setNineTotalOrder(getString(data.get(40)));



        examSienceService.save(entity);


		return true;
	}


    /**
     * 获取int
     * @param str
     * @return
     */
	private static Integer getInt(Object str){
        String tmp = (String) str;
        if(StringUtils.isBlank(tmp)){
            return 0;
        }
	    return Integer.valueOf(tmp);
    }

    private static String getString(Object str){
        String tmp = (String) str;
        if(StringUtils.isBlank(tmp)){
            return "";
        }
        return String.valueOf(tmp);
    }

	/**
	 * 获取对应城市的ID
	 * @param name
	 * @return
	 */
	public static int getCityId(String name){
		ICityService cityService = new CityServiceImpl();
		List<City> cityList = cityService.queryByProvinceId(1);
		Iterator<City> it = cityList.iterator();
		while(it.hasNext()){
			City c = it.next();
			int is = name.indexOf(c.getName());
			if(is != -1){
				return c.getId(); 
			} 
		}
		return 0; 
	}
	
}


