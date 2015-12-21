package com.chinesedreamer.dubbocenter.pid;

public class Pid {
	private String serviceName;
	private String pid;
	private String sessionName;
	private String sessionNumber;
	private String memory;
	public String getServiceName() {
		return serviceName;
	}
	public String getPid() {
		return pid;
	}
	public String getSessionName() {
		return sessionName;
	}
	public String getSessionNumber() {
		return sessionNumber;
	}
	public String getMemory() {
		return memory;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	
	
}
