package com.chinesedreamer.dubbocenter.job.model;

public class Job {
	private String jobId;
	private String jobDescription;
	private String cmdStartLocation;
	private String cmdStopLoacation;
	private Integer status;//0:启用	1:禁用
	public String getJobId() {
		return jobId;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public String getCmdStartLocation() {
		return cmdStartLocation;
	}
	public String getCmdStopLoacation() {
		return cmdStopLoacation;
	}
	public Integer getStatus() {
		return status;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public void setCmdStartLocation(String cmdStartLocation) {
		this.cmdStartLocation = cmdStartLocation;
	}
	public void setCmdStopLoacation(String cmdStopLoacation) {
		this.cmdStopLoacation = cmdStopLoacation;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
