package com.example.fragment;

import java.util.List;

import com.example.activity.SetQueueActivity;
import com.example.addlisttool.ImageAndTextAdapter;
import com.example.my_android_music.R;
import com.example.sql.FolderStructure;
import com.example.sql.MusicScanFolder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FolderFragment extends Fragment {
	private TextView tv_top_name; 
	private ListView lv;
	private ImageAndTextAdapter<FolderStructure> adapter;
	private List<FolderStructure> list;
	private MusicScanFolder msf;
	private TextView tv_folder_Name;
	private TextView tv_folder_Path;
	private ImageButton imageB;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    		Bundle savedInstanceState) {
	    	// TODO Auto-generated method stub
	    	View view = inflater.inflate(R.layout.fragment_share, container, false);
	    	tv_top_name=(TextView) view.findViewById(R.id.topTv_folder);
	    	lv=(ListView) view.findViewById(R.id.fragment_share_list);
	    	imageB = (ImageButton) view.findViewById(R.id.searchBtn);
	    	tv_top_name.setText("ÎÄ¼þ¼Ð");
	    	msf=new MusicScanFolder(getActivity());
	    	list=msf.getFolderList();
	    	adapter=new ImageAndTextAdapter<FolderStructure>(getActivity(),R.layout.list_frag_folder) {

				@Override
				protected void setViewId(int arg0, View view, ViewGroup arg2) {
					// TODO Auto-generated method stub
					FolderStructure fs;
					fs=(FolderStructure) getItem(arg0);
					tv_folder_Name=(TextView) view.findViewById(R.id.folder_name);
					tv_folder_Path=(TextView) view.findViewById(R.id.folder_path);
					tv_folder_Name.setText(fs.getFolder_Name());
					tv_folder_Path.setText(fs.getFolder_Path());
				}
			};
	    	
			lv.setAdapter(adapter);
			adapter.setList(list);
	    	
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
	
	
	OnItemClickListener oic=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Toast.makeText(getActivity(),"dianji", 1).show();
			FragmentManager fm=getFragmentManager();
			MainFragment f=new MainFragment();
			fm.beginTransaction().replace(R.id.content_fragment, f).commit();
		}
	};
}
