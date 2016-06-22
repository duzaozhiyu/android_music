package com.example.sql;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.activity.IConstant;
import com.example.lry.ReadLry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SqlTool implements IConstant {
    private SQLiteDatabase dbWrite;
    private SQLiteDatabase dbRead;
    private List<SingStructure> list;
    private Context context;
    private MusicDB db;
    private MusicScanFolder msf;
   
    public SqlTool(List<SingStructure> list,Context context)
    {
    	this.list=list;
    	this.context=context;
    }
    public SqlTool(Context context)
    {
    	this.context=context;
    }
    public void setList(List <SingStructure> list)
    {
    	this.list=list;
    }
    //插入音乐表
    public void inSert()
    {
    	db=new MusicDB(context);
    	ContentValues cv=new ContentValues();
    	dbWrite=db.getWritableDatabase();
    	for(int i=0;i<list.size();i++)
    	{
    	 
    	  cv.put("TP_ID_S",list.get(i).getTp_id_s());
    	  cv.put("S_NAME", list.get(i).getS_name());
    	  cv.put("ALBUM", list.get(i).getAlbum());
    	  cv.put("S_PATH",list.get(i).getS_path());
    	  cv.put("ARTIST", list.get(i).getSinger_name());
    	  cv.put("TP_ID_W",list.get(i).getTp_id_w());
    	  cv.put("FAVORITE",list.get(i).getFavorite());
    	  cv.put("TIME",list.get(i).getS_time());
    	  dbWrite.insert(TABLE_SING, null, cv);
    	}
    	//dbWrite.insert(TABLE_SING, null, cv);
    	dbWrite.close();
    }
    
    //读取音乐表
    public List<SingStructure> getMusicList()
    {
    	db=new MusicDB(context);
    	List<SingStructure> list=new ArrayList<SingStructure>();
    	dbRead=db.getReadableDatabase();
    	String sql="select * from "+TABLE_SING;
    	Cursor cursor=dbRead.rawQuery(sql, null);
        
    	while(cursor.moveToNext())
    	{
    		SingStructure ss=new SingStructure();
    		ss.setTp_id_s(cursor.getInt(cursor.getColumnIndex("TP_ID_S")));
    		ss.setAlbum(cursor.getString(cursor.getColumnIndex("ALBUM")));
    		ss.setFavorite(cursor.getInt(cursor.getColumnIndex("FAVORITE")));
    		ss.setS_name(cursor.getString(cursor.getColumnIndex("S_NAME")));
    		ss.setS_path(cursor.getString(cursor.getColumnIndex("S_PATH")));
    		ss.setS_time(cursor.getLong(cursor.getColumnIndex("TIME")));
    		ss.setTp_id_w(cursor.getInt(cursor.getColumnIndex("TP_ID_W")));
    		ss.setSinger_name(cursor.getString(cursor.getColumnIndex("ARTIST")));
    		list.add(ss);
    	}
    	cursor.close();
    	dbRead.close();
    	return  list;
    }
    
    
    public void setUpFavorite(int i,String singName)
    {
    	db=new MusicDB(context);
    	dbWrite=db.getWritableDatabase();
    	
    	String sql="update "+TABLE_SING+" where S_NAME=?";
    	Cursor cursor=dbWrite.rawQuery(sql, new String []{singName});
    	cursor.close();
    	dbWrite.close();
    }
    //判断数据库是否有数据
    public boolean hasData()
    {
    	db=new MusicDB(context);
    	dbRead=db.getReadableDatabase();
    	String sql="select * from "+TABLE_SING;
    	Cursor cursor = dbRead.rawQuery(sql, null);
		boolean has = false;
		if(cursor.moveToFirst()) {
			int count = cursor.getInt(0);
			if(count > 0) {
				has = true;
			}
		}
		cursor.close();
		dbRead.close();
		return has;
    }
    //删除数据库的内容
    public void deleteTable(String tableName)
    {
    	db=new MusicDB(context);
    	dbWrite=db.getWritableDatabase();
    	dbWrite.delete(tableName, null, null);
    	
    	dbWrite.close();
    }
    
   
    
    //插入歌词表
    public void insert_WORD()
    {
    	
    	db=new MusicDB(context);
    	dbWrite=db.getWritableDatabase();
    	msf=new MusicScanFolder(context);
    	List<FolderStructure> list=new ArrayList<FolderStructure>();
    	list=msf.getFolderList();
    	ContentValues cv=new ContentValues();
    	for(int i=0;i<list.size();i++)
    	{
    		File file=new File(list.get(i).getFolder_Path()+"/lyric");
    		File fileNext[]=file.listFiles();
    		for(int x=0;x<fileNext.length;x++)
    		{
    			if(fileNext[x].isFile())
    			{
    				cv.put("W_PATH",fileNext[x].getAbsolutePath());
    				cv.put("W_NAME", fileNext[x].getName());
    				
    			}
    			dbWrite.insert(TABLE_SING_WORD,null,cv);
    		}
    	}
    	cv.clear();
    	dbWrite.close();
    }
    
    //获取歌词路径
    public String getWordPath(String M_name)
    {
    	String path=null;
    	db=new MusicDB(context);
    	dbRead=db.getReadableDatabase();
    	String sql="select * from "+TABLE_SING_WORD
    	+" where W_NAME like ?";
    	Cursor cursor=dbRead.rawQuery(sql, new String []{M_name});
    	while(cursor.moveToNext())
    	{
    		path=cursor.getString(cursor.getColumnIndex("W_PATH"));
    		
    	}
    	cursor.close();
    	dbRead.close();
    	return path;
    }
    
    //获取时长
    public int getMusicTime(String name)
    {
    	dbRead=db.getReadableDatabase();
    	String sql="select TIME from "+TABLE_SING+" where S_NAME=?";
    	Cursor cursor=dbRead.rawQuery(sql, new String []{name});
    	int time=0;
    	while(cursor.moveToNext())
    	{
    	   time=cursor.getInt(cursor.getColumnIndex("TIME"));	
    	}
    	cursor.close();
    	dbRead.close();
    	return time;
    }
    
    
    //按文件路劲查找
    
    public List<SingStructure> getMusicListByFolder(String path)
    {
    	String n_path=path+"%";
    	String sql="Seletc * from "+TABLE_SING+" where S_PATH like ?";
    	List<SingStructure> listFolder=getMusicListBy(sql,n_path);
    	return listFolder;
    }
    //ARTIST
    public List<SingStructure> getMusicListByArtist(String artist)
    {
    	String sql="Seletc * from "+TABLE_SING+" where ARTIST=?";
    	List<SingStructure> listFolder=getMusicListBy(sql,artist);
    	return listFolder;
    }
    
    //ALBUM
    public List<SingStructure> getMusicListByAlbum(String album)
    {
    	String sql="Seletc * from "+TABLE_SING+" where ALBUM=?";
    	List<SingStructure> listFolder=getMusicListBy(sql,album);
    	return listFolder;
    }
    
    
    
    //获取歌词列表
    public List<SingStructure> getMusicListBy(String sql,String x)
    {
    	db=new MusicDB(context);
    	List<SingStructure> list=new ArrayList<SingStructure>();
    	dbRead=db.getReadableDatabase();
    	//String sql="select * from "+TABLE_SING;
    	Cursor cursor=dbRead.rawQuery(sql, new String[]{x});
    
    	while(cursor.moveToNext())
    	{
    		SingStructure ss=new SingStructure();
    		ss.setTp_id_s(cursor.getInt(cursor.getColumnIndex("TP_ID_S")));
    		ss.setAlbum(cursor.getString(cursor.getColumnIndex("ALBUM")));
    		ss.setFavorite(cursor.getInt(cursor.getColumnIndex("FAVORITE")));
    		ss.setS_name(cursor.getString(cursor.getColumnIndex("S_NAME")));
    		ss.setS_path(cursor.getString(cursor.getColumnIndex("S_PATH")));
    		ss.setS_time(cursor.getLong(cursor.getColumnIndex("TIME")));
    		ss.setTp_id_w(cursor.getInt(cursor.getColumnIndex("TP_ID_W")));
    		ss.setSinger_name(cursor.getString(cursor.getColumnIndex("ARTIST")));
    		list.add(ss);
    	}
    	cursor.close();
    	dbRead.close();
    	return  list;
    }
    
}
