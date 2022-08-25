package study._220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B13908 {
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		HashSet<Integer> num = new HashSet<>();
		for (int i = 0; i < M; i++) {
			num.add(Integer.parseInt(st.nextToken()));
		}
		
		per(N, 0, new Integer[N], num);
		
		System.out.println(result);
	}
	
	private static void per(int N, int count, Integer[] pass, HashSet<Integer> num) {
		if (count == N) {
			HashSet<Integer> p = new HashSet<>(Arrays.asList(pass));
			if (p.containsAll(num)) result++;
			return;
		}
		
		for (int i = 0; i <= 9; i++) {
			pass[count] = i;
			per(N, count+1, pass, num);
		}
	}
}
