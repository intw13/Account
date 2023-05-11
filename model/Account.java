package model;

import java.time.LocalDateTime;

public class Account {
		
	private int acNum; //계좌번호
	private int balance; //잔액
	private String name; //계좌주
	int pin; //비밀번호
	String tel; //전화번호
	private LocalDateTime createTime; // 생성시간
	
	public Account(int acNum,String name,String tel,int pin,int balance, LocalDateTime createTime) {
		this.acNum=acNum;
		this.name=name;
		this.tel=tel;
		this.pin=pin;
		this.balance=balance;
		this.createTime=createTime;
	}
	
	
	public int getAcNum() {
		return acNum;
	}
	public void setAcNum(int acNum) {
		this.acNum = acNum;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	 public boolean checkPin(int pin) {
	        return this.pin==pin;
	}
	public void deposit(int amount) {
		if(amount < 0)	{
			amount = -amount;
		}
	        balance += amount;
	}
	public boolean withdraw(int amount) {
		if (balance < amount) {
			return false;
		}
		balance -= amount;
		return true;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
}
