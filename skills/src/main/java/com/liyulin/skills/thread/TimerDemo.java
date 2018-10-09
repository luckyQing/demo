package com.liyulin.skills.thread;

import java.util.Calendar;
import java.util.Formatter;
import java.util.Timer;
import java.util.TimerTask;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时器
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@Slf4j
public class TimerDemo extends TimerTask {
	
	private String name;

	public TimerDemo(String name) {
		this.name = name;
	}

	public void run() {
		Formatter fmt = new Formatter();
		Calendar cal = Calendar.getInstance();
		fmt.format("%tY年%tm月%td日%tA  %tH时%tM分%tS秒", cal, cal, cal, cal, cal, cal, cal);
		log.info("timer-{}==>{}", name, fmt);
	}

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerDemo("frist"), 0, 2000);
		timer.schedule(new TimerDemo("second"), 0, 5000);
	}
	
}
