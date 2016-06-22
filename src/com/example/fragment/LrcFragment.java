package com.example.fragment;

import com.example.music_control.MusicControl;
import com.example.my_android_music.R;
import com.example.service.MusicService;
import com.example.service.MusicService.MusicServiceBinder;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SeekBar;

public class LrcFragment extends Fragment {

//	private WordView mWordView;
//	private List mTimeList;
//	private MediaPlayer mPlayer;
//
//	@SuppressLint("SdCardPath")
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//
//		Button button = (Button) findViewById(R.id.button);
//		button.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				mPlayer.stop();
//				finish();
//			}
//		});
//
//		mWordView = (WordView) findViewById(R.id.text);
//
//		mPlayer = new MediaPlayer();
//		mPlayer.reset();
//		LrcHandle lrcHandler = new LrcHandle();
//		try {
//			lrcHandler.readLRC("/sdcard/陪我去流浪.lrc");
//			mTimeList = lrcHandler.getTime();
//			mPlayer.setDataSource("/sdcard/陪我去流浪.mp3");
//			mPlayer.prepare();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		}
//		final Handler handler = new Handler();
//		mPlayer.start();
//		new Thread(new Runnable() {
//			int i = 0;
//
//			@Override
//			public void run() {
//				while (mPlayer.isPlaying()) {
//					handler.post(new Runnable() {
//
//						@Override
//						public void run() {
//							mWordView.invalidate();
//						}
//					});
//					try {
//						Thread.sleep(mTimeList.get(i + 1) - mTimeList.get(i));
//					} catch (InterruptedException e) {
//					}
//					i++;
//					if (i == mTimeList.size() - 1) {
//						mPlayer.stop();
//						break;
//					}
//				}
//			}
//		}).start();
//	}
}