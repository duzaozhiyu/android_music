package com.example.addlisttool;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public abstract class ImageAndTextAdapter<T> extends BaseAdapter {

	private Context context;
	private int r_id;
	
	private List<T> list=new ArrayList<T>();
	
	public ImageAndTextAdapter(Context context,int r_id)
	{
		this.context=context;
		this.r_id=r_id;
	}
	
	public Context getContext()
	{
		return context;
	}
	
	public void setList(List<T> list)
	{
		this.list=list;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		LinearLayout ll=null;
		if(view!=null)
		{
			ll=(LinearLayout) view;
		}
		else
		{
			ll=(LinearLayout) LayoutInflater.from(getContext()).inflate(r_id, null);
		}
		setViewId(arg0,ll,arg2);
		return ll;
	}
  protected abstract void setViewId(int arg0, View view, ViewGroup arg2);

 
}
