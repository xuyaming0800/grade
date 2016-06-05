package cn.com.hzbank.grade.bean;

import java.io.Serializable;

/**
 * 测评项目类
 * 
 * @author Xuyaming
 *
 */
public class GradeItemInfo extends GradeAbstractBean implements Serializable {

	private static final long serialVersionUID = 6025485127101242800L;
	private String itemName;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
