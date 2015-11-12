package com.chinesedreamer.dubbocenter.job.constant;

public enum JobStatus {
	UNKNOWN(-1),RUNNING(0),STOP(1);
	
	private final Integer value;
	
	private JobStatus(Integer value){
		this.value = value;
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
	
	
}
