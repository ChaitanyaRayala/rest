package org.chaitanya.test.rest.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private String errorMessage;
	private int errorCode;
	private String errorDocs;	
	
	public ErrorMessage() {
		
	}
	public ErrorMessage(String errorMessage, int errorCode, String errorDocs) {
		
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.errorDocs = errorDocs;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDocs() {
		return errorDocs;
	}
	public void setErrorDocs(String errorDocs) {
		this.errorDocs = errorDocs;
	}
}
