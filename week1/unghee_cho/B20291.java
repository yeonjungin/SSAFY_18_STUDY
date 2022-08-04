package study._220804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class B20291 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		TreeMap<String, Integer> count = new TreeMap<>();
		
		int N = Integer.parseInt(br.readLine()); 
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), ".");
			st.nextToken();
			String ex = st.nextToken();
			
			if (count.containsKey(ex)) {
				count.put(ex, count.get(ex) + 1);
			}
			else {
				count.put(ex, 1);
			}
		}
		
		for (String key : count.keySet()) {
			sb.append(key).append(" ").append(count.get(key)).append("\n");
		}
		System.out.println(sb.toString());
	}
}
