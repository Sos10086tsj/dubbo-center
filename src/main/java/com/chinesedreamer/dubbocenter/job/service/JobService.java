package com.chinesedreamer.dubbocenter.job.service;

import com.chinesedreamer.dubbocenter.job.constant.JobStatus;
import com.chinesedreamer.dubbocenter.job.model.Job;

public interface JobService {
	
	/**
	 * 获取job
	 * @param jobId
	 * @return
	 */
	public Job getJob(String jobId);
	
	/**
	 * 获取job实时状态
	 * @param jobId
	 * @return
	 */
	public JobStatus getJobStatus(String jobId);
	
	/**
	 * 增加job
	 * @param job
	 * @return
	 */
	public Job addJob(Job job);
	
	/**
	 * 更新job
	 * @param job
	 * @return
	 */
	public Job updateJob(Job job);
	
	/**
	 * 删除job
	 * @param job
	 * @return
	 */
	public int deleteJob(Job job);
	
	/**
	 * 启动job
	 * @param job
	 * @return
	 */
	public void startJob(Job job);
	
	/**
	 * 停止job
	 * @param job
	 * @return
	 */
	public void stopJob(Job job);
}
