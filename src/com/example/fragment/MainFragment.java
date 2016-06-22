package com.example.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.activity.IConstant;
import com.example.activity.SetQueueActivity;
import com.example.addlisttool.ImageAndTextAdapter;
import com.example.addlisttool.ViewPagerAdapter;
import com.example.lry.LryRunnable;
import com.example.lry.LryStructure;
import com.example.lry.ReadLry;
import com.example.lry.WordView;
import com.example.music_control.MusicControl;
import com.example.music_control.MyPrefference;
import com.example.my_android_music.R;
import com.example.service.MusicService;
import com.example.service.MusicService.MusicServiceBinder;
import com.example.sql.MusicScan;
import com.example.sql.SingStructure;
import com.example.sql.SqlTool;
import com.example.time.TimeTool;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainFragment extends Fragment implements IConstant {

	private ListView lv, listlry;
	private ArrayList<String> al;
	private ArrayAdapter<String> adt;
	private ImageButton imageB,btn_volume;
	private ImageButton btn_play,btn_play2;
	private ImageButton btn_pause,btn_pause2;
	private ImageButton btn_next;
	private ImageAndTextAdapter<SingStructure> adapter;
	private TextView tv_sname;
	private TextView tv_artist;
	private SqlTool sql_t;
	private MusicControl mc;
	private TextView tv_time;
	private ProgressBar pb;
	private TimeTool tt;
	private Handler mHandler;
	private ImageView IV_lrc;
	private Intent intent;
	private MusicService ms;
	private ServiceConnection sc;

	private Intent serviceIntent;
	private List<View> views;
	private ViewPagerAdapter vpAdapter;
	private ViewPager vp;
	private SeekBar seekbar,mVolumeSeekBar;

	private ReadLry rl;
	private WordView wv;
	private Thread thread;
	private MyPrefference mp;
	private TextView tv_time_p,tv_time_a;
	private LinearLayout mVolumeLayout;
	private AudioManager am;
	//private int currentVolume=4;
	private int maxVolume;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.frag_main_view, container, false);
		intiViews(view);
		btn_play.setOnClickListener(onc);
		btn_pause.setOnClickListener(onc);
		btn_next.setOnClickListener(onc);
		IV_lrc.setOnClickListener(onc);
		
		
		btn_play2.setOnClickListener(onc);
		btn_pause2.setOnClickListener(onc);
		
		seekbar.setOnSeekBarChangeListener(osbc);
		mVolumeSeekBar.setOnSeekBarChangeListener(ooseekbar);
		btn_volume.setOnClickListener(onc);
		
		
		am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
		  
		maxVolume= am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		
		mVolumeSeekBar.setMax(maxVolume); 
		
		
		int currentVolume=am.getStreamVolume(AudioManager.STREAM_MUSIC);
		System.out.println("当前系统音量"+currentVolume);
		mVolumeSeekBar.setProgress(currentVolume);
		
		tt = new TimeTool(mHandler);
		rl = new ReadLry();
		mp = new MyPrefference(getActivity());

		if (sql_t.hasData()) {
			adapter.setList(sql_t.getMusicList());
			// System.out.println(sql_t.getWordPath(1));
		} else {
			MusicScan ms = new MusicScan(getActivity());
			sql_t.setList(ms.getSing());
			sql_t.inSert();
			sql_t.insert_WORD();
			// System.out.println(sql_t.getWordPath(1));
			adapter.setList(ms.getSing());
		}
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(oic);

		imageB.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent startScan = new Intent(getActivity(),
						SetQueueActivity.class);
				startActivity(startScan);
			}
		});

		return view;
	}

	public void intiViews(View view) {

		LayoutInflater layout = LayoutInflater.from(getActivity());
		views = new ArrayList<View>();
		views.add(layout.inflate(R.layout.fragment_main, null));
		views.add(layout.inflate(R.layout.fragment_lry, null));
		vpAdapter = new ViewPagerAdapter(getActivity(), views);

		vp = (ViewPager) view.findViewById(R.id.viewpager);
		vp.setAdapter(vpAdapter);

		serviceIntent = new Intent(getActivity(), MusicService.class);
		sc = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName arg0) {
			}

			@Override
			public void onServiceConnected(ComponentName arg0, IBinder arg1) {
				// TODO Auto-generated method stub
				ms = ((MusicService.MusicServiceBinder) arg1).getService();
				
			}
		};
		getActivity().bindService(serviceIntent, sc, Context.BIND_AUTO_CREATE);
		lv = (ListView) views.get(0).findViewById(R.id.fragment_main_list);

		pb = (ProgressBar) views.get(0).findViewById(R.id.playback_seekbar2);
		imageB = (ImageButton) views.get(0).findViewById(R.id.searchBtn);
		btn_play = (ImageButton) views.get(0).findViewById(R.id.btn_play2);
		btn_pause = (ImageButton) views.get(0).findViewById(R.id.btn_pause2);
		btn_next = (ImageButton) views.get(0).findViewById(R.id.btn_playNext2);
		IV_lrc = (ImageView) views.get(0).findViewById(R.id.headicon_iv);

		seekbar = (SeekBar) views.get(1).findViewById(R.id.playback_seekbar);
		mVolumeSeekBar=(SeekBar) views.get(1).findViewById(R.id.volume_seekbar);
        btn_volume=(ImageButton) views.get(1).findViewById(R.id.btn_volume);
        mVolumeLayout=(LinearLayout) views.get(1).findViewById(R.id.volumeLayout);
        
        btn_play2=(ImageButton) views.get(1).findViewById(R.id.btn_play);
        btn_pause2=(ImageButton) views.get(1).findViewById(R.id.btn_pause);
        
		wv = (WordView) views.get(1).findViewById(R.id.lyricshow);
		tv_time_p= (TextView) views.get(1).findViewById(R.id.currentTime_tv);
		tv_time_a= (TextView) views.get(1).findViewById(R.id.totalTime_tv);
		sql_t = new SqlTool(getActivity());
		adapter = new ImageAndTextAdapter<SingStructure>(getActivity(),
				R.layout.list_frag_sing) {

			@Override
			protected void setViewId(int arg0, View view, ViewGroup arg2) {
				// TODO Auto-generated method stub
				tv_sname = (TextView) view.findViewById(R.id.music_name);
				tv_artist = (TextView) view.findViewById(R.id.artist);
				tv_time = (TextView) view.findViewById(R.id.time);
				SingStructure ss = (SingStructure) getItem(arg0);
				tv_sname.setText(ss.getS_name());
				tv_artist.setText(ss.getSinger_name());
				tv_time.setText(TimeTool.Concat(ss.getS_time()));
				
			}
		};

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				refishBar();
				//System.out.println(Thread.activeCount());
			}
		};
	}

	OnItemClickListener oic = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			if (mc == null) {
				mc = ms.getMControl();
				mc.setList(sql_t.getMusicList());

			}

			if (btn_play.getVisibility() == View.VISIBLE) {
				btn_play.setVisibility(View.GONE);
				btn_pause.setVisibility(View.VISIBLE);
				btn_play2.setVisibility(View.GONE);
				btn_pause2.setVisibility(View.VISIBLE);
				
			}		
			if(mc.getP_Statu()==P_PLAYING)
			{
				mc.stop();
			}
			mc.playById(arg2);
			
			showlry(mc.getSname(),mc.getOnlySname());

		}
	};

	public void refishBar() {
		if (mc == null) {
			return;
		} else {
			pb.setProgress(mc.getDu());
			seekbar.setProgress(mc.getDu());
			tv_time_p.setText(TimeTool.Concat(mc.getDu()));
		}
	}

	OnClickListener onc = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			if (mc == null) {
				mc = ms.getMControl();
				mc.setList(sql_t.getMusicList());
			}
			int id = view.getId();
			switch (id) {
			case R.id.btn_play2:
				int s = mc.getP_Statu();
				if (s == P_PREPARE || s == P_STOP) {
					btn_play.setVisibility(View.GONE);
					btn_pause.setVisibility(View.VISIBLE);
					btn_play2.setVisibility(View.GONE);
					btn_pause2.setVisibility(View.VISIBLE);
					mc.playById(mc.getIndext());
					tt.startTime();
					
				}
                if(s==P_PAUSED)
                {
                	btn_play.setVisibility(View.GONE);
					btn_pause.setVisibility(View.VISIBLE);
					btn_play2.setVisibility(View.GONE);
					btn_pause2.setVisibility(View.VISIBLE);
					purseToPlay(0);
		
                }
				break;
			case R.id.btn_pause2:
				if (mc.getP_Statu() == P_PLAYING) {
					btn_play.setVisibility(View.VISIBLE);
					btn_pause.setVisibility(View.GONE);
					btn_play2.setVisibility(View.VISIBLE);
					btn_pause2.setVisibility(View.GONE);
					mc.paused();
					tt.stopTime();
					
				}

				break;
			case R.id.btn_playNext2:
				mc.next(mc.getIndext());
				btn_play.setVisibility(View.GONE);
				btn_pause.setVisibility(View.VISIBLE);
				btn_play2.setVisibility(View.GONE);
				btn_pause2.setVisibility(View.VISIBLE);
                showlry(mc.getSname(),mc.getOnlySname());
				break;
			case R.id.btn_volume:
				
				
				if(mVolumeLayout.isShown())
				{
					mVolumeLayout.setVisibility(View.INVISIBLE);					
				}
				else
				{
					mVolumeLayout.setVisibility(View.VISIBLE);
					//mVolumeSeekBar.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
				}
				break;
			case R.id.btn_play:
				int s1 = mc.getP_Statu();
				if (s1 == P_PREPARE || s1 == P_STOP) {
					btn_play2.setVisibility(View.GONE);
					btn_pause2.setVisibility(View.VISIBLE);
					btn_play.setVisibility(View.GONE);
					btn_pause.setVisibility(View.VISIBLE);
					mc.playById(mc.getIndext());
					tt.startTime();
					
				}
                if(s1==P_PAUSED)
                {
                	btn_play2.setVisibility(View.GONE);
					btn_pause2.setVisibility(View.VISIBLE);
					btn_play.setVisibility(View.GONE);
					btn_pause.setVisibility(View.VISIBLE);
					purseToPlay(0);
		
                }
				break;
			case R.id.btn_pause:
				if (mc.getP_Statu() == P_PLAYING) {
					btn_play2.setVisibility(View.VISIBLE);
					btn_pause2.setVisibility(View.GONE);
					btn_play.setVisibility(View.VISIBLE);
					btn_pause.setVisibility(View.GONE);
					mc.paused();
					tt.stopTime();
					
				}

				break;
			default:
				break;
			}
		}
	};

	
	OnSeekBarChangeListener osbc=new OnSeekBarChangeListener() {
		
		int time=0;
		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			//List<LryStructure> list = rl.readFile(sql_t.getWordPath(mc.getSname()));
			 if(mc.getP_Statu()==P_PAUSED)
             {
             	    btn_play.setVisibility(View.GONE);
					btn_pause.setVisibility(View.VISIBLE);
					purseToPlay(time);
					//arg0.setProgress(list.get(time).getS_time());
					
             }
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			if (mc.getP_Statu() == P_PLAYING) {
				btn_play.setVisibility(View.VISIBLE);
				btn_pause.setVisibility(View.GONE);
				mc.paused();
				tt.stopTime();
			}
		}
		
		@Override
		public void onProgressChanged(SeekBar arg0, int process, boolean arg2) {
			// TODO Auto-generated method stub
			List<LryStructure> list = rl.readFile(sql_t.getWordPath(mc.getSname()));
			for(int i=0;i<list.size();i++)
			{
				if(process<list.get(i).getS_time())
				{
					time=i;
					break;
				}
				time=i-1;
			}
			tv_time_p.setText(TimeTool.Concat(list.get(time).getS_time()));
			System.out.println("kuaijingdao"+time);
		}
	};
	
	
	OnSeekBarChangeListener ooseekbar =new OnSeekBarChangeListener() {
		

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
			// TODO Auto-generated method stub
			am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
		}
	};
	
	
	OnSeekCompleteListener oscl=new OnSeekCompleteListener() {
		
		@Override
		public void onSeekComplete(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	public void onDestroy() {
		super.onDestroy();
		tt.stopTime();
		//getActivity().unbindService(sc);
		//ms.unbindService(sc);
	};

	
	public void showlry(String mSingName,String name)
	{
		
		pb.setMax(sql_t.getMusicTime(name));
		seekbar.setMax(sql_t.getMusicTime(name));
		tv_time_a.setText(TimeTool.Concat(sql_t.getMusicTime(name)));
		tt.stopTime();
		tt.startTime();
        
		while (thread != null && thread.isAlive()) {
			thread.interrupt();
		}
		List<LryStructure> list = rl.readFile(sql_t.getWordPath(mSingName));
		wv.setList(list);
		if (list.size() != 0) {
			Handler handler = new Handler(){
				public void handleMessage(Message msg) {
					wv.invalidate();
				};
			};
			LryRunnable lr = new LryRunnable(handler, wv, list, mc,
					getActivity());
			
				thread = new Thread(lr);
				mc.setP_Statu(P_PLAYING);
				thread.start();
		}
		
	}
	
	
	
	
	public void purseToPlay(final int itemIndex)
	{
		List<LryStructure> list = rl.readFile(sql_t.getWordPath(mc.getSname()));
		
		
		
		
		while (thread != null && thread.isAlive()) {
			thread.interrupt();			
		}
		if(itemIndex==0)
		{
			mc.seekTo(list.get(wv.getIndex()).getS_time());
		}
		else
		{
			mc.seekTo(itemIndex);
		}

		tt.startTime();
		Handler handler = new Handler(){
			public void handleMessage(Message msg) {
				
				if(itemIndex==0)
				{
					wv.setIndex(Integer.valueOf((String) msg.obj));
				}
				else
				{
					wv.setIndex(itemIndex);
				}
				wv.invalidate();
			};
		};
		LryRunnable lr;
		if(itemIndex==0)
		{
			 lr= new LryRunnable(handler, wv, list, mc,
					getActivity(),wv.getIndex());
		}
		else
		{
			 lr= new LryRunnable(handler, wv, list, mc,
						getActivity(),itemIndex);
		}
		
		thread = new Thread(lr);
		thread.start();
	}
}
