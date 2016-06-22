package com.example.sql;

public class ArtistStructure {
    private String artist_name=null;
    private int num=0;
    
    public ArtistStructure()
    {}
    public ArtistStructure(String artist_name,int num)
    {
    	this.artist_name=artist_name;
    	this.num=num;
    }
    
    
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
    
} 
