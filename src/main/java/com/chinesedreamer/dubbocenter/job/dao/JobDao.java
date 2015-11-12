package com.chinesedreamer.dubbocenter.job.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.chinesedreamer.dubbocenter.job.model.Job;

public interface JobDao {
	public int save(Job job);
	public int update(Job job);
	public int delete(String jobId);
	public int deleteInbatch(List<String> jobIds);
	public Job getByJobId(String jobId);
	public List<Job> getList(Map<String, Object> criteria);
	public int updateStatus(@Param(value="jobId")String jobId, @Param(value="status")Integer status);
}
