package study._220818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class B1283 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] keyIndex = new int[2];
		HashSet<Character> keys = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			String original = br.readLine();
			String[] option = original.split(" ");
			
			boolean iskey = false;
			
			for (int j = 0; j < option.length; j++) {
				char first = option[j].charAt(0);
				
				if (!keys.contains(Character.toUpperCase(first))) {
					keys.add(Character.toUpperCase(first));
					iskey = true;
					keyIndex[0] = j;
					keyIndex[1] = 0;
					break;
				}
			}
			
			if (!iskey) {
				for (int j = 0; j < option.length; j++) {
					if (iskey) {
						break;
					}
					for (int j2 = 0; j2 < option[j].length(); j2++) {
						char next = option[j].charAt(j2);
						
						if (!keys.contains(Character.toUpperCase(next))) {
							keys.add(Character.toUpperCase(next));
							iskey = true;
							keyIndex[0] = j;
							keyIndex[1] = j2;
							break;
						}
					}
				}
			}
			
			if (iskey) {
				for (int j = 0; j < option.length; j++) {
					for (int j2 = 0; j2 < option[j].length(); j2++) {
						if (keyIndex[0] == j && keyIndex[1] == j2) {
							sb.append("[").append(option[j].charAt(j2)).append("]");
						}
						else sb.append(option[j].charAt(j2));
					}
					sb.append(" ");
				}
			}
			else sb.append(original);
			
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
