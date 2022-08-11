import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] array;
	static int cnt;
	
	public static boolean can_go(int x,int y) {
		if (x>=0 && x<n && y>=0 && y<n && array[x][y]==0) return true;
		return false;
	}
	
	public static void move(int x,int y, int dir) {
		if (x==n-1 && y==n-1) {
			cnt++;
			return;
		}
		
		int[] dxs= {0,1,1}; // →,↓,↘ (가로, 세로, 대각선 순)
		int[] dys= {1,0,1}; // →,↓,↘
		
		if (dir==0) { // 현재 방향이 가로일 경우 : →, ↘
			for (int d = 0; d < 3; d+=2) {
				int nx=x+dxs[d];
				int ny=y+dys[d];
				if (can_go(nx,ny)) {
					if (d==0) move(nx,ny,d);
					else {
						if (can_go(nx,ny) && can_go(x+dxs[0],y+dys[0]) && can_go(x+dxs[1],y+dys[1])) {
							move(nx,ny,d);
						}
					}
				}
			}
		}else if(dir==1) { // 현재 방향이 세로일 경우 : ↘, ↓ 
			for (int d = 1; d < 3; d++) {
				int nx=x+dxs[d];
				int ny=y+dys[d];
				if (can_go(nx,ny)) {
					if (d==1) move(nx,ny,d);
					else {
						if (can_go(nx,ny) && can_go(x+dxs[0],y+dys[0]) && can_go(x+dxs[1],y+dys[1])) {
							move(nx,ny,2);
						}
					}
				}
				
			}
		}else { // 현재 방향이 대각선일 경우 : →, ↘, ↓
			for (int d = 0; d < 3; d++) {
				int nx=x+dxs[d];
				int ny=y+dys[d];
				if (can_go(nx,ny)) {
					if (d<=1) move(nx,ny,d);
					else {
						if (can_go(nx,ny) && can_go(x+dxs[0],y+dys[0]) && can_go(x+dxs[1],y+dys[1])) {
							move(nx,ny,d);
						}
					}
				}
			}
		}
}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n= Integer.parseInt(br.readLine()); // 격자 크기
		array=new int[n][n];
		
		for (int i = 0; i < n; i++) {			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				array[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int dir = 0; // 초기 방향 : 가로
		move(0,1,dir); // 파이프 앞쪽 위치 -> 이게 n-1, n-1이 되어야함
		System.out.println(cnt);
		
	}
}