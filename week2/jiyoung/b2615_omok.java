package week02;

import java.util.Scanner;

//2615. 오목
public class b2615_omok {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [][] map = new int [30][30];
		
		for (int i = 1; i < 20; i++)
			for (int j = 1; j < 20; j++)
				map[i][j] = sc.nextInt();
		boolean victory = false;
		
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				if (map[i][j] != 0 
					&& (row(map, i, j) || col(map, i, j) 
						|| leftUp(map, i, j) || leftDown(map, i, j) )) {
					victory = true;
					System.out.println(map[i][j] + "\n" + i + " " + j);
				}
			}
		}
		if (!victory) System.out.println("0");
	}
	
	// 가로선 →
	public static boolean row(int [][] map, int i, int j) {
		int flag = map[i][j]; 
		if (flag == map[i][j + 1] && flag == map[i][j + 2]
			&& flag == map[i][j + 3] && flag == map[i][j + 4]
			&& flag != map[i][j + 5] && flag != map[i][j - 1])
			return true;
		return false;
	}
	
	// 세로선 ↓
	public static boolean col(int [][] map, int i, int j) {
		int flag = map[i][j]; 
		if (flag == map[i + 1][j] && flag == map[i + 2][j]
			&& flag == map[i + 3][j] && flag == map[i + 4][j]
			&& flag != map[i + 5][j] && flag != map[i - 1][j])
			return true;
		return false;
	}
	
	// 올라가는 대각선 ↗
	public static boolean leftUp(int [][] map, int i, int j) {
		int flag = map[i][j]; 
		if (i >= 5 
			&& flag == map[i - 1][j + 1] && flag == map[i - 2][j + 2]
			&& flag == map[i - 3][j + 3] && flag == map[i - 4][j + 4]
			&& flag != map[i - 5][j + 5] && flag != map[i + 1][j - 1])
			return true;
		return false;
	}
	
	// 내려가는 대각선 ↘
	public static boolean leftDown(int [][] map, int i, int j) {
		int flag = map[i][j]; 
		if (flag == map[i + 1][j + 1] && flag == map[i + 2][j + 2]
			&& flag == map[i + 3][j + 3] && flag == map[i + 4][j + 4]
			&& flag != map[i + 5][j + 5] && flag != map[i - 1][j - 1])
			return true;
		return false;
	}
}