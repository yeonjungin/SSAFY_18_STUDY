package study._220818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17471 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[] p = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] ways = new int[N][];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int wayN = Integer.parseInt(st.nextToken());
			int[] tem = new int[wayN];
			
			for (int j = 0; j < wayN; j++) {
				tem[j] = Integer.parseInt(st.nextToken());
			}
			ways[i] = tem;
		}
		
		
	}
		
}
