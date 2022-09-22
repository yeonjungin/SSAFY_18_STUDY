import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int f = Integer.parseInt(br.readLine());
		
		int a[][] = new int[n][n];
		
		//가운데서부터 cur만큼 위, 오른쪽, 아래, 왼쪽 순으로 반복해서 이동
		int x = n/2, y = n/2, num = 1, cur = 1;
		a[x][y] = num++;
		while (true) {
			for (int i = 0; i < cur; i++)
				if (x - 1 >= 0)
					a[--x][y] = num++;
			
			if (x == 0 && y == 0) break;
			
			for (int i = 0; i < cur; i++)
				if (y + 1 < n)
					a[x][++y] = num++;
			
			cur++;
			
			for (int i = 0; i < cur; i++)
				if (x + 1 < n)
					a[++x][y] = num++;
			
			for (int i = 0; i < cur; i++)
				if (y - 1 >= 0)
					a[x][--y] = num++;	
			
			cur++;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++) {
				sb.append(a[i][j] + " ");
				if (a[i][j] == f) {
					x = i; y = j;
				}
			}
			sb.append("\n");
		}
		
		sb.append((x + 1) + " " + (y + 1));
		bw.write(sb.toString());
		bw.close();
	}
}