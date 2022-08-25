import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	// 행의 수(N), 열의 수(M), 공격 거리 제한(D)
	static int N, M, D;
	// 제거한 최대 적의 수 
	static int maxCnt = -1;
	
	// 방향 벡터 {좌,상,우}
	static int[] dy = {0,-1,0};
	static int[] dx = {-1,0,1};
	
	// 격자판 배열 - [index] : (1,2,...,N)(1,2,...,M)
	static int[][] map;
	// 디펜스를 진행하는 동안 사용할 임시 격자판 - [index] : (1,2,...,N)(1,2,...,M)
	static int[][] mapBuf;
	// 궁수들의 위치 배열 - [index] : (1,2,...,M)
	static boolean[] archer;
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    // 입력
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    D = Integer.parseInt(st.nextToken());
	    
	    // 배열 할당
	    map = new int[N+1][M+1];
	    mapBuf = new int[N+1][M+1];
	    archer = new boolean[M+1];
	    
	    // 배열 초기화
	    for(int i=1;i<=N;i++) {
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=1;j<=M;j++) {
	    		map[i][j] = Integer.parseInt(st.nextToken());
	    	}
	    } br.close();
	    
	    // 궁수 자리 선정 후 디펜스 시작
	    setArcher(1,0);
	    
	    // 결과 출력
	    System.out.println(maxCnt);
	    
	}
	
	// 3명의 궁수 자리 선정
	public static void setArcher(int index, int t) {
		// 3명이 모두 자리 선정이 완료되었다면 디펜스 시작
		if(t==3) {
			defence();
			return;
		}
		
		// 조합 선택
		for(int i=index;i<=M;i++) {
			if(!archer[i]) {
				archer[i] = true;
				setArcher(i+1,t+1);
				archer[i] = false;
			}
		}
	}
	
	// 하나의 디펜스 동작 함수
	public static void defence() {
		// 임시 격자판 mapBuf을 초기화 (map을 깊은 복사한다)
		cloneMap();
		
		// 죽인 적들의 수
		int cnt = 0;
		
		// N번의 공격과 적들의 움직임
		// 적들은 한칸씩 움직이므로 모든 적들이 제외되려면 최대 N번 움직여야 한다
		for(int i=1;i<=N;i++) {
			// 공격을 하고
			attack();
			// 죽인 적들을 확인하고 적들이 한칸 움직인다
			cnt += moveEnemy();
		}
		maxCnt = Math.max(cnt, maxCnt);
	}

	// 궁수가 공격을 한다
	public static void attack() {
		for(int i=1;i<=M;i++) {
			if(!archer[i]) continue;

			// 궁수가 있는 곳에서 범위 내의 공격 시작
			bfs(N,i,D);
		}
		
	}
	
	// 적이 한칸 이동한다
	public static int moveEnemy() {
		// 죽인 적들의 수
		int cnt = 0;
		
		// 격자판 마지막 행 확인
		for(int j=1;j<=M;j++) {
			// bfs를 통해 적을 죽였다면 2 이상의 값을 가진다
			// 값이 2 이상인 경우 궁수가 죽인 적이므로 0으로 바꿔준다
			if(mapBuf[N][j] > 1) {
				mapBuf[N][j] = 0;
				cnt++;
			}
		}
		
		// 나머지 행을 하나씩 앞당김
		for(int i=N;i>=1;i--) {
			for(int j=1;j<=M;j++) {
				// 값이 2 이상인 경우 궁수가 죽인 적이므로 0으로 바꿔준다
				if(mapBuf[i-1][j] > 1) {
					mapBuf[i][j] = 0;
					cnt++;
				}
				else {
					mapBuf[i][j] = mapBuf[i-1][j];
				}
			}
		}
		return cnt;
	}
	
	public static void bfs(int y, int x, int d) {
		boolean[][] visited = new boolean[N+1][M+1];
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(y,x,d-1));
		visited[y][x] = true;
		
		while(!q.isEmpty()) {
			y = q.peek().y;
			x = q.peek().x;
			d = q.peek().d;
			q.poll();
			
			// 1이면 적이 있고, 2 이상이면 어떤 궁수가 적을 죽인 상황
			// 궁수는 동시에 공격하므로 동시에 죽일 수도 있다
			if(mapBuf[y][x]>=1) {
				mapBuf[y][x]++;
				return;
			}
			
			if(d==0) {
				continue;
			}

			
			for(int i=0;i<3;i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				// map 범위 안에 있는 좌, 상, 우 좌표값을 넣어준다.
				if(ny>=1 && nx>=1 && ny<=N && nx<=M && !visited[ny][nx]) {
					q.add(new Pair(ny,nx,d-1));
					visited[ny][nx] = true;
				}
			}
			
		}
	}
	
	public static void cloneMap() {
		for(int i=0;i<=N;i++) {
			for(int j=0;j<=M;j++) {
				mapBuf[i][j] = map[i][j];
			}
		}
	}
}

class Pair{
	int y;
	int x;
	int d;
	Pair(int y, int x, int d){
		this.y=y;
		this.x=x;
		this.d=d;
	}
}