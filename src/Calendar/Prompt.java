package Calendar;

import java.util.Scanner;

public class Prompt {

	private final static String PROMPT = "cal> ";

	public void runPrompt() {
		Scanner in = new Scanner(System.in);
		CalendarMain cal = new CalendarMain();

		int year=0;
		int month=0;

		while (true) {
			System.out.println("년을 입력하세요");
			System.out.println("YEAR>");
			year = in.nextInt();
			if(year==-1) break;
			System.out.println("달을 입력하세요");
			System.out.println("MONTH>");
			month = in.nextInt();
			if (month<1||month>12) {
				System.out.println("달을 입력하세요");
				continue;
			}
			cal.printClendar(year, month);
		}
		System.out.println("Bye~");
		in.close();
	}

	public static void main(String[] args) {
		Prompt p = new Prompt();
		p.runPrompt();
	}
}
