package com.banry.pscm.commonbase.service.biz;

public class HiddenTrouble {
	
    private String troubleCode;
	private String divItemCode;
	private String divLevel;
	private String trobuleCate;
	private String trobuleLevel;
	private String investItem;
	private String investContent;
	private String description;

	public String getTroubleCode() {
		return troubleCode;
	}
	public void setTroubleCode(String troubleCode) {
		this.troubleCode = troubleCode == null ? null : troubleCode.trim();
	}
	public String getDivItemCode() {
		return divItemCode;
	}
	public void setDivItemCode(String divItemCode) {
		this.divItemCode = divItemCode == null ? null : divItemCode.trim();
	}
	public String getDivLevel() {
		return divLevel;
	}
	public void setDivLevel(String divLevel) {
		this.divLevel = divLevel == null ? null : divLevel.trim();
	}
	public String getTrobuleCate() {
		return trobuleCate;
	}
	public void setTrobuleCate(String trobuleCate) {
		this.trobuleCate = trobuleCate == null ? null : trobuleCate.trim();
	}
	public String getTrobuleLevel() {
		return trobuleLevel;
	}
	public void setTrobuleLevel(String trobuleLevel) {
		this.trobuleLevel = trobuleLevel == null ? null : trobuleLevel.trim();
	}
	public String getInvestItem() {
		return investItem;
	}
	public void setInvestItem(String investItem) {
		this.investItem = investItem == null ? null : investItem.trim();
	}
	public String getInvestContent() {
		return investContent;
	}
	public void setInvestContent(String investContent) {
		this.investContent = investContent == null ? null : investContent.trim();
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	
    
}