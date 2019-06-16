package com.test.demo.controller;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;


public class TimerEx extends TimerTask{





String s;

TimerEx(String s){
	this.s = s;
}
	@Override
	public void run() {
		try{
			Timer timer = new Timer();
		System.out.println("Terminated the Timer Thread!"+s);
		timer.cancel(); // Terminate the thread
		}catch(Exception	ex){
			System.out.println(ex);
		}
	}
}
