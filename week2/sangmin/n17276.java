import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int n,d;
	static int[][] map;
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	    
	    StringTokenizer st; 
	    
	    int T = Integer.parseInt(br.readLine());
	    
	    for(int t=1;t<=T;t++) {
	    	st = new StringTokenizer(br.readLine());
	    	n = Integer.parseInt(st.nextToken());
	    	d = Integer.parseInt(st.nextToken());
	    	
	    	map = new int[n][n];	    
	    	
	    	for(int i=0;i<n;i++) {
	    		st = new StringTokenizer(br.readLine());
	    		for(int j=0;j<n;j++) {
	    			map[i][j] = Integer.parseInt(st.nextToken());
	    		}
	    	}

	    	int D = (d + 360) % 360 / 45;
	    	
	    	for(int i=0;i<D;i++) {
	    		rotate();	    		
	    	}
	    		    	
	    	printMap();
	    	
	    }	
	    System.out.println(sb.toString());
	}
	
	public static void rotate() {
		int a = n/2;
		
		for(int i=a;i>0;i--) {
			int buf = map[a-i][a-i];
			// ^
			map[a-i][a-i] = map[a][a-i];
			map[a][a-i] = map[a+i][a-i];
			// <-
			map[a+i][a-i] = map[a+i][a];
			map[a+i][a] = map[a+i][a+i];
			// V
			map[a+i][a+i] = map[a][a+i];
			map[a][a+i] = map[a-i][a+i];
			// ->
			map[a-i][a+i] = map[a-i][a];
			map[a-i][a] = buf;
		}
	}
	
	public static void printMap() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
	}
}