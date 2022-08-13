import java.util.*;
import java.io.*;

public class Main {
	
	static int[][][] memo = new int[21][21][4]; // +1, -1 되니까 2개 더 확장
	static int[] dx = {1, 1, 0, -1}, dy = {0, 1, 1, 1};
	static int[][] a = new int[21][21];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();	
		
		for (int i = 1; i <= 19; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			for (int j = 1; j <= 19; j++) 
				a[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int f = 0;
		for (int j = 1; j <= 19; j++) { //왼쪽부터 나와야 해서 i, j 탐색 순서 바꿈
			for (int i = 1; i <= 19; i++) {
				if (a[i][j] != 0) { 
					for (int d = 0; d < 4; d++) {
						if (memo[i][j][d] == 0 && calc(i, j, d, a[i][j]) == 5) {
							sb.append(a[i][j] + "\n" + i + " " + j + "\n");
							f = 1;
						}
					}
				}
			}
		}
		
		if (f == 0) //오목이 아닌 경우
			sb.append(0);
		
		bw.write(sb.toString());
		br.close(); bw.close();	
	}
	
	public static int calc(int x, int y, int dir, int color) { //카운트 값 메모(누적합)
		int nx = x + dx[dir], ny = y + dy[dir];

		if (a[nx][ny] == color)
			return memo[nx][ny][dir] = calc(nx, ny, dir, color) + 1;

		return 1;
	}
}