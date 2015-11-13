package com.chinesedreamer.dubbocenter.executor.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.dubbocenter.executor.CmdExecutor;
import com.chinesedreamer.dubbocenter.job.model.Job;

@Service("shCmdExecutor")
public class CmdExecutorShImpl implements CmdExecutor{
	private Logger logger = LoggerFactory.getLogger(CmdExecutorShImpl.class);

	@Override
	public void execute(Job job, boolean start){
		
		String script = start ? job.getCmdStartLocation() : job.getCmdStopLoacation();
		String sudo = "chmod 777 " + script;
		String cmd = "/bin/sh " + script;
		
		
		Process process = null;
		try {
			logger.info("execute command:{}",sudo);
			process = Runtime.getRuntime().exec(sudo);
			process.waitFor();
			logger.info("execute command:{}",cmd);
			Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			logger.error("{}",e);
		}finally {
			process.destroy();
		}
	}

}
