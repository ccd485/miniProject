package Gugudan;

import java.util.Scanner;

public class Impliment{
		Scanner in=new Scanner(System.in);;
		int dan;
		
		public void input() {
			System.out.println("구구단 입력 : ");
			dan = in.nextInt();
		}
		public void multi() {
			for(int i=1; i<10; ++i) {
				if(dan<1) { 
					System.out.println("1이하의 단수는 지원하지않습니다"); break;
				}else System.out.println(dan+"X"+i+"= "+dan*i);
			}
		}
		
		public void restart() {
			System.out.println("1.재입력 2.프로그램종료 : ");
			int selcet = in.nextInt();
			switch(selcet) {
			case 1 : break;
			case 2 : System.out.println("프로그램이 종료됩니다"); System.exit(0);
				default : System.out.println("다시입력하세요"); restart();
			}
		}
		
}
