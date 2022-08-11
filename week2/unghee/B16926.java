package study._220811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16926 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int T = Math.min(N, M) / 2;
		
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int row;
		int col;
		int temp_b;
		int temp_a = 0;
		for (int i = 0; i < T; i++) {
			row = i;
			col = i;
			for (int j = 0; j < R; j++) {
				temp_b = arr[row][col];
				
				for (int l = i; l < N - i - 1; l++) {
					row++;
					temp_a = arr[row][col];
					arr[row][col] = temp_b;
					temp_b = temp_a;
				}
				
				for (int l = i; l < M - i - 1; l++) {
					col++;
					temp_a = arr[row][col];
					arr[row][col] = temp_b;
					temp_b = temp_a;
				}
				
				for (int l = i; l < N - i - 1; l++) {
					row--;
					temp_a = arr[row][col];
					arr[row][col] = temp_b;
					temp_b = temp_a;
				}
				
				for (int l = i; l < M - i - 1; l++) {
					col--;
					temp_a = arr[row][col];
					arr[row][col] = temp_b;
					temp_b = temp_a;
				}
			}
		}
		
		for (int[] is : arr) {
			for (int a : is) {
				sb.append(a).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}

