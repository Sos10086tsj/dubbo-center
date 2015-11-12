package com.chinesedreamer.dubbocenter.job.service;

import java.util.List;
import java.util.Map;

import com.chinesedreamer.dubbocenter.job.constant.JobStatus;
import com.chinesedreamer.dubbocenter.job.model.Job;

public interface JobService {
	
	/**
	 * 获取job
	 * @param jobId
	 * @return
	 */
	public Job getJob(String jobId);
	
	public List<Job> getList(Map<String, Object> criteria);
	
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
	 * @param jobId
	 * @return
	 */
	public int deleteJob(String jobId);
	
	/**
	 * 批量删除job
	 * @param job
	 * @return
	 */
	public void deleteJobInBatch(String[] jobIds);
	
	/**
	 * 启动job
	 * @param jobId
	 * @return
	 */
	public void startJob(String jobId);
	
	/**
	 * 停止job
	 * @param jobId
	 * @return
	 */
	public void stopJob(String jobId);
}
