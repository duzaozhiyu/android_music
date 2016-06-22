package com.example.sql;

import java.util.ArrayList;
import java.util.List;




import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.AlbumColumns;
import android.provider.MediaStore.Audio.Albums;
import android.provider.MediaStore.Audio.AudioColumns;
import android.provider.MediaStore.Audio.Media;

public class MusicScanAblums {
  
	 private Context context=null;
	 private String selection=null;
	 StringBuilder where = new StringBuilder(Albums._ID
				+ " in (select distinct " + Media.ALBUM_ID
				+ " from audio_meta where (1=1 ))");
	 Uri uri = Albums.EXTERNAL_CONTENT_URI;
	 private static String[] proj_album = new String[] { Albums.ALBUM,
			Albums.NUMBER_OF_SONGS, Albums._ID, Albums.ALBUM_ART };
	 
	 
	 public MusicScanAblums(Context context)
	 {
		 this.context=context;
		 setSelection();
	 }
	 public MusicScanAblums(Context context,String selection)
	 {
		 this.context=context;
		 this.selection=selection;
	 }
	 public void setSelection()
	 {
		 this.selection=where.toString();
	 }
	
	public List<ArtistStructure> getArtistList()
	{
		List<ArtistStructure> list=new ArrayList<ArtistStructure>();
		ContentResolver cr = context.getContentResolver();
		Cursor cursor=cr.query(uri, proj_album,
				selection, null, AudioColumns.ALBUM_KEY);
		while(cursor.moveToNext())
		{
			ArtistStructure as=new ArtistStructure();
			as.setArtist_name(cursor.getString(cursor
					.getColumnIndex(AlbumColumns.ALBUM)));
			as.setNum(cursor.getInt(cursor
					.getColumnIndex(AlbumColumns.NUMBER_OF_SONGS)));
			list.add(as);
		}
		cursor.close();
		return list;
		
	}
	

	
}
