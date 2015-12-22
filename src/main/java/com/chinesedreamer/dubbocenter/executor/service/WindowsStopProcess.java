package com.chinesedreamer.dubbocenter.executor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinesedreamer.dubbocenter.pid.Pid;

public class WindowsStopProcess {
	private static Logger logger = LoggerFactory.getLogger(WindowsStopProcess.class);
	
	private static String TASK_LIST = "taskList";
	private static String TASK_KILL = "TSKILL ";
	private static String PID_PATH = "wmic process where processid=<pid> get commandline";
	
	public static void stopProcess(String path) {
		Process process = null;
		List<String> pids = new ArrayList<String>();
		try {
			process = Runtime.getRuntime().exec(TASK_LIST);
			Scanner in = new Scanner(process.getInputStream(),"GBK");
			while (in.hasNextLine()) {
				String line = in.nextLine();
				if (line.startsWith("java")) {
					pids.add(line);
				}
			}
			in.close();
			List<Pid> getPidObjs = getPidObjs(pids);
			for (Pid pid : getPidObjs) {
				process = Runtime.getRuntime().exec(PID_PATH.replace("<pid>", pid.getPid()));
				in = new Scanner(process.getInputStream(),"GBK");
				while (in.hasNextLine()) {
					String commandPath = in.nextLine();
					logger.info("process path: {}" , commandPath);
					if (commandPath.trim().endsWith(path)) {
						logger.info("kill pid#{}",pid.getPid());
						Runtime.getRuntime().exec(TASK_KILL + pid.getPid());
						break;
					}
				}
			}
		} catch (IOException e) {
			logger.error("{}",e);
		}
	}
	
	private static List<Pid> getPidObjs(List<String> pids) {
		List<Pid> pidOjbs = new ArrayList<Pid>();
		
		List<String> pidObj = new ArrayList<String>();
		
		for (String pid : pids) {
			String[] pidProperties = pid.split(" ");
			pidObj.clear();
			for (String pidProperty : pidProperties) {
				if (StringUtils.isNotEmpty(pidProperty)) {
					pidObj.add(pidProperty);
				}
			}
			pidOjbs.add(generatePid(pidObj));
		}
		
		return pidOjbs;
	}
	
	private static Pid generatePid(List<String> pidObj) {
		Pid pid = new Pid();
		pid.setServiceName(pidObj.get(0));
		pid.setPid(pidObj.get(1));
		pid.setSessionName(pidObj.get(2));
		pid.setSessionNumber(pidObj.get(3));
		pid.setMemory(pidObj.get(4));
		return pid;
	}
}
