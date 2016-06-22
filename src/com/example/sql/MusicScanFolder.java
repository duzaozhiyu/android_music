package com.example.sql;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Files.FileColumns;
import android.provider.MediaStore.MediaColumns;

public class MusicScanFolder {
	private Context context = null;
	private String selection = null;
	private Uri uri = MediaStore.Files.getContentUri("external");
	private static String[] proj_folder = new String[] { FileColumns.DATA };
	StringBuilder mSelection = new StringBuilder(FileColumns.MEDIA_TYPE + " = "
			+ FileColumns.MEDIA_TYPE_AUDIO + " and " + "(" + FileColumns.DATA
			+ " like'%.mp3' or " + Media.DATA + " like'%.wma')");

	public MusicScanFolder(Context context) {
		this.context = context;
		mSelection.append(" )group by ( " + FileColumns.PARENT);
		setSelection();
	}

	public MusicScanFolder(Context context, String selection) {
		this.context = context;
		this.selection = selection;
	}

	public void setSelection() {
		selection = mSelection.toString();
	}

	public List<FolderStructure> getFolderList() {
		List<FolderStructure> list = new ArrayList<FolderStructure>();

		ContentResolver cr = context.getContentResolver();
		Cursor cursor = cr.query(uri, proj_folder, selection, null, null);
		while (cursor.moveToNext()) {
			FolderStructure ss = new FolderStructure();
			String s_path = cursor.getString(cursor
					.getColumnIndex(MediaColumns.DATA));
			ss.setFolder_Path(s_path.substring(0,
					s_path.lastIndexOf(File.separator)));
			String path = ss.getFolder_Path();
			ss.setFolder_Name(path.substring(path.lastIndexOf(File.separator) + 1));
			list.add(ss);
		}
		cursor.close();
		return list;
	}

}
