package com.chinesedreamer.dubbocenter.job.service.impl;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.dubbocenter.base.BaseTest;
import com.chinesedreamer.dubbocenter.job.constant.JobStatus;
import com.chinesedreamer.dubbocenter.job.model.Job;
import com.chinesedreamer.dubbocenter.job.service.JobService;

public class JobServiceImplTest extends BaseTest{
	
	@Resource
	private JobService jobService;

//	@Test
//	public void testGetJob() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetJobStatus() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testAddJob() {
		Job job = this.jobService.addJob(this.initJob());
		assertNotNull(job);
		System.out.println(job);
	}
	
	private Job initJob() {
		Job job = new Job();
		job.setCheckInterval(3600000l);
		job.setCmdStartLocation("sh /bin/start.sh");
		job.setCmdStopLoacation("sh /bin/stop.sh");
		job.setJobDescription("测试");
		job.setJobId("test" + System.currentTimeMillis());
		job.setStatus(JobStatus.RUNNING.getValue());
		return job;
	}

//	@Test
//	public void testUpdateJob() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteJob() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testStartJob() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testStopJob() {
//		fail("Not yet implemented");
//	}

}
