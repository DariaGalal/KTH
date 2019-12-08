package model;

import java.io.Serializable;

public class Filum implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String owner;
	private int size;
	
	public Filum(String fileName, String owner, int size) {
		this.fileName = fileName;
		this.owner = owner; 
		this.size = size;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public String getOwner() {
		return this.owner;
	}
	
	public int getSize() {
		return this.size;
	}
}
