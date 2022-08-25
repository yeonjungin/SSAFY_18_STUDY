import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int r,c,k;

	static int n = 3,m =3;
	static int[][] map = new int[101][101];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = 3; m = 3;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(func());
	}
	
	static int func() {
		// 100초가 될 때까지 반복
		for(int t=0;t<=100;t++) {
			// 타겟 좌표의 값이 목표값과 같다면 종료 
			if(map[r][c]==k) {
				return t;
			}
			// 연산 시작
			op();
		}
		return -1;
	}
	
	static void op() {
		// R연산
		if(n>=m) {
			// 각각의 행에 대해 R연산 수행
			for(int i=0;i<n;i++) {
				R(i);
			}
		}
		// C연산
		else {
			// 각각의 열에 대해 C연산 수행
			for(int j=0;j<m;j++) {
				C(j);
			}
		}
	}
	
	static void R(int i) {
		// 정렬을 위해 사용
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		// 숫자들의 누적 개수를 확인하기 위해 사용
		Map<Integer,Integer> temp = new HashMap<>();
		
		for(int j=0;j<m;j++) {
			// 0이 나오면 이후로는 볼 필요가 없음
			if(map[i][j]==0) continue;
			/**
			 * 처음엔 contains()를 통해 숫자가 있는지를 보고 값을 갱신하였다
			 * 찾아보니 compute()를 통해 key가 있을 때, 없을 때를 구분지어 value를 수정할 수 있다
			 */
			temp.compute(map[i][j], (num,cnt)->cnt==null?1:cnt+1);
		}
		
		// 숫자와 등장 횟수를 우선순위 큐에 넣는다
		for(int key : temp.keySet()) {
			pq.add(new Pair(key,temp.get(key)));
		}
		
		// 우선순위 큐로 정렬된 숫자들을 배열에 넣어준다
		int j = 0;
		while(!pq.isEmpty()) {
			map[i][j++] = pq.peek().num;
			map[i][j++] = pq.peek().cnt;
			pq.poll();
		}
		
		// 최대 열의 개수를 갱신한다
		m = Math.max(m, j);
		
		// 배열의 남은 공간에 0을 넣어준다
		for(;j<100;j++) {
			map[i][j] = 0;
		}
	}
	
	static void C(int j) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		Map<Integer,Integer> temp = new HashMap<>();
		
		for(int i=0;i<n;i++) {
			// 0이 나오면 이후로는 볼 필요가 없음
			if(map[i][j]==0) continue;
			// 숫자의 등장횟수 계산
			temp.compute(map[i][j], (num,cnt)->cnt==null?1:cnt+1);
		}

		// 숫자와 등장 횟수를 우선순위 큐에 넣는다
		for(int key : temp.keySet()) {
			pq.add(new Pair(key,temp.get(key)));
		}

		// 우선순위 큐로 정렬된 숫자들을 배열에 넣어준다
		int i = 0;
		while(!pq.isEmpty()) {
			map[i++][j] = pq.peek().num;
			map[i++][j] = pq.peek().cnt;
			pq.poll();
		}

		// 최대 행의 개수를 갱신한다
		n = Math.max(n, i);

		// 배열의 남은 공간에 0을 넣어준다
		for(;i<100;i++) {
			map[i][j] = 0;
		}
	}
}

class Pair implements Comparable<Pair>{
	int num;
	int cnt;
	Pair(int n, int c){
		num=n;
		cnt=c;
	}
	@Override
	public int compareTo(Pair o) {
		if(this.cnt==o.cnt) return this.num-o.num;
		return this.cnt-o.cnt;
	}
}