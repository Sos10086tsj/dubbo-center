package com.chinesedreamer.dubbocenter.executor.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.chinesedreamer.dubbocenter.executor.CmdExecutor;
import com.chinesedreamer.dubbocenter.job.model.Job;

@Service("shCmdExecutor")
public class CmdExecutorImpl implements CmdExecutor{
	
	private final String cmdSymbol = "sh ";

	@Override
	public String execute(Job job) throws IOException {
		String result = "";
		
		String cmd = cmdSymbol + job.getCmdStartLocation();
		Process process = Runtime.getRuntime().exec(cmd);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String line = null;
		while ((line = br.readLine()) != null) {
			result += line + " ";
		}

		br.close();
		process.destroy();
		
		return result;
	}

}
