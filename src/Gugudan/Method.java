package Gugudan;

import java.util.Scanner;

public class Method{
		Scanner in=new Scanner(System.in);;
		int dan;
		
		public void input() {
			System.out.println("���� �Է� : ");
			dan = in.nextInt();
		}
		public void multi() {
			for(int i=1; i<10; ++i) {
				if(dan<1) { 
					System.out.println("1������ �������� �Ұ��� �մϴ�"); break;
				}else System.out.println(dan+"X"+i+"= "+dan*i);
			}
		}
		
		public void restart() {
			System.out.println("1.���Է� 2.���α׷����� : ");
			int selcet = in.nextInt();
			switch(selcet) {
			case 1 : break;
			case 2 : System.out.println("���α׷��� �����մϴ�."); System.exit(0);
				default : System.out.println("�ٽ��Է��ϼ���"); restart();
			}
		}
		
}
