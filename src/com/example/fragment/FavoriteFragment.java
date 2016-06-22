package com.example.fragment;

import java.util.ArrayList;

import com.example.activity.PlayQueueActivity;
import com.example.activity.SetQueueActivity;
import com.example.my_android_music.R;

import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore.Audio.AlbumColumns;
import android.provider.MediaStore.Audio.Albums;
import android.provider.MediaStore.Audio.AudioColumns;
import android.provider.MediaStore.MediaColumns;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class FavoriteFragment extends Fragment {

	private TextView tv_top_name;
	private ImageButton ib;
	private ListView lv;
	private ArrayList<String> al;
	private ArrayAdapter<String> adp;
	public static final int FILTER_SIZE = 1 * 1024 * 1024;// 1MB
	public static final int FILTER_DURATION = 1 * 60 * 1000;// 1分钟
	private static String[] proj_album;
	private ImageButton imageB;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_share, container, false);
		lv = (ListView) view.findViewById(R.id.fragment_share_list);
		imageB = (ImageButton) view.findViewById(R.id.searchBtn);
		al = new ArrayList<String>();

		tv_top_name = (TextView) view.findViewById(R.id.topTv_folder);
		tv_top_name.setText("我的最爱");
		ib = (ImageButton) view.findViewById(R.id.btn_menu2);
//		
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
}
