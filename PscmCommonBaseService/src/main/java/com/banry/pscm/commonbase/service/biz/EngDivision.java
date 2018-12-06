package com.banry.pscm.commonbase.service.biz;

public class EngDivision {
    
	private String divItemCode;
	private String divName;
	private String skill;
	private String divLevel;
	private String parentDivItemCode;

	public String getDivItemCode() {
		return divItemCode;
	}
	public void setDivItemCode(String divItemCode) {
		this.divItemCode = divItemCode == null ? null : divItemCode.trim();
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName == null ? null : divName.trim();
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill == null ? null : skill.trim();
	}
	public String getDivLevel() {
		return divLevel;
	}
	public void setDivLevel(String divLevel) {
		this.divLevel = divLevel == null ? null : divLevel.trim();
	}
	public String getParentDivItemCode() {
		return parentDivItemCode;
	}
	public void setParentDivItemCode(String parentDivItemCode) {
		this.parentDivItemCode = parentDivItemCode == null ? null : parentDivItemCode.trim();
	}
	
	
	
}