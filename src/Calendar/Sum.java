package Calendar;

import java.util.Scanner;

public class Sum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int a, b;
		String s1, s2;
		System.out.println("첫번쨰 수 :");
		s1 = in.next();
		System.out.println("두번쨰 수 :");
		s2 = in.next();
		a=Integer.parseInt(s1);
		b=Integer.parseInt(s2);
		System.out.printf("두 수의 합 = %d",a+b);
		in.close();
	}
}
