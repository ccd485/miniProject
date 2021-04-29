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
			JOptionPane.showMessageDialog(null, name+"학생을 입력하였습니다.", 
					"알림", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, name+"학생은 이미 존재합니다.", 
					"알림", JOptionPane.INFORMATION_MESSAGE);
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
		case "이름" :
			Collections.sort(list);
			break;
		case "국어" :
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
		case "영어" :
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
		case "총점" :
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
			JOptionPane.showMessageDialog(null, name+"학생은 저희 없습니다.", 
									"알림", JOptionPane.INFORMATION_MESSAGE);
		}else {
			list.remove(delete);
			JOptionPane.showMessageDialog(null, name+"학생을 삭제하였습니다.", 
					"알림", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
/*
	@Override
	public void update() {
		System.out.print("수정할 학생의 이름 : ");
		String name = in.next();
		Student update = isName(name);
		if (update == null) {
			System.out.println(name+"학생은 없습니다. 다시 입력해 주세요!!");
			update();
		}else {
			System.out.println(name+"학생의 현재 국어점수 : " + update.getKor());
			System.out.print("수정할 국어점수 : ");
			int kor = in.nextInt();
			System.out.println(name+"학생의 현재 영어점수 : " + update.getEng());
			System.out.print("수정할 영어점수 : ");
			int eng = in.nextInt();
			update.setKor(kor);
			update.setEng(eng);
		}
	}

	@Override
	public void exit() {
		System.out.println("프로그램을 종료합니다.");
		System.exit(0);
		
	}
*/
}
