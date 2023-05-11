package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Bank;
import model.Account;

public class ModifyAccount {
	private Bank bank;
	private Scanner scanner = new Scanner(System.in);
	
	public ModifyAccount(Bank bank) { // Bank 클래스에서 객체 불러오기
		this.bank=bank;
	}
	
	public void modifyAccount() {
	    ArrayList<Account> accounts = bank.getAccountList(); // 저장된 객체 불러오기
	    if (accounts.isEmpty()) { // 객체 길이가 0일경우 = 저장된 객체가 없을시
	        System.out.println("생성된 계좌가 없습니다.");
	        
	    }
	    System.out.println("-----회원 정보 수정-----");
	    System.out.print("검색할 계좌주 이름을 입력하세요.: ");
	    String accountName = scanner.next(); // 검색할 계좌주 이름 받기
	    boolean found = false; // 계좌주 이름이 일치하는 계좌를 찾았는지 여부를 저장하는 변수
	    for (int i = 0; i < accounts.size(); i++) { //  객체 길이 만큼 반복
	        if (accounts.get(i).getName().equals(accountName)) { // 객체중에 accountName과 같은거 찾기
	            found = true; // 계좌주 이름이 일치하는 계좌를 찾음
	            System.out.println("1. 비밀번호 수정");
	            System.out.println("2. 전화번호 수정");
	            int menuNum = scanner.nextInt();
	            if (menuNum == 1) {
	                System.out.print("새로운 비밀번호를 입력하세요.: ");
	                int newPin = scanner.nextInt(); 
	                accounts.get(i).setPin(newPin); // 저장된 비밀번호 바꿈
	                System.out.println("비밀번호가 변경되었습니다.");
	            } else if (menuNum == 2) {
	                System.out.print("새로운 전화번호를 입력하세요.: ");
	                String newTel = scanner.next();
	                accounts.get(i).setTel(newTel); // 저장된 전화번호 바꿈
	                System.out.println("전화번호가 변경되었습니다.");
	            } else {
	                System.out.println("잘못된 메뉴 번호입니다.");
	            }
	            break; // 계좌주 이름이 일치하는 계좌를 찾았으므로 종료
	        }
	    }
	    if (!found) { // 계좌 못찾음
	        System.out.println("해당 계좌주는 존재하지 않습니다.");
	    }
	}
}
