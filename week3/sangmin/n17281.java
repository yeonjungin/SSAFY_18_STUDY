import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 이닝 수
	static int N;
	// 현재 타자 번호
	static int nowIndex;
	// 최대 점수
	static int maxScore = 0;
	// 선수 수
	static final int NUMBERS = 9;
	// 선수 타순
	static int[] seqPlayer;
	// 선수 타순 순열 생성시 확인하는 배열
	static boolean[] isSelected;
	// 선수들의 이닝 별 결과 (행_y:이닝, 열_x:선수)
	static int[][] resPlayer;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		seqPlayer = new int[NUMBERS];
		isSelected = new boolean[NUMBERS+1];
		resPlayer = new int[N+1][NUMBERS+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=NUMBERS;j++) {
				resPlayer[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		br.close();
		
		// 1번 선수를 4번 타자로 선정
		seqPlayer[3] = 1;
		isSelected[1] = true;
		permutation(0);
		
		System.out.println(maxScore);
	}
	
	// 선수 타순을 순열을 통해 정하고
	// 정했다면 야구를 진행
	public static void permutation(int cnt) {
		// 4번 타자는 이미 1번 선수로 정해졌으므로 다음 순열 진행
		if(cnt==3) {
			permutation(cnt+1);
			return;
		}
		// 모든 선수의 타순이 정해졌다면
		else if(cnt==NUMBERS) {
			// 게임을 시작한다
			playGame();
			return;
		}
		
		for(int i=1;i<=NUMBERS;i++) {
			if(!isSelected[i]) {
				seqPlayer[cnt] = i;
				isSelected[i] = true;
				permutation(cnt+1);
				isSelected[i] = false;
			}
		}
	}
	
	public static void playGame() {
		// 첫 타자 번호
		nowIndex = 0;
		// 획득 점수
		int score = 0;
		
		// N 이닝동안 게임 진행
		for(int i=1;i<=N;i++) {
			// 각각의 이닝마다 획득한 점수를 더한다
			score += innings(i);
		}

		// 최대 점수를 갱신
		maxScore = Math.max(maxScore, score);		
	}
	
	public static int innings(int inning) { // 현재 이닝 수 : i
		// out 카운트 => 3이 되면 끝남
		int outCnt = 0;
		
		// 획득 점수
		int score = 0;
		
		/**
		 * 베이스 비트 초기화 
		 * -> 00000
		 * 각각의 비트는 선수의 유무를 나타냄
		 * 오른쪽 비트부터 0번째 비트, 1번째 비트, ... , 4번째 비트이다
		 * 0 : 출발 홈
		 * 1 : 1루
		 * 2 : 2루
		 * 3 : 3루
		 * 4 : 도착 홈
		 */
		int base = 0;
		
		// 도착 홈을 나타내는 비트
		// -> 10000
		int goal = 1<<4;
		
		while(outCnt<3) {			
			// 현재 타자의 번호
			int nowPlayer = seqPlayer[nowIndex];
			// 다음 타자 번호 갱신
			nowIndex = (nowIndex + 1) % NUMBERS;
			
			/**
			 *  현재 타자의 결과 
			 *  -> 0,1,2,3,4
			 *  0 : 아웃
			 *  1 : 안타
			 *  2 : 2루타
			 *  3 : 3루타
			 *  4 : 홈런
			 */
			int resState = resPlayer[inning][nowPlayer];
			
			// 아웃일 경우
			if(resState==0) {
				outCnt++;
			}
			// 아웃이 아닌 경우
			else {
				// 출발 홈에 선수를 넣고
				base = (base|1);
				// 안타 상황에 따라 1루씩 진루
				for(int i=0;i<resState;i++) {
					// 진루 한번 진행
					base = base<<1;
					// 홈에 도착한 인원이 있다면 점수 추가
					if((base&goal)>0) score++;
				}
			}
		}
		return score;
	}
}
