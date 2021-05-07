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
			System.out.println(item+"������ �߰��Ͽ����ϴ�.");
		}else {
			System.out.println(item+"���� �߰� ����!!");
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
			System.out.println(item+"������ �����Ͽ����ϴ�.");
		}else {
			System.out.println(item+"�������� ���� ������ �����ϴ�.");
		}
		
	}

	@Override
	public void delete(String item) {
		
		int res = dao.deleteLog(item);
		if (res>0) {
			System.out.println(item+"������ �����Ͽ����ϴ�.");
		}else {
			System.out.println(item+"�������� ���� ������ �����ϴ�.");
		}
		
	}

	@Override
	public void exit() {
		System.out.println("���α׷��� �����մϴ�.!!");
		System.exit(0);
		
	}

}
