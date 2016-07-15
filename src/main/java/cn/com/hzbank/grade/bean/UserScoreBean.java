package cn.com.hzbank.grade.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuyaming on 16/7/15.
 */
public class UserScoreBean implements Serializable {


    private static final long serialVersionUID = -3759555711350706831L;
    private String userName;
	private Double totalScore;
	private Map<String, Double> scoreMap=new HashMap<String, Double>();
	private Map<String, Integer> gradeMap=new HashMap<String, Integer>();;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public Map<String, Double> getScoreMap() {
		return scoreMap;
	}

	public void setScoreMap(Map<String, Double> scoreMap) {
		this.scoreMap = scoreMap;
	}

	public Map<String, Integer> getGradeMap() {
		return gradeMap;
	}

	public void setGradeMap(Map<String, Integer> gradeMap) {
		this.gradeMap = gradeMap;
	}
}
