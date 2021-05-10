package logisticsManageProgram;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class LogImplement extends JFrame implements LogInterface{
	LogServer_DAO dao;
	Scanner in;
	
	
	public LogImplement() {
		dao = new LogServer_DAO();
		in = new Scanner(System.in);
	}
		
	@Override
	public void insert(String item, int price, int quantity) {
		
		System.out.println(item+":"+price+":"+quantity);
		int res = dao.insertLog(item, price, quantity);
		if (res>0) {
			JOptionPane.showMessageDialog(this, item+" �߰� �Ϸ�");
		}else {
			JOptionPane.showMessageDialog(this, item+" �Է� ����");
		}
		
	}

	@Override
	public ArrayList<Log_DTO> search(String search, String searchString) {
		ArrayList<Log_DTO> list = dao.searchLog(search, searchString);
		return list;
	}

	@Override
	public void update(String item, int quantity) {
		
		int res = dao.updateLog(item, quantity);
		if (res>0) {
			JOptionPane.showMessageDialog(this, item+" ������Ʈ �Ϸ�");
		}else {
			JOptionPane.showMessageDialog(this, item+" ������Ʈ ����");
		}
		
	}

	@Override
	public void delete(String item) {
		
		int res = dao.deleteLog(item);
		if (res>0) {
			JOptionPane.showMessageDialog(this, item+" ���� �Ϸ�");
		}else {
			JOptionPane.showMessageDialog(this, item+" ���� ����");
		}
		
	}

	@Override
	public void exit() {
		JOptionPane.showMessageDialog(this, "���α׷�����");
		System.exit(0);
		
	}

}
