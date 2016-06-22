package com.example.my_android_music;

import java.util.ArrayList;





import com.example.addlisttool.ImageAdapter;
import com.example.fragment.FavoriteFragment;
import com.example.fragment.FolderFragment;
import com.example.fragment.MainFragment;
import com.example.fragment.SingerFragment;
import com.example.fragment.AblumsFragment;
import com.example.music_control.MusicControl;
import com.example.service.MusicService;
import com.example.service.MusicService.MusicServiceBinder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

@SuppressLint("CommitTransaction") public class MainActivity extends Activity implements OnItemClickListener{

	private ListView lv;
	private ArrayAdapter<String> adt;
	//private ImageAdapter ia;
	private ArrayList<String> menu_List;
	private DrawerLayout mDrawerLayout;
	private FrameLayout fl;
	private Fragment f;
//	private Intent intent;
	private MusicService ms=null;
	private ServiceConnection sc;
	
	private Intent serviceIntent;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lv=(ListView)findViewById(R.id.drawerlist);
        fl=(FrameLayout) findViewById(R.id.content_fragment);
    	mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
    	f=new MainFragment();
    	FragmentManager fm=getFragmentManager();
    			fm.beginTransaction().replace(R.id.content_fragment, f).commit();
        lv.setAdapter(new ImageAdapter(this));
        lv.setOnItemClickListener(this);
        
        
        
		serviceIntent = new Intent(this, MusicService.class);
		
        
        
    }

//    
    
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		//插入一个Fragment
		FragmentManager fm=getFragmentManager();
		//fm.beginTransaction().replace(R.id.content_fragment, fragment).commit();
		this.setFragment(f,arg2,fm);
		mDrawerLayout.closeDrawer(lv);
		//System.out.println(String.valueOf(arg2));
		Log.e("输出列表号", arg2+"");
	}
	
	public void setFragment(Fragment f,int arg2,FragmentManager fm)
	{
		switch(arg2)
		{
		 case 0: break;
		 case 1:
		 if(f==null) 
		 {
	         f=new MainFragment();fm.beginTransaction().replace(R.id.content_fragment, f).commit();
	         Log.e("新的Fragment","new");
		 }
	     else
	     {
	    	 fm.beginTransaction().replace(R.id.content_fragment, f).commit();
	    	 Log.e("旧的Fragment","old");
	     }
//		 ScaleAnimation sa=new ScaleAnimation(0, 1, 0, 1);
//		 sa.setDuration(500);
//		 LayoutAnimationController lsa=new LayoutAnimationController(sa,0);
//		 fl.setLayoutAnimation(lsa);
		 break;
		 case 2:f=new FolderFragment();fm.beginTransaction().replace(R.id.content_fragment, f).commit();break;
		 case 3:f=new SingerFragment();fm.beginTransaction().replace(R.id.content_fragment, f).commit();
//		 ScaleAnimation sa2=new ScaleAnimation(0, 1, 0, 1);
//		 sa2.setDuration(500);
//		 LayoutAnimationController lsa2=new LayoutAnimationController(sa2,0);
		 //fl.setLayoutAnimation(lsa);
		 break;
		 case 4:f=new AblumsFragment();fm.beginTransaction().replace(R.id.content_fragment, f).commit();break;
		 case 5:f=new FavoriteFragment();fm.beginTransaction().replace(R.id.content_fragment, f).commit();break;
		 default :;
		 
		}
	}
    
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
//		stopService(intent);
		
		//unbindService(sc);
		stopService(serviceIntent);
		super.onDestroy();
	}


}
