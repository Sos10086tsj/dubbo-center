package com.chinesedreamer.dubbocenter.executor;

import com.chinesedreamer.dubbocenter.job.model.Job;

public interface CmdExecutor {
	
	/**
	 * 执行脚本
	 * @param job
	 * @return
	 */
	public String execute(Job job)  throws Exception ;
}
