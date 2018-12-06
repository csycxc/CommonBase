package com.banry.pscm.commonbase.service.biz;

public class ItemConsScheme {
	
    private String schemeCode;
	private String divItemCode;
	private String divLevel;
	private String name;
	private String schType;
	private String paths;
	private String description;

	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode == null ? null : schemeCode.trim();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
	public String getSchType() {
		return schType;
	}
	public void setSchType(String schType) {
		this.schType = schType == null ? null : schType.trim();
	}
	public String getPaths() {
		return paths;
	}
	public void setPaths(String paths) {
		this.paths = paths == null ? null : paths.trim();
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	
    
}