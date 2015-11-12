package com.chinesedreamer.dubbocenter.util;

public class DateUtils {
	/**
	 * 毫秒计算成其他周期
	 * @param milliseconds
	 * @return
	 */
	public static String format(long milliseconds) {
		if (milliseconds < 1000) {
			return milliseconds + "毫秒";
		}else if(milliseconds >= 1000 && milliseconds < 1000 * 60){
			return (milliseconds / 1000) + "分钟";
		}else if(milliseconds >= 1000 * 60 && milliseconds < 1000 * 60 * 60){
			return (milliseconds / 1000 / 60) + "小时";
		}else{
			return (milliseconds / 1000 / 60 / 24) + "天";
		}
	}
}
