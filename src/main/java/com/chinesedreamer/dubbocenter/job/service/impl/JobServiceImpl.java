package com.chinesedreamer.dubbocenter.job.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.dubbocenter.executor.service.CmdFactory;
import com.chinesedreamer.dubbocenter.executor.service.WindowsStopProcess;
import com.chinesedreamer.dubbocenter.job.constant.JobStatus;
import com.chinesedreamer.dubbocenter.job.dao.JobDao;
import com.chinesedreamer.dubbocenter.job.model.Job;
import com.chinesedreamer.dubbocenter.job.service.JobService;
import com.chinesedreamer.dubbocenter.util.SystemUtils;

@Service
public class JobServiceImpl implements JobService{
	
	@Resource
	private JobDao jobDao;
	
	@Resource
	private CmdFactory cmdFactory;
	
	@Override
	public Job getJob(String jobId) {
		return this.jobDao.getByJobId(jobId);
	}
	
	@Override
	public List<Job> getList(Map<String, Object> criteria) {
		return this.jobDao.getList(criteria);
	}
	
	@Override
	public JobStatus getJobStatus(String jobId) {
		Job job = this.jobDao.getByJobId(jobId);
		return JobStatus.getByValue(job.getStatus());
	}

	@Override
	public Job addJob(Job job) {
		int result = this.jobDao.save(job);
		if (result < 1) {
			return null;
		}
		return job;
	}

	@Override
	public Job updateJob(Job job) {
		int result = this.jobDao.update(job);
		if (result < 1) {
			return null;
		}
		return job;
	}

	@Override
	public int deleteJob(String jobId) {
		return this.jobDao.delete(jobId);
	}

	@Override
	public void startJob(String jobId) {
		Job exist = this.jobDao.getByJobId(jobId);
		if (null != exist) {
			this.jobDao.updateStatus(jobId, JobStatus.RUNNING.getValue());
		}
		this.cmdFactory.getInstance().execute(exist, true);
	}

	@Override
	public void stopJob(String jobId) {
		Job exist = this.jobDao.getByJobId(jobId);
		if (null != exist) {
			this.jobDao.updateStatus(jobId, JobStatus.STOP.getValue());
		}
		if (SystemUtils.isWindows()) {
			WindowsStopProcess.stopProcess(exist.getCmdStopLoacation());
		}else {
			this.cmdFactory.getInstance().execute(exist, false);
		}
	}

	@Override
	public void deleteJobInBatch(String[] jobIds) {
		this.jobDao.deleteInbatch(Arrays.asList(jobIds));
	}

	

}
