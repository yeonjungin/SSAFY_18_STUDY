package study._220818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B20207 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int result = 0;
		int N = Integer.parseInt(br.readLine());
		
		int[] days = new int[367];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			for (int j = s; j <= e; j++) {
				days[j] += 1;
			}
		}
		
		int count = 0;
		int max = 0;
		for (int i = 1; i < days.length; i++) {
			if (days[i] == 0) {
				result += count * max;
				count = 0;
				max = 0;
			}
			else {
				count++;
				max = max < days[i] ? days[i] : max;
			}
		}
		
		System.out.println(result);
	}
}
