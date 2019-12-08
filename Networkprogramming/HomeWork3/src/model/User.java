package model;

import java.security.MessageDigest;

public class User{
	  private String uName;
	  private String pWord;
	  private MessageDigest messageDigest;

	  public User(String name, String password){
	    try {
	    	messageDigest = MessageDigest.getInstance("SHA-256");
	    }catch(Exception e) {}
	    this.uName = name;
	    this.pWord = encrypt(password);
	  }
	  
	  private String encrypt(String pwd) {
		  messageDigest.update(pwd.getBytes());
		  return new String(messageDigest.digest());
	  }

	  public String getUsername(){
	    return this.uName;
	  }
	  
	  public String getPassword() {
		  return this.pWord;
	  }

	}
