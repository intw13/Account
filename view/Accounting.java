package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.*;
import model.Bank;

public class Accounting {
	private Bank bank = new Bank();
	private Scanner scanner=new Scanner(System.in);
	private CreateAcc createAcc = new CreateAcc(bank);
	private Depositor depositor = new Depositor(bank);
	private Withdrawer withdrawer = new Withdrawer(bank);
	private ListAccount listAccount = new ListAccount(bank);
	private ModifyAccount modifyAccount = new ModifyAccount(bank);
	private Transfer transfer = new Transfer(bank);
	private Interest interest = new Interest(bank);
	
	// 생성자 생성, 스캐너
	
	public void run() { // 실행
		
		while(true) { // 반복
			try { // try catch 예외
				System.out.println("--------------------------------------------------------------");
				System.out.println("| 0.종 료 | 1.계좌생성 | 2.회원정보 수정 | 3.예 금 ");
				System.out.println("| 4.출 금 | 5.이 체 | 6.이 자 | 7.회원정보 확인 ");
				System.out.println("--------------------------------------------------------------");
				System.out.println("      	모든 메뉴에서 0 입력시 메뉴로 돌아오기");
				System.out.println("--------------------------------------------------------------");
			
				System.out.print("선택: ");
				int menuNum= scanner.nextInt();
			
				if(menuNum==0) {
					System.out.println("프로그램 종료");
					return;
				
				} else if(menuNum==1) {
					createAcc.createAccount();
				
				} else if(menuNum==2) {
					modifyAccount.modifyAccount();
				
				} else if(menuNum==3) {
					depositor.deposit();
				
				} else if(menuNum==4) {
					withdrawer.withdraw();
				
				} else if(menuNum==5) {
					transfer.transfer();
					
				} else if(menuNum==6) {
					interest.calculateInterest();
				
				} else if(menuNum==7) {
					listAccount.listAccount();
				
				}
			}catch(InputMismatchException e) { // 예외
				System.out.println("숫자를 입력해주세요.");
				scanner.nextLine();
				
			}
		}
	}
}
