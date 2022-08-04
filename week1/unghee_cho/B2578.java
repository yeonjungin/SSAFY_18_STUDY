package study._220804;

import java.util.Scanner;

public class B2578 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[][] board = new int[5][5];
		int result = 0;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		int n;
		int bingo;
		boolean isBingo = true;
		for (int x = 1; x < 26; x++) {
			bingo = 0;
			n = sc.nextInt();
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (board[i][j] == n) {
						board[i][j] = 0;
					}
				}
			}
			
			for (int j = 0; j < 5; j++) {
				isBingo = true;
				if (board[0][j] == 0) {
					for (int i = 1; i < 5; i++) {
						if (board[i][j] != 0) {
							isBingo = false;
							break;
						}
					}
					if (isBingo) {
						bingo++;
					}
				}
			}
			
			for (int i = 0; i < 5; i++) {
				isBingo = true;
				if (board[i][0] == 0) {
					for (int j = 1; j < 5; j++) {
						if (board[i][j] != 0) {
							isBingo = false;
							break;
						}
					}
					
					if (isBingo) {
						bingo++;
					}
				}
			}
			
			isBingo = true;
			if (board[0][0] == 0) {
				int i = 1;
				int j = 1;
				for (int z = 1; z < 5; z++) {
					if (board[i][j] != 0) {
						isBingo = false;
						break;
					}
					i++;
					j++;
				}
				if (isBingo) {
					bingo++;
				}
			}
			
			isBingo = true;
			if (board[0][4] == 0) {
				int i = 1;
				int j = 3;
				
				for (int z = 1; z < 5; z++) {
					if (board[i][j] != 0) {
						isBingo = false;
						break;
					}
					i++;
					j--;
				}
				
				if (isBingo) {
					bingo++;
				}
			}
			
			if (bingo >= 3) {
				result = x;
				break;
			}
		}
		System.out.println(result);
	}
	
}
