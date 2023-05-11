package controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import model.Account;
import model.Bank;

public class Withdrawer {
	private Bank bank;
	private Scanner scanner = new Scanner(System.in);
	
	public Withdrawer(Bank bank) { // Bank 클래스에서 객체 불러오기
		this.bank=bank;
	}
	
	public void withdraw() {
	    while (true) {
	        try {
	            System.out.print("계좌번호를 입력하세요.: ");
	            int acNum = scanner.nextInt();
	            if (acNum == 0) {
	                break;
	            }

	            Account account = bank.findAccount(acNum);
	            if (account == null) {
	                System.out.println("해당 계좌가 존재하지 않습니다.");
	                continue;
	            }
	            boolean checkPin = false;
	            while (!checkPin) {
	                System.out.print("비밀번호를 입력하세요.: ");
	                int pin = scanner.nextInt();
	                if (pin == 0) {
	                    break;
	                }
	                if (!account.checkPin(pin)) {
	                    System.out.println("비밀번호가 틀렸습니다.");
	                    continue;
	                }
	                checkPin = true;
	            }
	            if (checkPin) {
	                System.out.print("출금액을 입력하세요.: ");
	                int amount = scanner.nextInt();

	                if (amount > account.getBalance()) {
	                    System.out.println("출금할 금액이 부족합니다.");
	                } else if (amount >= 10000000) {
	                    System.out.println("출금 한도인 1천만원을 넘었습니다.");
	                } else if (amount == 0) {
	                    System.out.println("입력이 잘못됐습니다.");
	                } else {
	                    try {
	                    	System.out.println("3초뒤에 출금됩니다.");
	                        TimeUnit.SECONDS.sleep(3);
	                        account.withdraw(amount);
	                        System.out.println("출금이 완료되었습니다. 현재 잔액은 " + account.getBalance() + "원 입니다");
	                        break;
	                    } catch (InterruptedException e) {
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
