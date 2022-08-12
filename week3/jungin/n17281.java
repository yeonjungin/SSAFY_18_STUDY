package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n17281 {
	static int[] selected; // 순열로 뽑은 타석 순서
	static boolean[] visited; // 방문 체크
	static int P=9; // 플레이어 수 9명
	static int maxCnt=0; // 최종출력값
	static int[][] player; // 각 선수의 이닝 결과 저장배열
	static int N; // 이닝수
	
	public static void startGame() {
		int curCount=0; // 현재 스코어
		int playerCnt=0; // 현재 타석에 서있는 선수의 번호
		int outCount=0; // 아웃 카운트
		 
		// N 이닝을 반복한다.
		for (int i = 0; i < N; i++) {			
			int[] state= {0,0,0}; // 현재 주자 상황 (1,2,3루)
			outCount=0;
			// 3아웃이 되기전까지 아래 과정을 반복한다.
			while (outCount<3) {
				int pResult = player[i][selected[playerCnt]]; // 타석에 서있는 선수의 점수 (해당 이닝의)
				if (pResult==0) outCount++; // 1. 아웃
				else if(pResult==1) { // 2. 안타 -> 타자와 각 주자들 1칸씩 이동
					if(state[2]>0) { // 3루 -> 홈
						curCount++;
						state[2]=0;
					}
					else if(state[1]>0) { // 2루 -> 3루
						state[2]=1;
						state[1]=0;
					}
					else if(state[0]>0) { // 1루 -> 2루
						state[1]=1;
						state[0]=0;
					}
					// 타자 -> 1루
					state[0]=1;
				}
				else if(pResult==2) { // 2. 2루타 -> 타자와 각 주자들 2칸씩 이동
					if(state[2]>0) { // 3루 -> 홈
						curCount++;
						state[2]=0;
					}
					else if(state[1]>0) { // 2루 -> 홈
						curCount++;
						state[1]=0;
					}
					else if(state[0]>0) { // 1루 -> 3루
						state[2]=1;
						state[0]=0;
					}
					// 타자 -> 2루
					state[1]=1;
				}
				else if(pResult==3) { // 3. 3루타 -> 타자와 각 주자들 3칸씩 이동
					if(state[2]>0) { // 3루 -> 홈
						curCount++;
						state[2]=0;
					}
					else if(state[1]>0) { // 2루 -> 홈
						curCount++;
						state[1]=0;
					}
					else if(state[0]>0) { // 1루 -> 홈
						curCount++;
						state[0]=0;
					}
					// 타자 -> 3루
					state[2]=1;
				}
				else if(pResult==4) { // 4. 홈런 -> 모든 주자 들어옴
					if(state[2]>0) { // 3루 -> 홈
						curCount++;
						state[2]=0;
					}
					else if(state[1]>0) { // 2루 -> 홈
						curCount++;
						state[1]=0;
					}
					else if(state[0]>0) { // 1루 -> 홈
						curCount++;
						state[0]=0;
					}
					// 타자 -> 홈
					curCount++;
				}
				playerCnt=(playerCnt+1)%9;
			}
		}
		maxCnt=Math.max(maxCnt, curCount);
		
	}
	
	public static void permu(int cnt) {
		if(cnt==P) {
			// 3. 순열로 정한 타순으로 게임을 진행한다.
			startGame();
			return;
		}
		for (int i = 0; i < P; i++) {
			if(visited[i]) continue;	
			selected[i]=cnt; 
			visited[i]=true;
			permu(cnt+1);
			visited[i]=false;
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine()); // 이닝 수 == 게임 수
		
		// 1. 각 선수별로 이닝 결과 입력받기
		player=new int[N][P];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j <P; j++) {
				player[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		visited=new boolean[P];
		selected=new int[P];
		
		// 1번 선수 -> 4번 타자 (고정)
		visited[3]=true; 
		selected[3]=0;
		
		// 2.순열로 타석 순서 정하기
		permu(1); 
		
		System.out.println(maxCnt);
	}

}
