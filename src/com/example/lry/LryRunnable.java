package com.example.lry;

import java.util.List;

import com.example.activity.IConstant;
import com.example.music_control.MusicControl;
import com.example.music_control.MyPrefference;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
public class LryRunnable implements Runnable{

	private Handler handler;
	private WordView wv;
	private List<LryStructure> list;
	private MusicControl mc;
	private MyPrefference mp;
	private Context context;
	private int index=0;
	//private MyPrefference mp=new MyPrefferenc(P)
	public LryRunnable (Handler handler,WordView wv,List<LryStructure> list,MusicControl mc,Context context)
	{
		this.handler=handler;
		this.wv=wv;
		this.list=list;
		this.mc=mc;
		this.context=context;
		mp=new MyPrefference(context);
	}
	
	public LryRunnable (Handler handler,WordView wv,List<LryStructure> list,MusicControl mc,Context context,int index)
	{
		this.handler=handler;
		this.wv=wv;
		this.list=list;
		this.mc=mc;
		this.context=context;
		mp=new MyPrefference(context);
		this.index=index;
	}
	
	@Override
	public void run() {
		
	
		System.out.println("线程开始"+index);
		// TODO Auto-generated method stub
		while (mc.getP_Statu()==1&&!Thread.interrupted()) {
		
			try {
				Message msg=new Message();
				msg.obj=String.valueOf(index);
				handler.sendMessage(msg);
				Thread.sleep(list.get(index + 1).getS_time()
						- list.get(index).getS_time());
				
			} catch (InterruptedException e) {
				
				System.out.println("线程中断");
				break;
			}
			index++;
			
			if (index== list.size()-1) {
				mc.stop();
				break;
			}
		  
		}
		
		Message msg=new Message();
		msg.obj=String.valueOf(index-1);
		handler.sendMessage(msg);
		//System.out.println("线程结束,时间索引为："+index);	
	}

}
