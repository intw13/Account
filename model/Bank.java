package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Bank { // Bank 클래스는 객체 저장소
	private ArrayList<Account> accounts = new ArrayList<>(); // ArrayList 객체 생성
	
	//addAccount 생성자(매개 변수)
	public void addAccount(int acNum,String name,String tel,int pin,int balance, LocalDateTime createTime) {
		accounts.add(new Account(acNum, name, tel, pin, balance, createTime)); // 객체 추가
	}
	public Account findAccount(int acNum) { //acNum이 리스트(accounts) 와 일치하면 account 불러오기
		for(Account account : accounts) { // account = accounts
			if(account.getAcNum()==acNum){ // 저장된 객체 계좌번호 == 입력된 계좌번호
				return account; // 객체 불러오기
			}
		}
		return null; // 반환
	}
	
	public ArrayList<Account> getAccountList() { //Account 객체 불러오기
		return accounts;
	}
}
