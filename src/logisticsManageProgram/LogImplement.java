package logisticsManageProgram;

import java.util.ArrayList;
import java.util.Scanner;



public class LogImplement implements LogInterface{
	LogServer_DAO dao;
	Scanner in;
	
	
	public LogImplement() {
		dao = new LogServer_DAO();
		in = new Scanner(System.in);
	}
		
	@Override
	public void insert(String item, int price, int quantity, String indate) {
		
		
		int res = dao.insertLog(item, price, quantity, indate);
		if (res>0) {
			System.out.println(item+"도서를 추가하였습니다.");
		}else {
			System.out.println(item+"도서 추가 실패!!");
		}
		
	}

	@Override
	public void search(String search, String searchString) {
		ArrayList<Log_DTO> list = dao.searchLog(search, searchString);
		for(Log_DTO dto : list) {
			System.out.print(dto.getItem()+"\t");
			System.out.print(dto.getPrice() + "\t");
			System.out.print(dto.getQuantity() + "\t");
			System.out.print(dto.getIndate()+"\t");
		}
		
	}

	@Override
	public void update(String item, int quantity) {
		
		int res = dao.updateLog(item, quantity);
		if (res>0) {
			System.out.println(item+"도서를 수정하였습니다.");
		}else {
			System.out.println(item+"도서명을 가진 도서는 없습니다.");
		}
		
	}

	@Override
	public void delete(String item) {
		
		int res = dao.deleteLog(item);
		if (res>0) {
			System.out.println(item+"도서를 삭제하였습니다.");
		}else {
			System.out.println(item+"도서명을 가진 도서는 없습니다.");
		}
		
	}

	@Override
	public void exit() {
		System.out.println("프로그램을 종료합니다.!!");
		System.exit(0);
		
	}

}
