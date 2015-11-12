package com.chinesedreamer.dubbocenter.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinesedreamer.dubbocenter.job.constant.JobStatus;
import com.chinesedreamer.dubbocenter.job.model.Job;
import com.chinesedreamer.dubbocenter.job.service.JobService;

public class JobRunner implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(JobRunner.class);

	private Job job;
	private CmdExecutor cmdExecutor;
	private JobService jobService;
	
	public JobRunner(Job job, CmdExecutor cmdExecutor, JobService jobService){
		this.job = job;
		this.cmdExecutor = cmdExecutor;
		this.jobService = jobService;
	}
	
	@Override
	public void run() {
		try {
			String result = this.cmdExecutor.execute(job);
			logger.info("result:{}", result);
		} catch (Exception e) {
			logger.error("{}",e);
		}
		
		do {
			try {
				Thread.sleep(this.job.getCheckInterval());
			} catch (InterruptedException e) {
				this.logger.error("{}",e);
			}
		} while (this.jobService.getJobStatus(this.job.getJobId()).equals(JobStatus.RUNNING));
	}

}
