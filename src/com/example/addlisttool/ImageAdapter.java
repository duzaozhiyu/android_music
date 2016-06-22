package com.example.addlisttool;

import com.example.my_android_music.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	
	private int  imagedateID[]={R.drawable.icon_out,R.drawable.icon_local_music,R.drawable.icon_folder_plus,R.drawable.icon_artist_plus,R.drawable.icon_album_plus,R.drawable.icon_favorite};
	
	public Context getContext()
	{
		return context;
	}
	public ImageAdapter(Context context)
	{
		this.context=context;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imagedateID.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return imagedateID[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		 LinearLayout ll=null;
		 if(view!=null)
		 {
			 ll=(LinearLayout) view;
		 }else
		 {
			 ll=(LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.left_list_item, null);
		 }
		 
		 ImageView iv=(ImageView) ll.findViewById(R.id.btn_image);
		 iv.setImageResource((Integer) getItem(position));
		return ll;
	}

}
