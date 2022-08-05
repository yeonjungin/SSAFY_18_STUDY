package week01;

import java.util.Scanner;

public class b2578_bingo {

	public static void main(String[] args) {
		int n = 5;
		int [][] bingo = new int [n][n];
		int [] check = new int [n*n];
		int [][] result = new int [n][n];
		int sayBingo = 0;
		
		Scanner sc = new Scanner(System.in);
		
		// 철수의 빙고 입력
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < n; j++) 
				bingo[i][j] = sc.nextInt();
		
		// 사회자가 부르는 수 입력
		for (int i = 0; i < n*n; i++) {
			check[i] = sc.nextInt();
		}
		
		// 빙고 3개 될 때까지 반복해서 검사
		while(sayBingo < 3) {
			// k = turn
			// i, j = bingo의 좌표
			loopOut :
			for (int k = 0; k < 25; k++) {
				loopNext :
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (check[k] == bingo[i][j]) {
							result[i][j] = 1;
							sayBingo = checkBingo(result);
							if (sayBingo >= 3) {
								System.out.println(k + 1);
								break loopOut;
							}
							break loopNext;
						}
					}
				}
			}
		}
	}
	
	public static int checkBingo(int [][] result) {
		int sayBingo = 0;
		int cnt = 0;
		int [][] line1 = {{4, 0}, {3, 1}, {2, 2}, {1, 3}, {0, 4}}; // 좌하-우상 대각선
		int [][] line2 = {{0, 0}, {1, 1}, {2, 2}, {3, 3}, {4, 4}}; // 좌상-우하 대각선
		int cntL1 = 0;
		int cntL2 = 0;
		
		// 가로줄 체크
		for (int i = 0; i < 5; i++) {
			cnt = 0;
			for (int j = 0; j < 5; j++) {
				if (result[i][j] == 1) cnt++;
				else cnt = 0;
			}
			if (cnt == 5) sayBingo++;
		}
		
		// 세로줄 체크
		for (int i = 0; i < 5; i++) {
			cnt = 0;
			for (int j = 0; j < 5; j++) {
				if (result[j][i] == 1) cnt++;
				else cnt = 0;
			}
			if (cnt == 5) sayBingo++;
		}
		
		// 대각선 체크
		for (int i = 0; i < 5; i++) {
			if (result[line1[i][0]][line1[i][1]] == 1) cntL1++;
			if (result[line2[i][0]][line2[i][1]] == 1) cntL2++;
		}
		if (cntL1 == 5) sayBingo++;
		if (cntL2 == 5) sayBingo++;
		
		return sayBingo;
	}
}