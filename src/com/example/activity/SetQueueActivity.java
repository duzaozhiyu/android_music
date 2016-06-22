package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.addlisttool.ImageAndTextAdapter;
import com.example.music_control.MyPrefference;
import com.example.my_android_music.R;
import com.example.my_android_music.R.drawable;
import com.example.sql.ArtistStructure;
import com.example.sql.MusicScan;
import com.example.sql.SingStructure;
import com.example.sql.SqlTool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SetQueueActivity extends Activity implements OnItemClickListener,
		IConstant {

	private ListView lv;
	private ArrayList<ArtistStructure> al;
	private ImageAndTextAdapter<ArtistStructure> adapter;
	private ImageView imageb;
	private TextView tv;
	private MyPrefference mp;
	private int ID_modle[]={R.drawable.icon_playing_mode_repeat_all,R.drawable.icon_sequence,R.drawable.icon_shuffle,R.drawable.icon_single_repeat};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setqueue);
		lv = (ListView) findViewById(R.id.setqueue_list);
		mp=new MyPrefference(SetQueueActivity.this);

		String setup[] = { "扫描歌曲", "列表循环", "换背景", "睡眠", "设置", "退出" };
		int R_id[] = { R.drawable.icon_search_dark, R.drawable.icon_sequence,
				R.drawable.icon_change_background, R.drawable.icon_sleep_mode,
				R.drawable.icon_preferences_dark, R.drawable.icon_exit };

		adapter = new ImageAndTextAdapter<ArtistStructure>(this,
				R.layout.list_frag_setqueue) {

			@Override
			protected void setViewId(int arg0, View view, ViewGroup arg2) {
				// TODO Auto-generated method stub
				ArtistStructure ast = (ArtistStructure) getItem(arg0);
				tv = (TextView) view.findViewById(R.id.setqueue_tv);
				tv.setText(ast.getArtist_name());
				imageb = (ImageView) view.findViewById(R.id.setqueue_image);
				imageb.setImageResource(ast.getNum());

			}
		};
		al = new ArrayList<ArtistStructure>();
		for (int x = 0; x < setup.length; x++) {
			if(x==1)
			{
				ArtistStructure as = new ArtistStructure(setup[x],ID_modle[mp.getModle()]);
				al.add(as);
				continue;
			}
			ArtistStructure as = new ArtistStructure(setup[x],R_id[x]);
			al.add(as);
		
			
		}
		adapter.setList(al);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);

	}

	@Override
	public void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		View view = getWindow().getDecorView();
		WindowManager.LayoutParams lp = (WindowManager.LayoutParams) view
				.getLayoutParams();
		lp.gravity = Gravity.RIGHT | Gravity.TOP;
		lp.x = getResources().getDimensionPixelSize(
				R.dimen.playqueue_dialog_marginright);
		lp.y = getResources().getDimensionPixelSize(
				R.dimen.setqueue_dialog_margintop);
		lp.width = getResources().getDimensionPixelSize(
				R.dimen.setqueue_dialog_width);
		lp.height = getResources().getDimensionPixelSize(
				R.dimen.setqueue_dialog_height);
		getWindowManager().updateViewLayout(view, lp);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
		// TODO Auto-generated method stub
		switch (arg2) {

		// 得加个线程
		case 0:
			Intent intent = new Intent(SetQueueActivity.this, MusicScanActivity.class);
			//intent.setClass();
			startActivity(intent);
			this.finish();
			break;
		case 1:
			int model=mp.getModle();
			if(model>=3)
			{
				mp.setModle(0);
				imageb=(ImageView) view.findViewById(R.id.setqueue_image);
				imageb.setImageResource(ID_modle[0]);
			}
			else
			{
				mp.setModle(model+1);
				imageb=(ImageView) view.findViewById(R.id.setqueue_image);
				imageb.setImageResource(ID_modle[model+1]);
			}
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		default:
			break;

		}
	}

}
