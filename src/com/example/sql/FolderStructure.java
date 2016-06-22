package com.example.sql;

public class FolderStructure {
	private String folder_Name = null;
	private String folder_Path = null;

	public FolderStructure() {
	}

	public FolderStructure(String folder_Name, String folder_Path) {
		this.folder_Name = folder_Name;
		this.folder_Path = folder_Path;
	}

	public String getFolder_Name() {
		return folder_Name;
	}

	public void setFolder_Name(String folder_Name) {
		this.folder_Name = folder_Name;
	}

	public String getFolder_Path() {
		return folder_Path;
	}

	public void setFolder_Path(String folder_Path) {
		this.folder_Path = folder_Path;
	}

}
