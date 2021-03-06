package org.marker.certificate.bean;


import java.io.Serializable;
import java.util.Date;

/**
 * 年级
 * @author marker
 * @version 1.0
 */
public class Class implements Serializable {


    /**
     * 班级名称
     */
    private String name;

	/**
	 * 年级ID
	 */
	private String gradeName;

	/**
	 * 录入时间
	 */
	private Date createTime;

	/**
	 * 描述
	 */
	private String desc;



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
