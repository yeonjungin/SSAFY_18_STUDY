package study._220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1342_2 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int result = 0;
		
		st = new StringTokenizer(br.readLine());
		char[] s = st.nextToken().toCharArray();
		
		Arrays.sort(s);
		
		do {
			if (isLuck(s)) ++result;
		} while (np(s));
		
		System.out.println(result);
	}

	private static boolean isLuck(char[] s) {
		boolean isluck = true;
		for (int i = 1; i < s.length; i++) {
			if (s[i-1] == s[i]) {
				isluck = false;
				break;
			}
		}
		return isluck;
	}

	private static boolean np(char[] s) {
		int N = s.length;
		
		int i = N - 1;
		while (i > 0 && s[i-1] >= s[i]) i--;
		
		if (i == 0) return false;
		
		int j = N - 1;
		while (s[i-1] >= s[j]) j--;
		
		swap(s, i-1, j);
		
		int k = N - 1;
		while (i < k) swap(s, i++, k--);
		
		return true;
	}

	private static void swap(char[] s, int i, int j) {
		char temp = s[i];
		s[i] = s[j];
		s[j] = temp;
	}

}


