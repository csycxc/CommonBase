package com.banry.pscm.commonbase.service.biz;


public class SubDivWork {
	
    private String subDivCode;
	private String name;
	private String charactDes;
	private String unit;
	private Double number;
	private Double compUnitPrice;
	private Double temporaryMeasurePrice;
	private Double quotaManualFee;

	public String getSubDivCode() {
		return subDivCode;
	}
	public void setSubDivCode(String subDivCode) {
		this.subDivCode = subDivCode == null ? null : subDivCode.trim();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
	public String getCharactDes() {
		return charactDes;
	}
	public void setCharactDes(String charactDes) {
		this.charactDes = charactDes == null ? null : charactDes.trim();
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}
	public Double getNumber() {
		return number;
	}
	public void setNumber(Double number) {
		this.number = number;
	}
	public Double getCompUnitPrice() {
		return compUnitPrice;
	}
	public void setCompUnitPrice(Double compUnitPrice) {
		this.compUnitPrice = compUnitPrice;
	}
	public Double getTemporaryMeasurePrice() {
		return temporaryMeasurePrice;
	}
	public void setTemporaryMeasurePrice(Double temporaryMeasurePrice) {
		this.temporaryMeasurePrice = temporaryMeasurePrice;
	}
	public Double getQuotaManualFee() {
		return quotaManualFee;
	}
	public void setQuotaManualFee(Double quotaManualFee) {
		this.quotaManualFee = quotaManualFee;
	}

	
    
}