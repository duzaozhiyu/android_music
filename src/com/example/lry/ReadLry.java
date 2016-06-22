package com.example.lry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.activity.IConstant;
import com.example.sql.SqlTool;

import android.content.Context;

public class ReadLry implements IConstant {

	private Context context;

	private String filepath = null;

	public ReadLry(Context context) {
		this.context = context;

	}

	public ReadLry() {
	}

	// 处理时间字符串和歌词字符串
	public LryStructure changeToTimeandLry(String read) {
		LryStructure lry=null;
        String regex="(\\[)([\\d]{2}:[\\d]{2}.[\\d]{2}\\])(.*)";
        if(read.matches(regex))
        {
        	
        	lry=new LryStructure();
        	String timeAndWord[]=read.split("\\]|\\[");//分割时间
        	int i=0;
        	for(i=0;i<timeAndWord.length;i++)
        	{
        	  
        	  if(timeAndWord[i].matches("(\\d{2}.*)"))
        	  {
        		  lry.setS_time(changeToTime(timeAndWord[i]));
        		 
        	  }else
        	  {
        		  if(timeAndWord[i].equals(""))
        		  {
        			  continue;
        		  }
        		  else
        		  {
        		  lry.setS_lry(timeAndWord[i]);
        		  }
        	  }
        	 // System.out.println(timeAndWord[i]);
        	}
        	if(i<3)
        	{
        		lry.setS_lry(" ");
        	}
        	
        }
		return lry;
	}

	// 处理时间
	public int changeToTime(String read) {
		int time=0 ;
	    String t[]=read.split(":|\\.");
	    time=Integer.valueOf(t[0])*60*1000;
	    time+=Integer.valueOf(t[1])*1000;
	    time+=Integer.valueOf(t[2]);
		return time;
	}

	// 读取文件并处理
	public List<LryStructure> readFile(String filePath) {
		List<LryStructure> list=new ArrayList<LryStructure>();
		if(filePath==null)
		{
			return list;
		}
		File file=new File(filePath);
		
		if(!file.exists())
			return list; 
		
		try {
			FileReader fReader = new FileReader(file);
			
			BufferedReader br=new BufferedReader(fReader);
			String readLine=null;
			while((readLine=br.readLine())!=null)
			{
				LryStructure lry=changeToTimeandLry(readLine);
				if(lry!=null)
				{
					list.add(lry);
				}
				//System.out.println(readLine);				
			}
			br.close();
			fReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
//	
}
