package week02;

import java.util.Scanner;

//17276. 배열 돌리기
public class b17276_arrayRotation {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int i = 0; i < T; i++) {
			int n = sc.nextInt();
			int [][] arr = new int [n + 1][n + 1];
			int [][] res = new int [n + 1][n + 1];
			int d = sc.nextInt();
			for (int x = 1; x <= n; x++)
				for (int y = 1; y <= n; y++) {
					arr[x][y] = sc.nextInt();
					res[x][y] = arr[x][y];
				}
			
			if (d < 0) d += 360;
			else if (d == 360) d = 0;
			d /= 45;
			if (d != 0) rotation45(arr, res, d);
			
			for (int x = 1; x <= n; x++) {
				for (int y = 1; y <= n; y++)
					System.out.print(res[x][y] + " ");
				System.out.println();
			}
		}
	}
	
	public static void rotation45(int [][] arr, int [][] res, int d) {
		int n = arr.length - 1;

		for (int i = 1; i <= n; i++) {
			int [] lines = new int [9];
			lines[1] = arr[i][(n + 1) / 2]; 				// 가운데 열, 위에서부터 3
			lines[5] = arr[(n + 1) - i][(n + 1) / 2]; 	// 가운데 열, 아래에서부터 23
			
			lines[2] = arr[i][i];						// 주 대각선, 왼쪽 위에서부터 1
			lines[6] = arr[(n + 1) - i][(n + 1) - i];// 주 대각선, 오른쪽 아래에서부터 25
			
			lines[3] = arr[(n + 1) / 2][i]; 				// 가운데 행, 왼쪽에서부터 11
			lines[7] = arr[(n + 1) / 2][(n + 1) - i]; 	// 가운데 행, 오른쪽에서부터 15
			
			lines[4] = arr[(n + 1) - i][i];			// 부 대각선, 왼쪽 아래에서부터 21
			lines[8] = arr[i][(n + 1) - i];			// 부 대각선, 오른쪽 위에서부터 5
		
			if (d <= 5) {
				// 가운데 행(왼쪽부터 11)에 부 대각선을
				res[(n + 1) / 2][i] = lines[d + 3];
				// 주 대각선(왼쪽 위부터 1)에 가운데 행을
				res[i][i] 			= lines[d + 2];
			}
			else if (d == 6) {
				// 가운데 행(왼쪽부터 11)에
				res[(n + 1) / 2][i] = lines[d + 3 - 8];
				// 주 대각선(왼쪽 위부터 1)에
				res[i][i] 			= lines[d + 2];
			}
			else if (d == 7) {
				// 가운데 행(왼쪽부터 11)에
				res[(n + 1) / 2][i] = lines[d + 3 - 8];
				// 주 대각선(왼쪽 위부터 1)에
				res[i][i] 			= lines[d + 2 - 8];
			}
			
			// 가운데 열(위부터 3)에 주 대각선을
			res[i][(n + 1) / 2] = lines[d + 1];
			// 부 대각선(오른쪽 위에서부터 5)에 가운데 열을
			res[i][(n + 1) - i] = lines[d];
		}
	}
}