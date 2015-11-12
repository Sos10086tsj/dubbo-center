package com.chinesedreamer.dubbocenter.job.constant;

public enum JobStatus {
	UNKNOWN(-1,"未知"),RUNNING(0,"运行中"),STOP(1,"已停止");
	
	private final Integer value;
	private final String label;
	
	private JobStatus(Integer value,String label){
		this.value = value;
		this.label = label;
	}
	
	public static JobStatus getByValue(Integer value) {
		for (JobStatus js : JobStatus.values()) {
			if (js.getValue() == value) {
				return js;
			}
		}
		return JobStatus.UNKNOWN;
	}

	public Integer getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	
}
