package week02;

import java.util.Scanner;

//17070. 배열 돌리기
public class b17070_movePipe1 {
	static int n, cnt = 0;
	static int [][] home;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		home = new int [n + 1][n + 1];
		for (int x = 1; x <= n; x++)
			for (int y = 1; y <= n; y++)
				home[x][y] = sc.nextInt();
		
		dfs(1, 2, 0);
		System.out.println(cnt);
	}
	
	static public void dfs(int x, int y, int pipe) {
		if (x == n && y == n) {
			cnt++;
			return;
		}
		
		// 다음 파이프가 가로로 놓이려면 현재 가로 or 대각선
		if (pipe == 0 || pipe == 2)
			// 다음 파이프가 가로로 놓일 자리가 있으면
			if (y + 1 <= n && home[x][y + 1] == 0)
				dfs(x, y + 1, 0);
		
		// 다음 파이프가 세로로 놓이려면 현재 세로 or 대각선
		if (pipe == 1 || pipe == 2)
			// 다음 파이프가 세로로 놓일 자리가 있으면
			if (x + 1 <= n && home[x + 1][y] == 0)
				dfs(x + 1, y, 1);
		
		// 다음 파이프가 대각선으로 놓이려면 현재 상관 없음
		// 다음 파이프가 대각선으로 놓일 자리가 있으면
		if (x + 1 <= n && y + 1 <= n && home[x][y + 1] == 0
			&& home[x + 1][y] == 0 && home[x + 1][y + 1] == 0)
			dfs(x + 1, y + 1, 2);
	}
}