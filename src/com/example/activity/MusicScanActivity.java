package com.example.activity;


import java.util.List;

import com.example.my_android_music.MainActivity;
import com.example.my_android_music.R;
import com.example.sql.MusicScan;
import com.example.sql.SingStructure;
import com.example.sql.SqlTool;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MusicScanActivity extends Activity implements IConstant {
	private Button btn_scan;
	private ProgressDialog mProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.musicscan_activity);
		btn_scan=(Button) findViewById(R.id.activity_m_scan);
		btn_scan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				mProgress=ProgressDialog.show(MusicScanActivity.this, "…®√Ë“Ù¿÷", "’˝‘⁄…®√Ë÷–£¨«Î…‘µ»");
				new Thread(){
					public void run() {
						  MusicScan ms=new MusicScan(MusicScanActivity.this);
				          List<SingStructure> list=ms.getSing();
				          SqlTool sql_t=new SqlTool(list,MusicScanActivity.this);
				          sql_t.deleteTable(TABLE_SING);
				          sql_t.deleteTable(TABLE_SING_WORD);
				          sql_t.inSert();
				          sql_t.insert_WORD();
				          mProgress.dismiss();
					};
				}.start();
				  
			}
		});
	}
}
