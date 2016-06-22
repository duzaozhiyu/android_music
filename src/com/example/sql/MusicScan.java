package com.example.sql;

import java.util.ArrayList;
import java.util.List;

import com.example.activity.IConstant;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.AudioColumns;
import android.provider.MediaStore.MediaColumns;

public class MusicScan implements IConstant{
	private Context context;
	private String selection=null;
	private Uri uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	public MusicScan(Context context)
	{
		this.context=context;
	}
	public MusicScan(Context context,String selection)
	{
		this.context=context;
		this.selection=selection;
	}
    //ContentResolver cr=context.getContentResolver();
    //Cursor cursor=cr.query(uri, projection, selection, selectionArgs, sortOrder)
    /*
     * uri:查询的数据库的名称
     * projection:查询数据库的哪几列
     * selection :查询条件
     * selectionArgs:替换符
     * sortOrder：查询结果排序顺序
     */
    //Cursor cursor=cr.query(uri, null, selection, null,SORT_ORDER);
    //获取歌曲的全部信息
    public List<SingStructure> getSing()
    {
    	ContentResolver cr=context.getContentResolver();
    	Cursor cursor=cr.query(uri, null, selection, null,SORT_ORDER);
//    	int singCount=0;
//    	SingStructure []ss =null;
    	
    	List<SingStructure> list=new ArrayList<SingStructure>();
    	while(cursor.moveToNext())
    	{
    		SingStructure ss=new SingStructure();
    		ss.setTp_id_s(getMusicID(cursor)); 
    		ss.setAlbum(getAlbumName(cursor));
    		ss.setS_name(getMusicName(cursor));
    		ss.setS_path(getFolderPath(cursor));
    		ss.setFavorite(0);
    		ss.setSinger_name(getArtist(cursor));
    		ss.setTp_id_w(0);
    		ss.setS_time(getMusicTime(cursor));
    		list.add(ss);	
    		//System.out.println(getMusicID(cursor));
    	}
    	cursor.close();
    	return list;
    }
    //获取歌曲的ID
    public int getMusicID(Cursor cursor)
    {
//    	return cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID));
    	return cursor.getInt(cursor.getColumnIndexOrThrow(MediaColumns._ID));
    	
    }
    //获取歌曲的名字
    public String getMusicName(Cursor cursor)
    {
    	return cursor.getString(cursor.getColumnIndexOrThrow(MediaColumns.TITLE));
    }
    //获取歌曲的专辑
    public String getAlbumName(Cursor cursor)
    {
    	return cursor.getString(cursor.getColumnIndexOrThrow(AudioColumns.ALBUM));
    }
    //获取歌曲的歌手
    public String  getArtist(Cursor cursor)
    {
    	return cursor.getString(cursor.getColumnIndexOrThrow(AudioColumns.ARTIST));
    }
    //获取歌曲的文件路径
    public String getFolderPath(Cursor cursor)
    {
    	return cursor.getString(cursor.getColumnIndexOrThrow(MediaColumns.DATA));
    }
    //获取歌曲的时长
    public int getMusicTime(Cursor cursor)
    {
    	return cursor.getInt(cursor.getColumnIndexOrThrow(AudioColumns.DURATION));
    }
    //获取歌曲的文件大小
    public long getMusicSize(Cursor cursor)
    {
    	return cursor.getLong(cursor.getColumnIndexOrThrow(MediaColumns.SIZE));
    }
    //获取歌曲的文件列表
    
    
}
