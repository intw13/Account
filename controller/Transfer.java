package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.Account;
import model.Bank;

public class Transfer {
	private Bank bank;
	private Scanner scanner = new Scanner(System.in);
		
	public Transfer(Bank bank) { // Bank 클래스에서 객체 불러오기
		this.bank=bank;
	}
	
	public void transfer() {
		while(true) { //반복문
			try { //try catch 예외
				System.out.print("계좌번호를 입력하세요.: ");
				int acNum = scanner.nextInt();
				if(acNum==0) { // 0 입력시 종료
					break;
				}
		
				Account account1 = bank.findAccount(acNum); // account1 객체 생성
				if(account1 == null) { // 계좌가 틀릴경우
					System.out.println("해당 계좌가 존재하지 않습니다.");
					continue; // 틀릴경우 while부터 다시 반복
				}
				boolean checkPin = false; // 초기값
				while(!checkPin) { // false 일때 반복
					System.out.print("비밀번호를 입력하세요.: ");
					int pin=scanner.nextInt();
					if(pin == 0) { // 0 입력시 탈출
						break;
					}
					if(!account1.checkPin(pin)){ // 틀릴경우 반복
						System.out.println("비밀번호가 틀렸습니다.");
						continue; // 틀린경우 while부터 다시 반복
					}
					checkPin = true; // 일치하면 탈출
				}
				if(checkPin) { //true일때 실행
					System.out.print("이체할 계좌번호를 입력해주세요.: ");
					int toTrans = scanner.nextInt();
					Account account2 = bank.findAccount(toTrans); // account2 객체 생성
					if(account2 == null) { // 계좌가 틀릴경우
						System.out.println("해당 계좌가 존재하지 않습니다.");
						continue; // 툴린경우 while부터 다시 반복
					}
					else {
						System.out.print("송금할 금액을 입력해주세요.: ");
						int amount= scanner.nextInt();
						
						if(amount>=10000000) {
							System.out.println("송금 한도인 1천만원을 넘었습니다.");
						}
						else if(amount==0) {
							System.out.println("입력이 잘못됐습니다.");
						}
						else if(account1.getBalance() < amount) {
							System.out.println("계좌 잔액이 부족합니다.");
							continue;
						}
						else {
							account2.deposit(amount); // 받는 계좌
							account1.withdraw(amount); // 주는 계좌
							
							System.out.println("송금이 완료되었습니다. 현재 잔액은 "+account1.getBalance()
							+"원 입니다.");
							break; //종료
						}
					}
					
				}
			}catch(InputMismatchException e) { // 예외
				System.out.println("숫자를 입력해주세요.");
				scanner.nextLine();
			}
		}
	}
}
