package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Account;
import model.Bank;

public class CreateAcc {
	private Bank bank;
    private Scanner scanner = new Scanner(System.in);
    
    public CreateAcc(Bank bank) { // Bank 클래스에서 객체 불러오기
        this.bank = bank;
    }
	private int baseAcNum=33330800; // 계좌 번호 초기값
	private int cName=2; // 동명이인 있으면 이름뒤에 2
	public void createAccount() {
		try {
			LocalDateTime createTime= LocalDateTime.now();
			System.out.println("---계좌생성---");
			int acNumPlus= baseAcNum++; // 생성 할때마다 1증가
			System.out.println("생성된 계좌 번호는: "+acNumPlus);
			
			System.out.print("계좌주 입력: ");
			String name = scanner.nextLine();
			name.replace(" ", "");
			
			System.out.print("전화번호 입력: ");
			String tel = scanner.nextLine();
					
			System.out.print("비밀번호 입력: ");
			int pin = scanner.nextInt();
			
			System.out.print("초기 입금액을 입력하세요.: ");
			int balance = scanner.nextInt();
			ArrayList<Account> accounts = bank.getAccountList();
			if(accounts.stream().anyMatch(account -> account.getName().equals(name))) {
				int dupName= cName++;
				String nameSame = name+dupName;
				bank.addAccount(acNumPlus, nameSame, tel, pin, balance, createTime); //동명이인 있으면 이걸로
				System.out.println("계좌가 생성되었습니다. 계좌주 : "+nameSame);
				System.out.println(createTime);
			} else {
				bank.addAccount(acNumPlus,name, tel, pin, balance, createTime); // 입력된 값으로 객체 생성
				System.out.println("계좌가 생성되었습니다.");
				System.out.println(createTime);
				}
			}catch(InputMismatchException e) {
				System.out.println("다시 만들어주세요.");
				scanner.nextLine();
				baseAcNum--;
		}
	}
}
