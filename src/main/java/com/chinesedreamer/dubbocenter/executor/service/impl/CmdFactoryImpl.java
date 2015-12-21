package com.chinesedreamer.dubbocenter.executor.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.dubbocenter.executor.CmdExecutor;
import com.chinesedreamer.dubbocenter.executor.CmdFactory;

@Service
public class CmdFactoryImpl implements CmdFactory{
	@Resource(name = "shCmdExecutor")
	private CmdExecutor shCmdExecutor;
	@Resource(name = "batCmdExecutor")
	private CmdExecutor batCmdExecutor;

	@Override
	public CmdExecutor getInstance() {
		String osName = System.getProperties().getProperty("os.name");
		if (osName.toLowerCase().startsWith("windows")) {
			return this.batCmdExecutor;
		}
		return this.shCmdExecutor;
	}

}
