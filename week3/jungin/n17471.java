package day_0813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class n17471 {
	static int N; // 구역의 개수
	static int[] people; // 구역의 인구수
	static List<List<Integer>> map; // 인접 구역의 정보 (2차원 리스트)
	static boolean[] selected;
	static int answer;
	
	
	public static boolean isConnect(int size, ArrayList<Integer> array) {
		boolean[] visited=new boolean[N+1]; // A팀 방문구역
		int allCnt=1; 
		Queue<Integer> queue= new LinkedList<>();
		queue.add(array.get(0));
		visited[array.get(0)]=true;
		
		while(!queue.isEmpty()) {
			int start= queue.poll(); // 시작 노드
			for (int i : map.get(start)) {
				if (!visited[i] && array.contains(i)) {
					queue.add(i);
					allCnt++;
					visited[i]=true;
				}
			}
		}
		if (size==allCnt) return true;
		return false;
		
	}
	
	public static void comb(int cnt, int start) {
		if (cnt==0) {
			ArrayList<Integer> teamA=new ArrayList<>();
			ArrayList<Integer> teamB=new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				if (selected[i]) teamA.add(i);
				else teamB.add(i);
			}
			// 2. A팀, B팀이 각각 연결 되어 있는지 확인
			if (isConnect(teamA.size(),teamA) && isConnect(teamB.size(), teamB)) {				
				// 3. 연결 되어 있다면 인구 차이 계산
				int valA=0, valB=0;
				for (int a : teamA) {
					valA+=people[a];
				}
				for (int b : teamB) {
					valB+=people[b];
				}
				answer=Math.min(answer, Math.abs(valA-valB));
			}
			return;
		}
		for (int i = start; i <= N; i++) {
			selected[i]=true;
			comb(cnt-1,i+1);
			selected[i]=false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine()); // 구역의 개수
		people=new int[N+1]; // 구역의 인구수
		map=new ArrayList<>(); 
		answer=Integer.MAX_VALUE;
		selected=new boolean[N+1];
		
		// 맵 크기 초기화
		for (int i = 0; i <=N; i++) {
			map.add(new ArrayList<>());
		}
		
		// 인구 정보 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int j = 1; j <= N; j++) {
			people[j]=Integer.parseInt(st.nextToken());
		}
		// 인접한 구역 정보 입력 받기
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken()); 
			for (int j = 0; j < size; j++) {
				map.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 1. 조합으로 구역 나누기
		for (int i = 1; i < N; i++) {
			comb(i,1); // cnt, start
		}
		
		if(answer==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(answer);
		}
	}

}