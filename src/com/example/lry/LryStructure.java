package com.example.lry;

public class LryStructure {
	private int s_time;
	private String s_lry;

	public LryStructure(int s_time, String s_lry) {
		this.s_time = s_time;
		this.s_lry = s_lry;
	}

	public LryStructure()
	{}
	public int getS_time() {
		return s_time;
	}

	public void setS_time(int s_time) {
		this.s_time = s_time;
	}

	public String getS_lry() {
		return s_lry;
	}

	public void setS_lry(String s_lry) {
		this.s_lry = s_lry;
	}

}
