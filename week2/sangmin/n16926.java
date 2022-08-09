import java.util.*;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException; 

public class Main {  
    static int n, m, r;
    static int[][] map;
    static int[] dx = {1,0,-1,0}; // 우, 상, 좌, 하
    static int[] dy = {0,1,0,-1};
    
	public static void main(String[] args) throws Exception  {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder();
	    StringTokenizer st;
		
	    st = new StringTokenizer(br.readLine());	
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		
		// 입력
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int group_cnt = Math.min(n, m) / 2;
		
		// 회전
		for(int i=0;i<r;i++) {
			for(int j=0;j<group_cnt;j++) {
				int x = j, y = j;
				
				int val = map[y][x];
				for(int k=0;k<4;k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					
					if(nx<j||nx>=m-j||ny<j||ny>=n-j) continue;
					
					map[y][x] = map[ny][nx];
					x = nx; 
					y = ny;
					k--;
				}
				map[j+1][j] = val;
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				sb.append(map[i][j]+" ");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}	
}