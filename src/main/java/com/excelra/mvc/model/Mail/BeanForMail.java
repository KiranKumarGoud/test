package com.excelra.mvc.model.Mail;

import java.util.ResourceBundle;

public class BeanForMail {

	private String toAddress;
	private String ccAddress;
	private String subject;
	private String body;
	
	public BeanForMail()
	{
		
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getCcAddress() {
		return ccAddress;
	}
	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}
	
}
