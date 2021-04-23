package Calendar;

import java.util.*;

public class CalendarMain {

	private static final int[] maxDays = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public int getLastDaysOfMonth(int month) {

		return maxDays[month - 1];
	}

	public void printSampleClendar() {
		System.out.println("일\t월\t화\t수\t목\t금\t토");
		System.out.println("--------------------------------------------------");
		System.out.println("1\t2\t3\t4\t5\t6\t7");
		System.out.println("8\t9\t10\t11\t12\t13\t14");
		System.out.println("15\t16\t17\t18\t19\t20\t21");
		System.out.println("22\t23\t24\t25\t26\t27\t28");
		System.out.println("29\t30\t31");
	}

	public static void main(String[] args) {
		String prompt = "cal>";
		Scanner in = new Scanner(System.in);
		CalendarMain cal = new CalendarMain();
		
		int month=1;
		
		while(true) {
			System.out.println("달을입력하세요");
			System.out.println(prompt);
			month = in.nextInt();
			if(month==-1) {
				break;
			}else if(month>12) {
				continue;
			}
			System.out.printf("%d월은 %d일까지 있습니다. \n", month, cal.getLastDaysOfMonth(month));
		}
			System.out.println("Bye~");
			in.close();
	}
}
