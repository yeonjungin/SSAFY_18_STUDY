import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static final int N = 19;
	static int[][] map;
	static int[] dy = {-1,0,1,1};
	static int[] dx = {1,1,1,0};
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	    
	    StringTokenizer st; 
	    
	    map = new int[N+1][N+1];
	    
	    for(int i=1;i<=N;i++) {
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=1;j<=N;j++) {
	    		map[i][j] = Integer.parseInt(st.nextToken());
	    	}
	    }
	    
	    for(int i=1;i<=N;i++) {
	    	for(int j=1;j<=N;j++) {
	    		if(map[i][j]!=0 && func(i,j,map[i][j])) {
	    			System.exit(0);
	    		}
	    	}
	    }
	    System.out.println("0");
	}
	
	public static boolean func(int y, int x, int num) {
		for(int d=0;d<4;d++) {
			int ny = y;
			int nx = x;
			
			int cnt = 1;
			while(true) {
				ny += dy[d];
				nx += dx[d];
				if(ny<=0||nx<=0||ny>N||nx>N||map[ny][nx]!=num) {
					break;		
				}
				cnt++;
			}			
			if(cnt==5) {
				if(map[y-dy[d]][x-dx[d]]==num) continue;
				
				if(num==1)
					System.out.printf("1\n%d %d\n",y,x);
				else
					System.out.printf("2\n%d %d\n",y,x);
				return true;
			}
		}
		return false;
	}
}