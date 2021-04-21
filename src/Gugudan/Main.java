package Gugudan;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Impliment mt = new Impliment();
		
		while(true) {
			mt.input();
			mt.multi();
			mt.restart();
		}
	}
}
