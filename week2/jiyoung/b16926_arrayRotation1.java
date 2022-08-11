package week02;

import java.util.Scanner;

//16926. 배열 돌리기 1
public class b16926_arrayRotation1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };

		int n = sc.nextInt();
		int m = sc.nextInt();
		int r = sc.nextInt();
		
		int [][] arr = new int [n][m];
		
		for (int x = 0; x < n; x++)
			for (int y = 0; y < m; y++)
				arr[x][y] = sc.nextInt();
		
		int depth = Math.min(n, m) / 2; // 돌릴 내부 원의 수
		
		for (int rot = 0; rot < r; rot++) { // r만큼 반복
			for (int d = 0; d < depth; d++) { // 돌릴 내부 원의 수만큼 반복
				int seq = 0; // 하우상좌(0123) 중 몇 번째 방향인지
				int stdX = d, stdY = d; // 맨 처음 시작할 기준 칸은 원 중 가장 맨 왼쪽 위 칸
				int tmp = arr[stdX][stdY]; // 맨 처음 칸의 값 저장
				
				while (seq < 4) {
					int nowX = stdX + dx[seq]; // 기준 칸에서부터 이동한 현재 좌표
					int nowY = stdY + dy[seq];
					
					if (nowX >= d && nowY >= d && nowX < n - d && nowY < m - d) {
						arr[stdX][stdY] = arr[nowX][nowY];
						stdX = nowX;
						stdY = nowY;
					} else seq++;
				}
				arr[d + 1][d] = tmp;
			}
		}
		
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++)
				System.out.print(arr[x][y] + " ");
			System.out.println();
		}
	}
}