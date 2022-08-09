import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 집의 크기 n
    static int n;
    // 방법의 개수 cnt
    static int cnt = 0;
    // 집 배열 map
    static int[][] map;
    // 방향 벡터 ( →, ↓, ↘ )
    static int[] dy = {0,1,1};
    static int[] dx = {1,0,1};

	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	    
	    // n 입력
	    n = Integer.parseInt(br.readLine());
	    
	    // 집 배열 할당
	    map = new int[n+1][n+1];
	    
	    // 집 배열 초기화
	    for(int i=1;i<=n;i++) {
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=1;j<=n;j++) {
	    		map[i][j] = Integer.parseInt(st.nextToken());
	    	}
	    } br.close();
	    
	    // 파이프를 이동시키는 메서드
	    dfs(1,2,0);
	    
	    // 결과 출력
	    System.out.println(cnt);	    
	}
	
	/**
	 *  파이프의 앞쪽만 이동시켜도 문제가 없으므로 하나의 좌표값만 이동시킴 
	 *  
	 *  (y,x) : 현재 좌표 (파이프의 앞쪽 좌표)
	 *  d : 현재 방향 ( → : 0, ↓ : 1, ↘ : 2 )
	 *  
	 *  현재 좌표에서 →, ↓, ↘ 로 이동할 수 있는지 확인
	 *  이동할 수 있다면 다음 좌표로 넘어간다
	 */
	public static void dfs(int y, int x, int d) {
		// 현재 좌표가 도착 지점이라면
		if(x==n && y==n) {
			// 방법의 개수를 증가시키고 종료
			cnt++;
			return;
		}
		
		// 현재좌표에서 ( → : 0, ↓ : 1, ↘ : 2 )의 3가지 방향을 탐색한다
		for(int dir=0;dir<3;dir++) {
			// 1. 현재 → 방향이면 ↓으로 회전이 불가능
			// 2. 현재 ↓ 방향이면 →으로 회전이 불가능
			// 두가지 경우 중 하나라도 해당하면 continue
            if((d==0 && dir==1) || (d==1 && dir==0)) continue;
            
            // 이동한 좌표
            int ny = y+dy[dir];
            int nx = x+dx[dir];
            
            // 이동은 +방향으로만 하므로 기본적으로 0보다 크다
            // 이동한 좌표가 범위를 벗어나면 continue
            if(ny>n||nx>n) continue;
            
            // 이동한 좌표가 빈 칸이라면 이동이 가능하다
            if(map[ny][nx]==0) {
            	// 현재 ↘ 방향이면 오른쪽 좌표와 아래 좌표가 빈 곳이여야 이동할 수 있다
            	if(dir==2 && (map[ny-1][nx]!=0 || map[ny][nx-1]!=0)) continue;
            	
            	// 이동한 좌표에서 해당 작업 반복
                dfs(ny,nx,dir);
            }
        }
	}
}