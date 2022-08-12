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
	
	public static int startGame() {
		int curCount=0; // 현재 스코어
		int playerCnt=0; // 현재 타석에 서있는 선수의 번호
		int outCount=0; // 아웃 카운트
		int[] state= {0,0,0,0}; // 현재 주자 상황
		
		// N 이닝을 반복한다.
		for (int i = 0; i < N; i++) {			
			// 3아웃이 되기전까지 아래 과정을 반복한다.
			while (outCount<3) {
				int pResult = player[i][playerCnt]; // 타석에 서있는 선수의 점수 (해당 이닝의)
				if (pResult ==0) outCount++; // 1. 아웃
				else if(pResult==1) { // 2. 안타 -> 타자와 각 주자들 1칸씩 이동
				}
				else if(pResult==2) { // 2. 2루타 -> 타자와 각 주자들 2칸씩 이동
				}
				else if(pResult==3) { // 3. 3루타 -> 타자와 각 주자들 3칸씩 이동
				}
				else if(pResult==4) { // 4. 홈런 -> 모든 주자 들어옴
				}
				
			}
		}
//		2.1 1번 선수~ 9번 선수순으로 타순에 들어선다. 
//		2.2 타석에 있는 선수가 낸 결과에 따라 state 배열을 업데이트 해준다. 
//		state = [1루,2루,3루,홈]에 기록한다.
	}
	
	public static void permu(int cnt) {
		if(cnt==P) {
			// 2. 순열로 정한 타순으로 게임을 진행한다.
			int temp=startGame();
			if(temp>maxCnt) temp=maxCnt;
			return;
		}
		for (int i = 0; i < P; i++) {
			if(visited[i]) continue;
			selected[cnt]=i;
			visited[i]=true;
			permu(cnt+1);
			selected[cnt]=0;
			visited[i]=false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine()); // 이닝 수 == 게임 수
		
		// 1.순열로 타석 순서 정하기
		permu(0); 
		
		// 각 선수별로 이닝 결과 입력받기
		player=new int[N][P];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j <P; j++) {
				player[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(maxCnt);
	}

}
/*
1. 모든 선수의 조합을 구한다. (순열)
2. 뽑은 선수 조합을 토대로 게임을 시작한다. == 득점 구하기
	(3아웃이 되기 전까지 아래 과정을 반복)
	2.1 1번 선수~ 9번 선수순으로 타순에 들어선다. 
	2.2 타석에 있는 선수가 낸 결과에 따라 state 배열을 업데이트 해준다. 
	state = [1루,2루,3루,홈]에 기록한다.
	
가장 많은 득점을 하는 타순, 그때의 득점을 출력하기

A팀(9명)  vs B팀(9명)
총 N이닝 동안 게임을 진행한다. ( 한 이닝은 공격과 수비로 이루어져있다.)
공격 			수비
수비			공격
...			...

규칙
1. 한 이닝에 3아웃 -> 종료 (이닝 교체 == 공격과 수비 교체)
2. 경기 시작 전, 타순을 정해야한다. (중간에 타순 변경 불가)
3. 9번 타자까지 공을 쳤는데 이닝이 끝나지 않으면 1번 타자가 다시 타석에 선다. 
(타순은 이닝이 변경되어도 순서는 유지되어야함) 
4. 1루 -> 2루 -> 3루 -> 홈 (1점+)
5. 안타(1루타), 2루타, 3루타, 홈런, 아웃 
	안타: *타자와 모든 주자*가 한 루씩 진루한다. -> 1
	2루타: *타자와 모든 주자*가 두 루씩 진루한다. -> 2
	3루타: *타자와 모든 주자*자가 세 루씩 진루한다. -> 3
	홈런: *타자와 모든 주자*가 홈까지 진루한다. -> 4
	아웃: 모든 주자는 진루하지 못하고, 공격 팀에 아웃이 하나 증가한다. -> 0

아인타팀
타순순서	1	2	3	4	5	6	7	8	9 
선수번호				1번
*/