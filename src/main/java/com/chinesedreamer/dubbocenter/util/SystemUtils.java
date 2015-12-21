package com.chinesedreamer.dubbocenter.util;

public class SystemUtils {
	public static boolean isWindows() {
		String osName = System.getProperties().getProperty("os.name");
		if (osName.toLowerCase().startsWith("windows")) {
			return true;
		}
		return false;
	}
}
