package controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import model.Bank;
import model.Account;

public class Depositor {
    private Bank bank;
    private Scanner scanner = new Scanner(System.in);

    public Depositor(Bank bank) { // Bank 클래스에서 객체 불러오기
        this.bank = bank;
        }
	
	public void deposit() {
		while(true) { // 반복
			try { // try catch 예외
				System.out.print("계좌번호를 입력하세요.: ");
				int acNum = scanner.nextInt();
				if(acNum==0) { // 0 입력시 종료
					break;
				}
		
				Account account = bank.findAccount(acNum); // acNum과 일치한 계좌를 찾는 account 객체 생성
				if(account == null) { // 틀릴 경우
					System.out.println("해당 계좌가 존재하지 않습니다.");
					continue; // while로 이동
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
						continue; // while로 이동
					}
					checkPin = true; // 일치하면 탈출
				}
				if(checkPin) {
					System.out.print("입금액을 입력하세요.: ");
					int amount = scanner.nextInt();
		
					if(amount>=10000000) {
						System.out.println("입금 한도인 1천만원을 넘었습니다.");
					}
					else if(amount==0) {
						System.out.println("입력이 잘못됐습니다.");
					} else {
						try {
							System.out.println("3초뒤에 입금됩니다.");
							TimeUnit.SECONDS.sleep(3);
							account.deposit(amount);
							System.out.println("입금이 완료되었습니다. 현재 잔액은 "+account.getBalance()+"원 입니다.");
							break;
                    }catch(InterruptedException e) {
                    	e.printStackTrace();
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요.");
            scanner.nextLine();
        }
    }
}
}
