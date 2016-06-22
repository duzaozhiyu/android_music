package com.example.activity;

import android.provider.MediaStore;

public interface  IConstant {
    public final String SORT_ORDER=MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
    public static final int FILTER_SIZE = 1 * 1024 * 1024;// 1MB
	public static final int FILTER_DURATION = 1 * 60 * 1000;// 1����
	
	//���ݿ�ı���
	public final String TABLE_SING = "Sing";
	public final String TABLE_SING_WORD = "Sing_word";
	public final String TABLE_LIST = "Play_list";
	
	//����ģʽ
	public final int P_LOOP_PLAY=0;
	public final int P_ORDER_PLAY=1;//˳�򲥷�
	public final int P_RANDOM_PLAY=2;
	public final int P_SINGLE_LOOP_PLAY=3;
	
	//����״̬
	public final int P_PLAYING=1;
	public final int P_PAUSED=2;
	public final int P_STOP=3;
	public final int P_PREPARE=0;
	public final int P_NA=-1;//��Ч״̬
	
	//��ǰģʽ��״̬
	public final String PLAY_MODLE="Play_Molde";
	public final String PLAY_STATU="Play_Statu";
	
	//��ǰ����߳��Ƿ���
	public final String THREADSTATU="Threadstatu";
	
}
