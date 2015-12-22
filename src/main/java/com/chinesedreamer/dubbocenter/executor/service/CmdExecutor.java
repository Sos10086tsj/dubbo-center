package com.chinesedreamer.dubbocenter.executor.service;

import com.chinesedreamer.dubbocenter.job.model.Job;

public interface CmdExecutor {
	
	/**
	 * 执行脚本
	 * @param job
	 */
	public void execute(Job job, boolean start);
}
