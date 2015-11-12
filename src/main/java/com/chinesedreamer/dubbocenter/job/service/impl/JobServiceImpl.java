package com.chinesedreamer.dubbocenter.job.service.impl;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.chinesedreamer.dubbocenter.executor.CmdExecutor;
import com.chinesedreamer.dubbocenter.executor.JobRunner;
import com.chinesedreamer.dubbocenter.job.constant.JobStatus;
import com.chinesedreamer.dubbocenter.job.dao.JobDao;
import com.chinesedreamer.dubbocenter.job.model.Job;
import com.chinesedreamer.dubbocenter.job.service.JobService;
import com.chinesedreamer.dubbocenter.util.ThreadUtils;

@Service
public class JobServiceImpl implements JobService{
	
	private Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
	
	@Resource
	private JobDao jobDao;
	
	@Resource(name = "shCmdExecutor")
	private CmdExecutor cmdExecutor;
	
	@Override
	public Job getJob(String jobId) {
		return this.jobDao.getByJobId(jobId);
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
	public int deleteJob(Job job) {
		return this.jobDao.delete(job);
	}

	@Override
	public void startJob(Job job) {
		Job exist = this.jobDao.getByJobId(job.getJobId());
		if (null == exist) {
			this.jobDao.save(job);
		}else {
			try {
				BeanUtils.copyProperties(exist, job);
			} catch (Exception e) {
				this.logger.error("{}",e);
			}
			this.jobDao.update(exist);
		}
		
		JobRunner jr = new JobRunner(job, cmdExecutor, this);
		Thread thread = new Thread(jr, job.getJobId());
		thread.start();
	}

	@Override
	public void stopJob(Job job) {
		Thread thread = ThreadUtils.getThread(job.getJobId());
		thread.interrupt();
		
		job.setStatus(JobStatus.STOP.getValue());
		this.jobDao.update(job);
	}

}
