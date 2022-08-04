import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	static int n;
	static int[] dy = {-1,0,1,0,-1,1,1,-1};
	static int[] dx = {0,1,0,-1,1,1,-1,-1};
	static boolean check;
	static char[][] map;
	static char[][] res;
	static char[][] out;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());	
		check = false;
		
		map = new char[n][n];
		res = new char[n][n];
		out = new char[n][n];
		
		for(int i=0;i<n;i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i=0;i<n;i++) {
			res[i] = br.readLine().toCharArray();
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				func(i,j);			
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(check && map[i][j]=='*')
					out[i][j]='*';
				sb.append(out[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	public static void func(int y, int x) {
		if(res[y][x]=='.') {
			out[y][x] = '.';
			return;
		}
		
		if(map[y][x]=='*') {
			out[y][x] = '*';
			check = true;
			return;
		}
		
		int cnt = 0;
		for(int i=0;i<8;i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny<0||nx<0||ny>=n||nx>=n) continue;
			
			if(map[ny][nx]=='*') {
				cnt++;
			}
		}
		out[y][x] = (char) ('0' + cnt);	
	}
}
