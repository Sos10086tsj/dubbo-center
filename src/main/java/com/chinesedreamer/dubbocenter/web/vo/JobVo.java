package com.chinesedreamer.dubbocenter.web.vo;

import com.chinesedreamer.dubbocenter.job.constant.JobStatus;
import com.chinesedreamer.dubbocenter.job.model.Job;
import com.chinesedreamer.dubbocenter.util.DateUtils;

public class JobVo {
	private String jobId;
	private String jobDescription;
	private String cmdStartLocation;
	private String cmdStopLoacation;
	private String status;
	private String checkInterval;
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
	public String getStatus() {
		return status;
	}
	public String getCheckInterval() {
		return checkInterval;
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
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCheckInterval(String checkInterval) {
		this.checkInterval = checkInterval;
	}
	
	public static JobVo covert2Vo(Job job) {
		JobVo vo = new JobVo();
		vo.setCmdStartLocation(job.getCmdStartLocation());
		vo.setCmdStopLoacation(job.getCmdStopLoacation());
		vo.setJobDescription(job.getJobDescription());
		vo.setJobId(job.getJobId());
		vo.setStatus(JobStatus.getByValue(job.getStatus()).getLabel());
		vo.setCheckInterval(DateUtils.format(job.getCheckInterval()));
		return vo;
	}
}
