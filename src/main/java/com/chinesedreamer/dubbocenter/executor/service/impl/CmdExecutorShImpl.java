package com.chinesedreamer.dubbocenter.executor.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.dubbocenter.executor.service.CmdExecutor;
import com.chinesedreamer.dubbocenter.job.model.Job;

@Service("shCmdExecutor")
public class CmdExecutorShImpl implements CmdExecutor{
	private Logger logger = LoggerFactory.getLogger(CmdExecutorShImpl.class);

	@Override
	public void execute(Job job, boolean start){
		
		String script = start ? job.getCmdStartLocation() : job.getCmdStopLoacation();
		logger.info("execute command:{}",script);
		try {
			Runtime.getRuntime().exec(script);
		} catch (IOException e) {
			logger.error("{}",e);
		}
	}

}
