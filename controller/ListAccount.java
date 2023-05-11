package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Account;
import model.Bank;

public class ListAccount {
	private Bank bank;
	private Scanner scanner = new Scanner(System.in);
	
	public ListAccount(Bank bank) { // Bank 클래스에서 객체 불러오기
		this.bank = bank;
	}
	
	public void listAccount() {
		ArrayList<Account> accounts = bank.getAccountList(); 
		if(accounts.isEmpty()) {	
			System.out.println("생성된 계좌가 없습니다."); 
			return;
		}
		System.out.print("검색할 계좌주 이름을 입력하세요.: ");
		String name = scanner.nextLine();
		
		// 스트림 변환 > 하나라도 맞는거 확인 > account에 getName이 입력한 이름과 같을때
		if(accounts.stream().anyMatch(account -> account.getName().equals(name))) {
			for(Account account : accounts) { // Account 클래스 account = accounts 객체와 같다
				if(account.getName().equals(name)) { // 검색한 계좌주 이름과 같을때 프린트
					System.out.println("계좌주: "+account.getName());	
					System.out.println("계좌번호: "+account.getAcNum());
					System.out.println("잔액: "+account.getBalance());
					System.out.println("계좌비밀번호: "+account.getPin());
					System.out.println("계좌주 전화번호: "+account.getTel());
					}
				}
			} else { // 잘못 입력시 출력
				System.out.println("해당 계좌주는 존재하지 않습니다.");
		}
	}
}
