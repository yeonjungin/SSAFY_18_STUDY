package week01;

import java.util.Scanner;

public class b4396_minesweeper {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // n*n 사이즈의 격자 크기
		String [] fieldMine = new String[n]; // 지뢰가 있는 칸 표시 격자
		String [] fieldOpen = new String[n]; // 열린 칸 표시 격자
		char [][] fieldResult = new char[n][n]; // 출력 결과 격자

		// 입력 값 기반으로 기본 세팅
		for (int i = 0; i < n; i++) {
			fieldMine[i] = sc.next(); 
		}
		for (int i = 0; i < n; i++) {
			fieldOpen[i] = sc.next(); 
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				fieldResult[i][j] = '.'; 		
			}
		}
		
		// 열린 칸이면('x'이면) 출력 결과 격자에 기록
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < n; j++) 
				if (fieldOpen[i].charAt(j) == 'x') 
					mineCheck(fieldMine, fieldResult, i, j, n);
		
		// 기록이 끝났으면 출력 결과 격자인 fieldResult를 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(fieldResult[i][j]);
			}
			System.out.println();
		}
	}

	public static void mineCheck(String [] fieldMine, char [][] fieldResult, int i, int j, int n) {
		int mineCount = 0;	// 지뢰 수
		int [][] grid = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
		int nowR = i;		// 지뢰 체크 기준 칸 행
		int nowC = j;		// 지뢰 체크 기준 칸 열
		
		// 만약 이 칸이 지뢰가 이닐 경우
		if (fieldMine[i].charAt(j) == '.') {
			// 주위 8칸 검사
			for (int k = 0; k < 8; k++) {
				nowR = i + grid[k][0];
				nowC = j + grid[k][1];
				// 해당 칸이 격자 범위를 벗어나지 않은 칸이고 지뢰(*)라면
				if (nowR >= 0 && nowR < n && nowC >= 0 && nowC < n && fieldMine[nowR].charAt(nowC) == '*') 
					mineCount++; // 지뢰 수 증가
			}
			// 주위 8칸 전부 검사했으면 지뢰 수를 출력 결과에 저장
			fieldResult[i][j] = (char) (mineCount + '0');
		}
	
		// 만약 이 칸이 지뢰일 경우 -> 지뢰가 있는 모든 칸이 별표(*)로 표시되어야 함
		else if (fieldMine[i].charAt(j) == '*')
			for (int k = 0; k < n; k++)
				for (int l = 0; l < n; l++)
					if (fieldMine[k].charAt(l) == '*') fieldResult[k][l] = '*';
	}
}