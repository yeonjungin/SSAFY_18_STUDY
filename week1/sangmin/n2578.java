import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	static final int N = 5;
	static boolean bingo = false;
	static int[][] map = new int[N][N];
	static int[][] callNum = new int[N][N];
	static boolean[][] visited = new boolean[N][N];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				callNum[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bingo();
		
		br.close();
	}
		
	public static void bingo() {	
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				cnt++;
				if(bingoCheck(callNum[i][j]) >= 3){
					System.out.println(cnt);
					return;
				}
			}
		}
	}
	
	public static int bingoCheck(int num) {
		checkNum(num);
		return row(num) + column(num) + diagonal(num);
	}
	
	
	public static void checkNum(int num) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==num) {
					visited[i][j] = true;
					return;
				}
			}
		}
	}
	
	public static int row(int num) {
		int bingo = 0;
		for(int i=0;i<N;i++) {
			int cnt = 0;
			for(int j=0;j<N;j++) {
				if(visited[i][j]) cnt++;
			}
			if(cnt==5) bingo++;
		}
		return bingo;
	}

	public static int column(int num) {
		int bingo = 0;
		for(int j=0;j<N;j++) {
			int cnt = 0;
			for(int i=0;i<N;i++) {
				if(visited[i][j]) cnt++;
			}
			if(cnt==5) bingo++;
		}
		return bingo;
	}

	public static int diagonal(int num) {
		int bingo = 0;
		int cnt = 0;
		for(int i=0;i<N;i++) {
			if(visited[i][i]) cnt++;
		}
		if(cnt==5) bingo++;
		
		cnt = 0;
		for(int i=0;i<N;i++) {
			if(visited[i][N-i-1]) cnt++;
		}
		if(cnt==5) bingo++;
		
		return bingo;
	}
	
}
