package com.example.fragment;

import com.example.my_android_music.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;


public class SetFragment extends Fragment {
	  
	private RotateAnimation ra;
	//private Button btn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.fragment_set,container,false);
		ra=new RotateAnimation(0, 180, 10, 20);
		ra.setDuration(1000);
		
		//btn=(Button) view.findViewById(R.id.btn_set);
		//btn.startAnimation(ra);
		return view;
	}
}
