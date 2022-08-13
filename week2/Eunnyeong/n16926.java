import java.util.*;

import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[][] a = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				a[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for (int k = 0; k < r; k++) {
			int[][] tmp = new int[n][m];
			int z1 = n - 1, z2 = m - 1, x = 0, y = 0, turn = 1, idx = 0;
			while(z1 > 0 && z2 > 0) {
				//v
				for (int i = 0; i < z1; i++) {
					tmp[x + turn][y] = a[x][y];
					x += turn;
				}
				tmp[x][y + 1] = a[x][y];
				y++; z2--;
				
				//->
				for (int i = 0; i < z2; i++) {	
					tmp[x][y + turn] = a[x][y];
					y += turn;
				}
				tmp[x - 1][y] = a[x][y];
				x--; z1--;
				
				turn *= -1;
				
				//^
				for (int i = 0; i < z1; i++) {
					tmp[x + turn][y] = a[x][y];
					x += turn;
				}
				tmp[x][y - 1] = a[x][y];
				y--;
				
				//<-
				for (int i = 0; i < z2; i++) {	
					tmp[x][y + turn] = a[x][y];
					y += turn;
				}
				x++; y++; turn = 1; idx++;
				z1 = (n - (2 * idx))- 1; z2 = (m - (2 * idx))- 1;	
			}
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					a[i][j] = tmp [i][j];
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(a[i][j] + " ");
			}
			sb.append("\n");	
		}
		bw.write(sb.toString());
		br.close(); bw.close();
	}	
}