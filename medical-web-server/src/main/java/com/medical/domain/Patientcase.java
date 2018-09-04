package com.medical.domain;

import java.util.Date;

public class Patientcase extends BaseObject {

	private Integer id;
	
	private String uuid;
	
	private String casename; // 病例名称
	
	private Date occurancetime; // 发病时间
	
	private String maincase; // 主诉
	
	private String casehis; // 现病史
	
	private String diagnosis; //初步诊断
	
	private String suggestion; // 治疗意见
	
	private String remark; // 备注
	
	private Date createtime; // 病例创建时间
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCasename() {
		return casename;
	}

	public void setCasename(String casename) {
		this.casename = casename;
	}

	public Date getOccurancetime() {
		return occurancetime;
	}

	public void setOccurancetime(Date occurancetime) {
		this.occurancetime = occurancetime;
	}

	public String getMaincase() {
		return maincase;
	}

	public void setMaincase(String maincase) {
		this.maincase = maincase;
	}

	public String getCasehis() {
		return casehis;
	}

	public void setCasehis(String casehis) {
		this.casehis = casehis;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
	
}
