package com.example.time;

import java.util.Timer;
import java.util.TimerTask;

import com.example.music_control.MyPrefference;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

public class TimeTool {
	
	private Timer mTimer = null;
	private MyPrefference mp;
	private Context context;
	private TimerTask mTimerTask = null;
	private int time = 0;
	private Handler[] mHandler;
	private String timeStatue;
	
	public TimeTool(Handler...mHandler)
	{
		this.mHandler=mHandler;
		mTimer=new Timer();
	}
    
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	

	public static String Concat(long l) {
		String nTime = null;
		long m = l / 1000 / 60;
		long second = l / 1000 % 60;
		nTime = String.format("%02d:%02d", m, second);
		return nTime;
	}

	
	public void startTime()
	{
		if (mHandler == null) {
			return;
		}
		if(mTimerTask==null)
		{
			mTimerTask = new MyTimerTask();
		}
		
		mTimer.schedule(mTimerTask, 1000, 1000);
	}
	
	public void stopTime()
	{
		if (mTimerTask != null) {
			mTimerTask.cancel();
//			mTimer.cancel();
			mTimerTask = null;
//			mTimer=null;
		}
	}

	public Timer getTimer()
	{
		return mTimer;
	}
	
	public void setTimer()
	{
		mTimer=null;
	}
	class MyTimerTask extends TimerTask {

		@Override
		public void run() {
			if (mHandler != null) {
				for (Handler handler : mHandler) {
					Message msg = handler.obtainMessage(time);
					time++;
					msg.sendToTarget();
				}
			}
		}
		
	}
	
}
