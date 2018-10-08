package com.liyulin.skills.awt.other;

import java.io.IOException;

/**
 * 锁屏
 * 
 * @author liyulin
 * @version 1.0 08/07/2013
 */
public class LockScreem {

	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
