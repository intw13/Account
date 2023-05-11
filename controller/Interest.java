package controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Account;
import model.Bank;

public class Interest {
	private Bank bank;
	private Scanner scanner=new Scanner(System.in);
	
	public Interest(Bank bank) {
		this.bank=bank;
	}
	

	public void calculateInterest() {
        System.out.println("---이자---");
        while(true) {
        	try {
        		System.out.print("계좌번호 입력: ");
        		int acNum = scanner.nextInt();
        		if(acNum==0) {
        			break;
        		}
      
		        Account account = bank.findAccount(acNum);
		        LocalDateTime interestTime= LocalDateTime.now(); // 이자율 계산할때 시간
		        if (account == null) {
		        	System.out.println("해당 계좌를 찾을 수 없습니다.");
		        	return;
		        }
		        boolean checkPin = false; // 초기값
				while(!checkPin) { // false 일때 반복
					System.out.print("비밀번호를 입력하세요.: ");
					int pin=scanner.nextInt();
					if(pin == 0) { // 0 입력시 탈출
						break;
					}
					if(!account.checkPin(pin)){ // 틀릴경우 반복
						System.out.println("비밀번호가 틀렸습니다.");
						continue;
					}
					checkPin = true; // 일치하면 탈출
				}
				if(checkPin) {
	//				System.out.println("현재 이자액: "+);			
					Duration between = Duration.between(account.getCreateTime(), interestTime);
					long min = between.toMinutes(); // 분
//					long sec = between.getSeconds(); // 초
					int interest = (int) (account.getBalance() * 0.02 * min);
					System.out.println("현재 이자액: " + interest); // 현재 이자액 계산 및 출력
					System.out.println("이자액을 받으시려면 1 / 아니면 0");
					System.out.print("입력: ");
					int num=scanner.nextInt();
					switch (num) {
					case 0:
				        break;
				    case 1:
				        if (min > 0) {
				            account.deposit(interest);
				            account.setCreateTime(interestTime);
				            System.out.println("이자액이 입금되었습니다. 현재 잔액은: " + account.getBalance() + "원 입니다.");
				        } else {
				            System.out.println("받을 수 있는 이자액이 없습니다.");
				            System.out.println("현재 이자액: " + interest); // 현재 이자액 계산 및 출력
				        }
				        break;
        				}
					}
        	}catch(InputMismatchException e) {
				System.out.println("숫자를 입력해주세요.");
				scanner.nextLine();	
        	}
        }
	}
}