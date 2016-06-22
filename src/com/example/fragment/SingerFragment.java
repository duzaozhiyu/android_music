package com.example.fragment;

import com.example.activity.SetQueueActivity;
import com.example.addlisttool.ImageAndTextAdapter;
import com.example.my_android_music.R;
import com.example.sql.ArtistStructure;
import com.example.sql.MusicScanAritst;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingerFragment extends Fragment {
	
	private TextView tv_top_name;
	private ListView lv;
	private TextView tv_artist_Name;
	private TextView tv_num;
	private MusicScanAritst msa;
	private ImageButton imageB;
	

	private ImageAndTextAdapter<ArtistStructure> adapter;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    		Bundle savedInstanceState) {
	    	// TODO Auto-generated method stub
	    	//View view = inflater.inflate(R.layout.fragment_singer, container, false);
	    	//tv=(TextView) view.findViewById(R.id.textView);
	    	View view=inflater.inflate(R.layout.fragment_share, container, false);
	    	
	    	lv=(ListView) view.findViewById(R.id.fragment_share_list);
	    	imageB = (ImageButton) view.findViewById(R.id.searchBtn);
	    	msa=new MusicScanAritst(getActivity());
	    	tv_top_name=(TextView) view.findViewById(R.id.topTv_folder);
	    	tv_top_name.setText("∏Ë ÷");
	    	
	    	adapter=new ImageAndTextAdapter<ArtistStructure>(getActivity(),R.layout.list_frag_artist) {

				@Override
				protected void setViewId(int arg0, View view, ViewGroup arg2) {
					// TODO Auto-generated method stub
					ArtistStructure as=new ArtistStructure();
					as=(ArtistStructure) getItem(arg0);
					tv_artist_Name=(TextView) view.findViewById(R.id.list_artist_name);
					tv_num=(TextView) view.findViewById(R.id.tv_num);
					tv_artist_Name.setText(as.getArtist_name());
					tv_num.setText(as.getNum()+"");
				}
			};
			
			lv.setAdapter(adapter);
			adapter.setList(msa.getArtistList());
	    	
			lv.setOnItemClickListener(listener);
			
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
	
	    OnItemClickListener listener=new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView tv=(TextView) view.findViewById(R.id.list_artist_name);
				
				FragmentManager fm=getFragmentManager();
				MainFragment f=new MainFragment();
    			fm.beginTransaction().replace(R.id.content_fragment, f).commit();
			}
		};
}
