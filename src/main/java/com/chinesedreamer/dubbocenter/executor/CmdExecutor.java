package com.chinesedreamer.dubbocenter.executor;

import com.chinesedreamer.dubbocenter.job.model.Job;

public class CmdExecutor implements Runnable{
	
	private Job job;
	
	public CmdExecutor(Job job){
		this.job = job;
	}

	@Override
	public void run() {
		
	}

}
