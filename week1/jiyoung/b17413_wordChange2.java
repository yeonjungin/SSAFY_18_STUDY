package week01;

import java.util.*;

public class b17413_wordChange2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		boolean pass = false;
		Stack<Character> stack = new Stack<>();
		char now = '\0';
		char next = '\0';

		for (int idx = 0; idx < s.length(); idx++) {
			now = s.charAt(idx);
			if (idx + 1 != s.length()) next = s.charAt(idx + 1);
			
			switch (s.charAt(idx)) {
			case '<' :
			case '>' :
			case ' ' :
				System.out.print(now);
				if (now == '<') pass = true;
				else if (now == '>' && idx + 1 != s.length() && next != '<') pass = false;
				break;

			default:
				if (idx + 1 == s.length() || ((next == '<' || next == ' ') && !pass)) {
					stack.push(now);
					print(stack);
				}
				else if (((next == '>' || next == ' ') && pass) || pass)
					System.out.print(now);
				else stack.push(now);
				break;
			}
		}
	}
	
	public static void print(Stack<Character> stack) {
		while (!stack.isEmpty()) {
			Character item = stack.pop();
			System.out.print(item);
		}
	}
}