package com.yyc.androiddemo.bean;

import com.yyc.androiddemo.util.StringUtil;

/**
 * TimerCount类，每个实例对应于一个表示分开计时的条目，包含计次数，计次时间，于上次计次相差时间（单位是百分之一秒）
 * 
 * @author laberat
 * 
 */
public class TimerCount {
	private int count;// 计次数
	private int timerMin;// 计次时间——分
	private int timerSec;// 计次时间——秒
	private int timerCentiSec;// 计次时间——百分秒
	private int timerDiffer;// 与上次计次相差时间，单位百分之一秒

	public TimerCount(int count, int timerMin, int timerSec, int timerCentiSec,
			int timerDiffer) {
		super();
		this.count = count;
		this.timerMin = timerMin;
		this.timerSec = timerSec;
		this.timerCentiSec = timerCentiSec;
		this.timerDiffer = timerDiffer;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTimerMin() {
		return timerMin;
	}

	public void setTimerMin(int timerMin) {
		this.timerMin = timerMin;
	}

	public int getTimerSec() {
		return timerSec;
	}

	public void setTimerSec(int timerSec) {
		this.timerSec = timerSec;
	}

	public int getTimerCentiSec() {
		return timerCentiSec;
	}

	public void setTimerCentiSec(int timerCentiSec) {
		this.timerCentiSec = timerCentiSec;
	}

	public int getTimerDiffer() {
		return timerDiffer;
	}

	public void setTimerDiffer(int timerDiffer) {
		this.timerDiffer = timerDiffer;
	}

	public String toString() {
		return "第" + this.count + "次分时计次——————"
				+ StringUtil.formatUnderTen(this.timerMin) + " : "
				+ StringUtil.formatUnderTen(this.timerSec) + " : "
				+ StringUtil.formatUnderTen(this.timerCentiSec) + "————  +"
				+ this.timerDiffer;
	}

}
