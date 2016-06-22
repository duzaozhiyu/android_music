package com.example.service;
import com.example.music_control.MusicControl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return musicServiceBinder;
	}
//	private Context context;
	private  MusicServiceBinder musicServiceBinder = new MusicServiceBinder();
    private MusicControl mc; 
	
	public MusicServiceBinder getBinder(){
		return musicServiceBinder;
	}
	
	public MusicControl getMControl()
	{
		return mc;
	}
	
	public class MusicServiceBinder extends Binder {

		public MusicService getService() {
			return MusicService.this;
		}

	}


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub");
		System.out.println("创建媒体控制器");
		
		super.onCreate();
		if(mc==null)
		{
		  mc=new MusicControl();
		}

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("取消媒体控制器");
		mc.exit();
		super.onDestroy();
		
	}


}
