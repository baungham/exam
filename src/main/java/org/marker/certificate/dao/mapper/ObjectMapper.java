package org.marker.certificate.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.marker.certificate.bean.*;
import org.marker.certificate.bean.Class;
import org.marker.certificate.dao.RowMapper;



/**
 * 
 * @author marker
 * @version 1.0
 */
public final class ObjectMapper {

	
	// User
	public static final class MapperUser implements RowMapper<User> {
		 
		@Override
		public User fetch(ResultSet rs, int index) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setPass(rs.getString("pass"));
			user.setPhone(rs.getString("phone"));
			user.setDesc(rs.getString("desc"));
			user.setStatus(rs.getBoolean("status"));// 状态
			user.setTime(rs.getTimestamp("time"));// 登录时间
			return user;
		}
	}
	
	// Enterprise
	public static final class MapperEnterprise implements RowMapper<Enterprise> {  
		@Override
		public Enterprise fetch(ResultSet rs, int index) throws SQLException {
			Enterprise enter = new Enterprise();
			enter.setId(rs.getInt("id"));
			enter.setName(rs.getString("name"));
			enter.setDesc(rs.getString("desc"));
			enter.setAddress(rs.getString("address"));
			enter.setProvinceId(rs.getInt("province_id"));
			enter.setCityId(rs.getInt("city_id"));
			enter.setTime(rs.getTimestamp("time")); 
			enter.setCityName(rs.getString("cname"));// 城市名称
			enter.setProvinceName(rs.getString("pname"));// 省份名称
			return enter;
		}
	}

	// Grade
	public static final class MapperGrade implements RowMapper<Grade> {
		@Override
		public Grade fetch(ResultSet rs, int index) throws SQLException {
			Grade enter = new Grade();
			enter.setName(rs.getString("name"));
			enter.setDesc(rs.getString("desc"));
			enter.setCreateTime(rs.getDate("createTime"));
			return enter;
		}
	}
 
	
	
	// City
	public static final class MapperCity implements RowMapper<City> {  
		@Override
		public City fetch(ResultSet rs, int index) throws SQLException {
			City o = new City(); 
			o.setId(rs.getInt("id"));
			o.setName(rs.getString("name"));
			o.setPid(rs.getInt("province_id"));
			return o;
		}
	}
	
	// Certificate for 证书管理
	public static final class MapperCertificate implements RowMapper<Certificate>{ 
		@Override
		public Certificate fetch(ResultSet rs, int index) throws SQLException { 
			Certificate c = new Certificate();
			c.setId(rs.getInt("id"));
			c.setCode(rs.getString("code"));
			c.setSendTime(rs.getTimestamp("stime"));
			c.setEffectiveTime(rs.getTimestamp("etime"));
			c.setVerifyMode(rs.getString("verifymode"));// 检验方式
			c.setEnterpriseName(rs.getString("ename"));// 企业
			c.setProvince(rs.getString("pname"));// 省份
			c.setCity(rs.getString("cname"));// 城市
			c.setProduct(rs.getString("product"));// 产品
			c.setPrinter(rs.getInt("printer"));// 打印状态
			return c;
		}
		
	}
	// Certificate for 查看证书
	public static final class MapperCertificateForFind implements RowMapper<Certificate>{ 
		@Override
		public Certificate fetch(ResultSet rs, int index) throws SQLException { 
			Certificate c = new Certificate();
			c.setId(rs.getInt("id"));
			c.setCode(rs.getString("code"));
			c.setSendTime(rs.getTimestamp("stime"));
			c.setEffectiveTime(rs.getTimestamp("etime"));
			c.setVerifyMode(rs.getString("verifymode"));// 检验方式
		 	c.setEnterpriseId(rs.getInt("enterprise_id"));// 企业ID 
			c.setProduct(rs.getString("product"));// 产品
			return c;
		}
		
	}
	// Certificate for 打印队列
	public static final class MapperCertificateForQueue implements RowMapper<Certificate>{ 
		@Override
		public Certificate fetch(ResultSet rs, int index) throws SQLException { 
			Certificate c = new Certificate();
			c.setId(rs.getInt("id"));
			c.setQid(rs.getInt("qid"));// 队列ID
			c.setCode(rs.getString("code"));
			c.setSendTime(rs.getTimestamp("stime"));
			c.setEffectiveTime(rs.getTimestamp("etime"));
			c.setVerifyMode(rs.getString("verifymode"));// 检验方式
		 	c.setEnterpriseId(rs.getInt("enterprise_id"));// 企业ID 
			c.setProduct(rs.getString("product"));// 产品
			return c;
		}
		
	}
	
	
	// Certificate unit
	public static final class MapperUnit implements RowMapper<Unit>{ 
		@Override
		public Unit fetch(ResultSet rs, int index) throws SQLException { 
			Unit u = new Unit();
			u.setId(rs.getInt("id"));
			u.setCid(rs.getInt("cid"));
			u.setDesc(rs.getString("desc"));
			return u;
		}
		
	}
	
	
	// Certificate unit food
	public static final class MapperFood implements RowMapper<Food>{  
		public Food fetch(ResultSet rs, int index) throws SQLException { 
			Food f = new Food();
			f.setId(rs.getInt("id"));
			f.setUid(rs.getInt("uid"));
			f.setName(rs.getString("name"));// 食品品种
			f.setUsed(rs.getString("used"));//使用标准
			return f;
		}
		
	}
	
	// PrinterLog 日志
	public static final class MapperPrinterLog implements RowMapper<PrinterLog>{ 
		public PrinterLog fetch(ResultSet rs, int index) throws SQLException {
			PrinterLog log = new PrinterLog();
			log.setCode(rs.getString("code"));
			log.setEnterpriseName(rs.getString("enterprise_name"));
			log.setCity(rs.getString("city"));
			log.setProvince(rs.getString("province"));
			log.setAddress(rs.getString("address"));
			log.setTime(rs.getTimestamp("time"));
			log.setProduct(rs.getString("product"));
			log.setVerifyMode(rs.getString("verifyMode"));
			log.setStatus(rs.getString("status"));// 状态 
			return log;
		} 
	}
	
	// for id int
	public static final class MapperListInteger implements RowMapper<Integer>{

		@Override
		public Integer fetch(ResultSet rs, int index) throws SQLException {
			return rs.getInt("id");
		}
		
	}
	
	// for 统计
	public static final class MapperTimeCount implements RowMapper<TimeCount>{ 
		public TimeCount fetch(ResultSet rs, int index) throws SQLException { 
			TimeCount c = new TimeCount();
			c.setStime(rs.getTimestamp("stime"));
			c.setEtime(rs.getTimestamp("etime"));
			c.setYear(rs.getInt("year"));
			c.setCount(rs.getInt("count")); 
			return c;
		}


	}

	// class
    public static class MapperClass implements RowMapper<Class> {
        @Override
        public Class fetch(ResultSet rs, int index) throws SQLException {
            Class enter = new Class();
            enter.setName(rs.getString("name"));
            enter.setGradeName(rs.getString("gradeName"));
            enter.setDesc(rs.getString("desc"));
            enter.setCreateTime(rs.getDate("createTime"));
            return enter;
        }
    }

    // Student
    public static class MapperStudent implements RowMapper<Student> {
        @Override
        public Student fetch(ResultSet rs, int index) throws SQLException {
            Student enter = new Student();
            enter.setStudentNo(rs.getString("studentNo"));
            enter.setName(rs.getString("name"));
            enter.setGradeName(rs.getString("gradeName"));
            enter.setClassName(rs.getString("className"));
            return enter;
        }
    }

    public static class MapperExam implements RowMapper<Exam> {
        @Override
        public Exam fetch(ResultSet rs, int index) throws SQLException {
            Exam enter = new Exam();
            enter.setId(rs.getInt("id"));
            enter.setSemesterId(rs.getInt("semesterId"));
            enter.setName(rs.getString("name"));
            enter.setSortNum(rs.getInt("sortNum"));
            enter.setGradeName(rs.getString("gradeName"));
            enter.setCreateTime(rs.getDate("createTime"));
            return enter;
        }
    }


    // 成绩
    public static class MapperExamSience implements RowMapper<ExamSience> {
        @Override
        public ExamSience fetch(ResultSet rs, int index) throws SQLException {
            ExamSience enter = new ExamSience();
            try {
                enter .setName(rs.getString("name"));
            } catch (Exception e){}

            enter          .setExamId(rs.getInt("examId")); /** 考试Id */
            enter       .setGradeName(rs.getString("gradeName"));  /** 年级 */
            enter       .setClassName(rs.getString("className"));  /** 班级 */
            enter      .setSemesterId(rs.getInt("semesterId")); /** 学期 */
            enter       .setStudentNo(rs.getString("studentNo")); /** 学号 */
            enter     .setStudentName(rs.getString("studentName"));
            enter           .setCount(rs.getInt("count"));// 人数

            enter        .setLanguage(rs.getInt("language")); /** 语文 */
            enter   .setLanguageOrder(rs.getString("languageOrder"));  /** 语文名次 */
            enter     .setMathematics(rs.getInt("mathematics")); /** 数学 */
            enter.setMathematicsOrder(rs.getString("mathematicsOrder")); /** 数学名次 */
            enter         .setEnglish(rs.getInt("english"));/** 英语 */
            enter.    setEnglishOrder(rs.getString("englishOrder"));  /** 英名 */
            enter         .setPhysics(rs.getInt("physics"));  /** 物理 */
            enter    .setPhysicsOrder(rs.getString("physicsOrder"));  /** 物名 */
            enter       .setChemistry(rs.getInt("chemistry"));  /** 化学 */
            enter  .setChemistryOrder(rs.getString("chemistryOrder"));  /** 化名 */
            enter         .setBiology(rs.getInt("biology")); /** 生物 */
            enter    .setBiologyOrder(rs.getString("biologyOrder"));  /** 生名 */
            enter.setScienceTotalAchievement(rs.getInt("scienceTotalAchievement"));  /** 理6科 */
            enter.setScienceTotalAchievementOrder(rs.getString("scienceTotalAchievementOrder"));  /** 理6科名 */
            enter.setScienceClassOrder(rs.getString("scienceClassOrder"));/** 理6科班名 */
            enter.setPolitics(rs.getInt("politics"));  /** 政治 */
            enter.setPoliticsOrder(rs.getString("politicsOrder"));  /** 政名 */
            enter.setHistory(rs.getInt("history")); /** 历史 */
            enter.setHistoryOrder(rs.getString("historyOrder"));  /** 历名 */
            enter.setGeography(rs.getInt("geography"));  /** 地理 */
            enter.setGeographyOrder(rs.getString("geographyOrder"));  /** 地名 */
            enter.setLiteralTotalAchievement(rs.getInt("literalTotalAchievement")); /** 文6科 */
            enter.setLiteralTotalAchievementOrder(rs.getString("literalTotalAchievementOrder"));  /** 文6科名 */
            enter.setLiteralClassOrder(rs.getString("literalClassOrder"));  /** 文6科班名 */
            enter.setInfomation(rs.getInt("infomation"));   /** 信息 */
            enter.setInfomationOrder(rs.getString("infomationOrder"));
            enter.setGeneral(rs.getInt("general"));  /** 通用 */
            enter.setGeneralOrder(rs.getString("generalOrder"));
            enter.setSports(rs.getInt("sports"));  /** 体育 */
            enter.setSportsOrder(rs.getString("sportsOrder"));
            enter.setMusic(rs.getInt("music"));  /** 音乐 */
            enter.setMusicOrder(rs.getString("musicOrder"));
            enter.setArt(rs.getInt("art")); /** 美术 */
            enter.setArtOrder(rs.getString("artOrder"));
            enter.setPsychology(rs.getInt("psychology"));  /** 心理健康 */
            enter.setPsychologyOrder(rs.getString("psychologyOrder"));

            enter.setNineTotal(rs.getInt("nineTotal"));
            enter.setNineTotalOrder(rs.getString("nineTotalOrder"));
            return enter;
        }
    }

    // PrinterQueue
    public static class MapperPrinterQueue implements RowMapper<PrinterQueue> {
		@Override
		public PrinterQueue fetch(ResultSet rs, int index) throws SQLException {
			PrinterQueue enter = new PrinterQueue();
			enter.setId(rs.getInt("id"));
			enter.setGradeName(rs.getString("gradeName"));
			enter.setClassName(rs.getString("className"));
			enter.setStudentNo(rs.getString("studentNo"));
			enter.setSemesterId(rs.getInt("semesterId"));
			enter.setType(rs.getInt("type"));
			enter.setStatus(rs.getInt("status"));
			enter.setTime(rs.getString("time"));
			enter.setStudentName(rs.getString("studentName"));
			return enter;
		}
    }

	public static class MapperTmp implements RowMapper<Tmp> {
		@Override
		public Tmp fetch(ResultSet rs, int index) throws SQLException {
			Tmp enter = new Tmp();
			enter.setName(rs.getString("name"));
			enter.setClassName(rs.getString("className"));
			enter.setStudentNo(rs.getString("studentNo"));
			enter.setOldStudentNo(rs.getString("oldStudentNo"));
			return enter;
		}
	}
}
