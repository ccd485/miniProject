package sungjuk_frame;

import java.util.*;

import javax.swing.JOptionPane;

public class SungProImpl implements SungPro{
	ArrayList<Student> list;
	Scanner in;

	public SungProImpl() {
		list = new ArrayList<>();
		in = new Scanner(System.in);
	}
	
	protected Student isName(String name) {
		for(Student st : list) {
			if (name.equals(st.getName())) {
				return st;
			}
		}
		return null;
	}
	
	@Override
	public void input(String name, int kor, int eng) {
		if (isName(name) == null) {
			Student input = new Student(name, kor, eng);
			list.add(input);
			JOptionPane.showMessageDialog(null, name+"�л��� �Է��Ͽ����ϴ�.", 
					"�˸�", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, name+"�л��� �̹� �����մϴ�.", 
					"�˸�", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void rank() {
		for(Student rank1 : list) {
			rank1.clearRank();
			for(Student rank2 : list) {
				if (rank1.getTot() < rank2.getTot()) {
					rank1.plusRank();
				}
			}
		}
	}
	
	@Override
	public ArrayList<Student> view(String title) {
		rank();
		switch(title) {
		case "�̸�" :
			Collections.sort(list);
			break;
		case "����" :
			Collections.sort(list, new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					if (o1.getKor() < o2.getKor()) {
						return 1;
					}else {
						return -1;
					}
				}
			});
			break;
		case "����" :
			Collections.sort(list, new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					if (o1.getEng() < o2.getEng()) {
						return 1;
					}else {
						return -1;
					}
				}
			});
			break;
		case "����" :
			Collections.sort(list, new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					if (o1.getTot() < o2.getTot()) {
						return 1;
					}else {
						return -1;
					}
				}
			});
		}
		return list;
	}

	@Override
	public void delete(String name) {
		Student delete = isName(name);
		if (delete == null) {
			JOptionPane.showMessageDialog(null, name+"�л��� ���� �����ϴ�.", 
									"�˸�", JOptionPane.INFORMATION_MESSAGE);
		}else {
			list.remove(delete);
			JOptionPane.showMessageDialog(null, name+"�л��� �����Ͽ����ϴ�.", 
					"�˸�", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
/*
	@Override
	public void update() {
		System.out.print("������ �л��� �̸� : ");
		String name = in.next();
		Student update = isName(name);
		if (update == null) {
			System.out.println(name+"�л��� �����ϴ�. �ٽ� �Է��� �ּ���!!");
			update();
		}else {
			System.out.println(name+"�л��� ���� �������� : " + update.getKor());
			System.out.print("������ �������� : ");
			int kor = in.nextInt();
			System.out.println(name+"�л��� ���� �������� : " + update.getEng());
			System.out.print("������ �������� : ");
			int eng = in.nextInt();
			update.setKor(kor);
			update.setEng(eng);
		}
	}

	@Override
	public void exit() {
		System.out.println("���α׷��� �����մϴ�.");
		System.exit(0);
		
	}
*/
}
