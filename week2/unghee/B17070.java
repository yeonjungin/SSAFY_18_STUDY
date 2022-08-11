package study._220811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B17070 {
	private static int[][] home;
	private static int N;
	private static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		home = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		movePipe(new int[] {0, 0}, new int[] {0, 1});
		
		System.out.println(count);
	}
	
	private static void movePipe(int[] pipe_l, int[] pipe_r) {
		if (pipe_r[0] == N - 1 && pipe_r[1] == N - 1) {
			count++;
			return;
		}
		
		int[] d = {pipe_r[0] - pipe_l[0], pipe_r[1] - pipe_l[1]};
		
		boolean[] v = isValid(pipe_r);
		
		//현재 파이프의 방향이 가로일 때
		if (d[0] == 0 && d[1] == 1) {
			if (v[0] && home[pipe_r[0]][pipe_r[1] + 1] == 0) {
				movePipe(new int[] {pipe_l[0], pipe_l[1] + 1}, new int[] {pipe_r[0], pipe_r[1] + 1});
			}
			
			if (v[0] && v[1] && v[2]) {
				if (home[pipe_r[0]][pipe_r[1] + 1] == 0 && 
						home[pipe_r[0] + 1][pipe_r[1] + 1] == 0 && 
						home[pipe_r[0] + 1][pipe_r[1]] == 0) {
					movePipe(new int[] {pipe_l[0], pipe_l[1] + 1}, new int[] {pipe_r[0] + 1, pipe_r[1] + 1});
				}
			}
				
		}
		//현재 파이프의 방향이 세로일 때
		else if (d[0] == 1 && d[1] == 0) {
			if (v[1] && home[pipe_r[0] + 1][pipe_r[1]] == 0) {
				movePipe(new int[] {pipe_l[0] + 1, pipe_l[1]}, new int[] {pipe_r[0] + 1, pipe_r[1]});
			}
			
			if (v[0] && v[1] && v[2] && 
					home[pipe_r[0]][pipe_r[1] + 1] == 0 && 
					home[pipe_r[0] + 1][pipe_r[1] + 1] == 0 && 
					home[pipe_r[0] + 1][pipe_r[1]] == 0) {
				movePipe(new int[] {pipe_l[0] + 1, pipe_l[1]}, new int[] {pipe_r[0] + 1, pipe_r[1] + 1});
			}
		}
		//현재 파이프의 방향이 대각선일 때
		else {
			if (v[0] && home[pipe_r[0]][pipe_r[1] + 1] == 0) {
				movePipe(new int[] {pipe_l[0] + 1, pipe_l[1] + 1}, new int[] {pipe_r[0], pipe_r[1] + 1});
			}
			
			if (v[1] && home[pipe_r[0] + 1][pipe_r[1]] == 0) {
				movePipe(new int[] {pipe_l[0] + 1, pipe_l[1] + 1}, new int[] {pipe_r[0] + 1, pipe_r[1]});
			}
			
			if (v[0] && v[1] && v[2] && 
					home[pipe_r[0]][pipe_r[1] + 1] == 0 && 
					home[pipe_r[0] + 1][pipe_r[1] + 1] == 0 && 
					home[pipe_r[0] + 1][pipe_r[1]] == 0) {
				movePipe(new int[] {pipe_l[0] + 1, pipe_l[1] + 1}, new int[] {pipe_r[0] + 1, pipe_r[1] + 1});
			}
		}
	}
	
	//다음 움직일 수 있는 영역이 범위 내인지 확인하는 함수
	private static boolean[] isValid(int[] pipe_r) {
		boolean[] v = new boolean[3];
		int[] row = {0, 1, 1};
		int[] col = {1, 0, 1};
		int n_row;
		int n_col;
		for (int i = 0; i < 3; i++) {
			n_row = pipe_r[0] + row[i];
			n_col = pipe_r[1] + col[i];
			if (n_row < N && n_col < N) {
				v[i] = true;
			}
		}
		return v;
	}

}
