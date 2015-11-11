package com.chinesedreamer.dubbocenter.job.dao;

import com.chinesedreamer.dubbocenter.job.model.Job;

public interface JobDao {
	public int save(Job job);
	public int update(Job job);
	public int delete(Job job);
	public Job getByJobId(String jobId);
}
