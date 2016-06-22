package com.example.sql;

public class SingStructure {
    private int tp_id_s;
    private String s_name;
    private String album;
    private String s_path;
    private String singer_name;
    private int tp_id_w;
    private int favorite;
    private long s_time;
    
    
    public SingStructure()
    {}
    
    /**
     * @param tp_id_s
     * @param s_name
     * @param album
     * @param s_path
     * @param singer_name
     * @param tp_id_w
     * @param favorite
     * @param s_time
     */
    public SingStructure(int tp_id_s,String s_name,String album,String s_path,String singer_name,int tp_id_w,
    		int favorite,int s_time)
    {
    	this.tp_id_s=tp_id_s;
    	this.s_name=s_name;
    	this.album=album;
    	this.s_path=s_path;
    	this.singer_name=singer_name;
    	this.tp_id_w=tp_id_w;
    	this.favorite=favorite;
    	this.s_time=s_time;
    }

	public int getTp_id_s() {
		return tp_id_s;
	}

	public void setTp_id_s(int tp_id_s) {
		this.tp_id_s = tp_id_s;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getS_path() {
		return s_path;
	}

	public void setS_path(String s_path) {
		this.s_path = s_path;
	}

	public String getSinger_name() {
		return singer_name;
	}

	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}

	public int getTp_id_w() {
		return tp_id_w;
	}

	public void setTp_id_w(int tp_id_w) {
		this.tp_id_w = tp_id_w;
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public long getS_time() {
		return s_time;
	}

	public void setS_time(long s_time) {
		this.s_time = s_time;
	}

}
