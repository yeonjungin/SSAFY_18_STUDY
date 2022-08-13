import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for (int k = 0; k < t; k++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int[][] a = new int[n][n];
			for (int i = 0; i < n; i++) {
				st= new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) 
					a[i][j] = Integer.parseInt(st.nextToken());
			}
			
			if (d > 0) { //시계 방향
				for (int l = 0; l < d / 45; l++) {
					int[][] tmp = new int[n][n];
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							if (i == j) // case: \
								tmp[i][j] = a[n / 2][j];
							else if (i + j == n - 1) // case: /
								tmp[i][j] = a[i][n / 2];
							else if (j == n / 2) // case: |
								tmp[i][j] = a[i][i];
							else if (i == n / 2) // case: -
								tmp[i][j] = a[n - j - 1][j];
							else 
								tmp[i][j] = a[i][j];
						}
					}
					a = tmp;
				}
			}
			
			else {
				d *= -1;
				for (int l = 0; l < d / 45; l++) {
					int[][] tmp = new int[n][n];
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							if (i == j) // case: \
								tmp[i][j] = a[i][n / 2];
							else if (i + j == n - 1) // case: /
								tmp[i][j] = a[n / 2][j];
							else if (j == n / 2) // case: |
								tmp[i][j] = a[i][n - i - 1];
							else if (i == n / 2) // case: -
								tmp[i][j] = a[j][j];
							else 
								tmp[i][j] = a[i][j];
						}
					}
					a = tmp;
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					sb.append(a[i][j] + " ");
				sb.append("\n");
			}
		}
		
		bw.write(sb.toString());
		br.close(); bw.close();	
	}
}