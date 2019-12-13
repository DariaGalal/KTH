package com.networkprogramming.hw4.model;

import java.text.DecimalFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Convert {
	 
	@NotNull
	private String from = "";
	
	@NotNull
	private String to = "";
	
	@NotNull
	@Min(value = 0)
	private double amount;
	
	private String result;
	
	public String getFrom() {
		return this.from;
	}
	
	public String getTo() {
		return this.to;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public String getResult() {
		return this.result;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void doConvert(Double rate){
		
		this.result = round(amount)+" "+from+" = "+ round(rate*amount)+" "+to;
		
	}
	
	private String round(double amount) {
		DecimalFormat df = new DecimalFormat("###.###");
		return df.format(amount);
	}
	
	
}
