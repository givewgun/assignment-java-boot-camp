package com.kbtg.techkamp.week1.shop.models.requests;

public class OrderRequest{
	private String cvv;
	private String address;
	private String phone;
	private String fullName;
	private String expiry;
	private int userid;
	private String cardNumber;

	public OrderRequest() {
	}

	public OrderRequest(String cvv, String address, String phone, String fullName, String expiry, int userid, String cardNumber) {
		this.cvv = cvv;
		this.address = address;
		this.phone = phone;
		this.fullName = fullName;
		this.expiry = expiry;
		this.userid = userid;
		this.cardNumber = cardNumber;
	}

	public void setCvv(String cvv){
		this.cvv = cvv;
	}

	public String getCvv(){
		return cvv;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setExpiry(String expiry){
		this.expiry = expiry;
	}

	public String getExpiry(){
		return expiry;
	}

	public void setUserid(int userid){
		this.userid = userid;
	}

	public int getUserid(){
		return userid;
	}

	public void setCardNumber(String cardNumber){
		this.cardNumber = cardNumber;
	}

	public String getCardNumber(){
		return cardNumber;
	}
}
