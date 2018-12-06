package com.banry.pscm.commonbase.service.biz;

public class Hazards {
    
	private String hazardsCode;
	private String divItemCode;
	private String divLevel;
	private String hazardsFactors;
	private String hazardsLevel;
	private String accidents;
	private String controlsMeasures;
	private String description;

	public String getHazardsCode() {
		return hazardsCode;
	}
	public void setHazardsCode(String hazardsCode) {
		this.hazardsCode = hazardsCode == null ? null : hazardsCode.trim();
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
	public String getHazardsFactors() {
		return hazardsFactors;
	}
	public void setHazardsFactors(String hazardsFactors) {
		this.hazardsFactors = hazardsFactors == null ? null : hazardsFactors.trim();
	}
	public String getHazardsLevel() {
		return hazardsLevel;
	}
	public void setHazardsLevel(String hazardsLevel) {
		this.hazardsLevel = hazardsLevel == null ? null : hazardsLevel.trim();
	}
	public String getAccidents() {
		return accidents;
	}
	public void setAccidents(String accidents) {
		this.accidents = accidents == null ? null : accidents.trim();
	}
	public String getControlsMeasures() {
		return controlsMeasures;
	}
	public void setControlsMeasures(String controlsMeasures) {
		this.controlsMeasures = controlsMeasures == null ? null : controlsMeasures.trim();
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
	
    
    
    
    
}