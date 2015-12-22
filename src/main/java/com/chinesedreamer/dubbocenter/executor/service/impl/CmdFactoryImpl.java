package com.chinesedreamer.dubbocenter.executor.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.dubbocenter.executor.service.CmdExecutor;
import com.chinesedreamer.dubbocenter.executor.service.CmdFactory;
import com.chinesedreamer.dubbocenter.util.SystemUtils;

@Service
public class CmdFactoryImpl implements CmdFactory{
	@Resource(name = "shCmdExecutor")
	private CmdExecutor shCmdExecutor;
	@Resource(name = "batCmdExecutor")
	private CmdExecutor batCmdExecutor;

	@Override
	public CmdExecutor getInstance() {
		if (SystemUtils.isWindows()) {
			return this.batCmdExecutor;
		}
		return this.shCmdExecutor;
	}

}
