package com.example.cursoandroid;

public class Message {
	private long id;
	private String number_source;
	private String text;
	private String date;
	
	public Message(String pNumber_source, String ptext){
		this.number_source=pNumber_source;
		this.text=ptext;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumber_source() {
		return number_source;
	}
	public void setNumber_source(String number_source) {
		this.number_source = number_source;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString(){
		return this.text;
	}
	
	

}
