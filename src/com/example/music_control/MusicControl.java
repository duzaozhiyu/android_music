package com.example.music_control;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.widget.ProgressBar;
import com.example.activity.IConstant;
import com.example.sql.SingStructure;

public class MusicControl implements OnCompletionListener, IConstant {

	private MediaPlayer play;
	private int p_Model;
	private int p_Statu;
	private int p_Indext;
	private List<SingStructure> list;

	private Random random = new Random();

	public MusicControl() {

		p_Model = 1;
		p_Statu = 0;
		p_Indext = 0;

		play = new MediaPlayer();
		play.setOnCompletionListener(this);
	}

	public int getP_Model() {
		return p_Model;
	}

	public void setP_Model(int p_Model) {
		this.p_Model = p_Model;
	}

	public int getP_Statu() {
		return p_Statu;
	}

	public void setP_Statu(int p_Statu) {
		this.p_Statu = p_Statu;
	}

	public void setIndext(int p_Indext) {
		this.p_Indext = p_Indext;
	}

	public int getIndext() {
		return p_Indext;
	}

	public boolean prepare(String path) {
		boolean flag = false;
		try {
			play.reset();
			play.setDataSource(path);
			play.prepare();
			p_Statu = P_PREPARE;
			flag = true;

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean start(String path) {
		boolean flag = false;
		switch (p_Statu) {
		case P_NA:
			flag = false;
			break;
		case P_PAUSED:
			if (seekTo()) {
				p_Statu = P_PLAYING;

			}
			flag = true;
			break;
		default:
			prepare(path);
			play.start();
			p_Statu = P_PLAYING;
			break;
		}

		return flag;
	}

	public int getFullTime() {
		return play.getDuration();
		
	}

	public boolean stop() {
		boolean flag = false;
		if (p_Statu == P_PLAYING || p_Statu == P_PAUSED) {
			play.stop();
			p_Statu = P_STOP;
			flag = true;

		}
		return flag;
	}

	public boolean paused() {
		boolean flag = false;
		if (p_Statu == P_PLAYING) {
			play.pause();
			p_Statu = P_PAUSED;
		}
		return flag;
	}

	public boolean playById(int posion) {

		SingStructure ss = list.get(posion);
		p_Statu = P_STOP;
		start(ss.getS_path());
		setIndext(posion);
		return true;
	}

	public boolean next(int posion) {
		boolean flag = false;
		if (posion < list.size() && posion >= 0) {
			flag = playById(++posion);

			setIndext(posion++);
		} else {
			posion = 0;
			flag = playById(posion);
			setIndext(posion);

		}
		return flag;
	}

	// 更换播放列表
	public void setList(List<SingStructure> list) {
		this.list = list;
		setIndext(0);

	}
	
	public String getSname()
	{
		SingStructure ss = list.get(p_Indext);
		return ss.getS_name() + "%" + ss.getSinger_name() + "%lrc";
	}
	public String getOnlySname()
	{
		SingStructure ss = list.get(p_Indext);
		return ss.getS_name();
	}

	@Override
	public void onCompletion(MediaPlayer play) {
		// TODO Auto-generated method stub
		switch (p_Model) {
		case P_LOOP_PLAY:
			break;
		case P_ORDER_PLAY:
			next(getIndext());
			break;
		case P_RANDOM_PLAY:
			break;
		case P_SINGLE_LOOP_PLAY:
			break;
		}
	}

	public boolean seekTo() {
		if (p_Statu == P_NA) {
			return false;
		} else {
			int time = play.getCurrentPosition();
			play.seekTo(time);
			play.start();
			p_Statu = P_PLAYING;
			return true;
		}

	}
	
	public boolean seekTo(int time) {
		if (p_Statu == P_NA) {
			return false;
		} else {
			
			play.seekTo(time);
			play.start();
			p_Statu = P_PLAYING;
			return true;
		}

	}

	public void exit() {
		if (p_Statu == P_STOP || p_Statu == P_PAUSED||p_Statu==P_PLAYING) {
			play.stop();
			p_Statu =P_STOP;
            
		}
		
		play.release();
	}

	public int getDu() {
		return play.getCurrentPosition();
	}

}
