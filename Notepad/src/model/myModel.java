package model;

import java.util.ArrayList;

public class myModel {
	private String content;
	private String fileName;
	
	public myModel() {
		content = "";
		fileName = "";
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
