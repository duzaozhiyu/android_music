package com.example.music_control;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.activity.IConstant;

public class MyPrefference implements IConstant{
   private Context context;
   private SharedPreferences sp;
   private static MyPrefference preference = null;
   private String packageName = "";
   
   public static synchronized MyPrefference getInstance(Context context){
		if(preference == null)
			preference = new MyPrefference(context);
		return preference;
	}
	
	
	public MyPrefference(Context context){
		packageName = context.getPackageName() + "_preferences";
		sp = context.getSharedPreferences(
				packageName, context.MODE_PRIVATE);
	}
   
   
   
//   public MyPrefference(Context context)
//   {
//	   this.context=context;
//	   sp=context.getSharedPreferences("mypreferences",Context.MODE_PRIVATE);
//	  
//   }
   
   public void setModle(int modle)
   {
	   Editor ed=sp.edit();
	   ed.putInt(PLAY_MODLE, modle);
	   ed.commit();   
   }
   
   public int getModle()
   {
	   return sp.getInt(PLAY_MODLE, 1);
   }
   
   
   public void setStatu(int statu)
   {
	   Editor ed=sp.edit();
	   ed.putInt(PLAY_STATU, statu);
	   ed.commit();
   }
   
   public int getStatu()
   {
	   return sp.getInt(PLAY_STATU, 1);
   }
   
   
  
}
