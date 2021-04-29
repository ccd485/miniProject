package sungjuk_frame;

public class Student implements Comparable<Student>{
	private String name;
	private int kor;
	private int eng;
	private int tot;
	private int rank;
	
	public Student(String name, int kor, int eng){
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.tot = kor + eng;
	}
	
	public String getName() {
		return name;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getKor() {
		return kor;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getEng() {
		return eng;
	}
	public void setTot() {
		tot = kor + eng;
	}
	public int getTot() {
		return tot;
	}
	public int getRank() {
		return rank;
	}
	public void clearRank() {
		rank = 1;
	}
	public void plusRank() {
		rank++;
	}
		
	public String disp() {
		return name+"\t"+kor+"\t"+eng+"\t"+tot+"\t"+rank;
	}

	@Override
	public int compareTo(Student s) {
		return name.compareTo(s.getName());
	}
}









