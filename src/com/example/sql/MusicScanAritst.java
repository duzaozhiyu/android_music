package com.example.sql;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.ArtistColumns;

public class MusicScanAritst {

	 private Context context=null;
	 private String selection=null;
	 Uri uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
	 
	 private static String[] proj_artist = new String[] {
			MediaStore.Audio.Artists.ARTIST,
			MediaStore.Audio.Artists.NUMBER_OF_TRACKS};
	 
	 public MusicScanAritst(Context context)
	 {
		 this.context=context;
	 }
	 public MusicScanAritst(Context context,String selection)
	 {
		 this.context=context;
		 this.selection=selection;
	 }
	
	public List<ArtistStructure> getArtistList()
	{
		List<ArtistStructure> list=new ArrayList<ArtistStructure>();
		ContentResolver cr = context.getContentResolver();
		Cursor cursor=cr.query(uri,null,null,null,ArtistColumns.NUMBER_OF_TRACKS);
		while(cursor.moveToNext())
		{
			ArtistStructure as=new ArtistStructure();
			as.setArtist_name(cursor.getString(cursor.getColumnIndex(ArtistColumns.ARTIST)));
			as.setNum(cursor.getInt(cursor.getColumnIndex(ArtistColumns.NUMBER_OF_TRACKS)));
			list.add(as);
		}
		cursor.close();
		return list;
		
	}
	
	
}
