package com.banry.pscm.commonbase.service.biz;


public class SubDivWorkQuota {
    
	private String resCode;
	private String subDivCode;
	private String quotaCode;
	private String itemName;
	private String itemDetailName;
	private String resourcesType;
	private String resTypeLevel;
	private String resDetailType;
	private String unit;
	private Double usedNum;
	private Double lossRate;
	private Double saveExcessRate;

	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode == null ? null : resCode.trim();
	}
	public String getSubDivCode() {
		return subDivCode;
	}
	public void setSubDivCode(String subDivCode) {
		this.subDivCode = subDivCode == null ? null : subDivCode.trim();
	}
	public String getQuotaCode() {
		return quotaCode;
	}
	public void setQuotaCode(String quotaCode) {
		this.quotaCode = quotaCode == null ? null : quotaCode.trim();
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName == null ? null : itemName.trim();
	}
	public String getItemDetailName() {
		return itemDetailName;
	}
	public void setItemDetailName(String itemDetailName) {
		this.itemDetailName = itemDetailName == null ? null : itemDetailName.trim();
	}
	public String getResourcesType() {
		return resourcesType;
	}
	public void setResourcesType(String resourcesType) {
		this.resourcesType = resourcesType == null ? null : resourcesType.trim();
	}
	public String getResTypeLevel() {
		return resTypeLevel;
	}
	public void setResTypeLevel(String resTypeLevel) {
		this.resTypeLevel = resTypeLevel == null ? null : resTypeLevel.trim();
	}
	public String getResDetailType() {
		return resDetailType;
	}
	public void setResDetailType(String resDetailType) {
		this.resDetailType = resDetailType == null ? null : resDetailType.trim();
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}
	public Double getUsedNum() {
		return usedNum;
	}
	public void setUsedNum(Double usedNum) {
		this.usedNum = usedNum;
	}
	public Double getLossRate() {
		return lossRate;
	}
	public void setLossRate(Double lossRate) {
		this.lossRate = lossRate;
	}
	public Double getSaveExcessRate() {
		return saveExcessRate;
	}
	public void setSaveExcessRate(Double saveExcessRate) {
		this.saveExcessRate = saveExcessRate;
	}

	
    
    
}