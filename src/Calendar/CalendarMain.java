package Calendar;

import java.util.*;

public class CalendarMain {

	private static final int[] maxDays = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] LeapMaxDays = new int[] { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public boolean isLeapYear(int year) {
		if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
			return true;
		} else
			return false;
	}

	public int getLastDaysOfMonth(int year, int month) {
		if (isLeapYear(year)) {
			return LeapMaxDays[month - 1];
		}else {
			return maxDays[month - 1];
		}
	}

	public void printClendar(int year,int month) {
		
		System.out.printf("\t\t%4d년%3d월\n\n",year,month);
		System.out.println("SUN\tMON\tTUE\tWED\tTHU\tFRI\tSAT");
		System.out.println("----------------------------------------------------");
		
		int weekday=getWeekDay(year,month,1);
		int maxDay = getLastDaysOfMonth(year,month);
		for(int i=1; i<=maxDay; i++) {
			System.out.print(" "+i+"\t");
			if(i%7==0) {
				System.out.println();
			}
		}
		System.out.println();
	}

	private int getWeekDay(int year, int month, int i) {
		
		return 0;
	}

}
