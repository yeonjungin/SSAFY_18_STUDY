package study._220804;

import java.util.Scanner;

public class B20546 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int money = sc.nextInt();
		int jun[] = {money, 0};
		int sung[] = {money, 0};
		
		int[] prices = new int[14];
		
		for (int i = 0; i < 14; i++) {
			prices[i] = sc.nextInt();
		}
		
		int up = 0;
		int down = 0;
		for (int i = 0; i < 14; i++) {
			if (i != 0) {
				if (prices[i] > prices[i-1]) {
					up++;
					down = 0;
				}
				else if (prices[i] < prices[i-1]) {
					down++;
					up = 0;
				}
				else {
					up = 0;
					down = 0;
				}
			}
			if (jun[0] >= prices[i]) {
				jun[1] += jun[0] / prices[i];
				jun[0] = jun[0] % prices[i];
			}
			if (up >= 3) {
				sung[0] += sung[1] * prices[i];
				sung[1] = 0;
			}
			if (down >= 3) {
				sung[1] += sung[0] / prices[i];
				sung[0] = sung[0] % prices[i];
			}
		}
		
		jun[0] += jun[1] * prices[13];
		sung[0] += sung[1] * prices[13];
		
		String result = "";
		if (jun[0] > sung[0]) {
			result = "BNP";
		}
		else if (jun[0] < sung[0]) {
			result = "TIMING";
		}
		else {
			result = "SAMESAME";
		}
		
		System.out.println(result);
	}

}
