package com.chinesedreamer.dubbocenter.executor.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.dubbocenter.executor.service.CmdExecutor;
import com.chinesedreamer.dubbocenter.job.model.Job;

@Service("batCmdExecutor")
public class CmdExecutorBatImpl implements CmdExecutor{
	private Logger logger = LoggerFactory.getLogger(CmdExecutorBatImpl.class);

	@Override
	public void execute(Job job, boolean start) {
		String script = start ? job.getCmdStartLocation() : job.getCmdStopLoacation();
		logger.info("execute command:{}",script);
		try {
			Process process = Runtime.getRuntime().exec(script);
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line ;
			while ((line = br.readLine()) != null) {
				logger.info(line);
			}
		} catch (IOException e) {
			logger.error("{}",e);
		}
	}
}
