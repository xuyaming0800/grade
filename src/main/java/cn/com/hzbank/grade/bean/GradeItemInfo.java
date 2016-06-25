package cn.com.hzbank.grade.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 测评项目类
 * 
 * @author Xuyaming
 *
 */
public class GradeItemInfo extends GradeAbstractBean implements Serializable {

	private static final long serialVersionUID = 6025485127101242800L;
	@NotNull(message = "{item.itemName.null}", groups = { AddGradeItemInfo.class})
	@NotEmpty(message = "{item.itemName.null}", groups = { AddGradeItemInfo.class })
	private String itemName;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public interface AddGradeItemInfo{

	}

}
