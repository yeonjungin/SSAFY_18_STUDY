package study._220811;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 조합을 이용하여 나올 수 있는 모든 연산자의 경우의 수를 구하고, 구한 경우의 수에 따른 계산 결과 중 최대값을 출력 
 */
public class B16637 {
	
	private static LinkedList<Character> ope;
	private static LinkedList<Integer> num;
	private static int result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		String s = sc.next();
		
		//연산자와 숫자들을 각각 리스트에 저장
		ope = new LinkedList<>();
		num = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '*' || s.charAt(i) == '+' || s.charAt(i) == '-') {
				ope.add(s.charAt(i));
			}
			else {
				num.add(s.charAt(i) - '0');
			}
		}
		
		//괄호가 하나도 없을때 계산한 결과값을 result변수에 저장
		result = calAll(new LinkedList<Character>(ope), new LinkedList<Integer>(num));
		
		//최대로 나올 수 있는 괄호 개수
		n = (ope.size() + 1) / 2;
		
		//괄호 개수 1 ~ n까지 조합 구하고 계산하기
		for (int i = 1; i <= n; i++) {
			int[] numbers = new int[i];
			comb(numbers, 0, ope.size(), 0, i);
		}	
		
		System.out.println(result);
	}
		
	// 하나의 부분, 괄호 씌운 부분만을 계산하고 다시 리스트에 값을 넣어주는 함수
	private static void cal(LinkedList<Character> ope, LinkedList<Integer> num, int i) {
		char operation = ope.remove(i);
		if (operation == '+') {
			num.add(i, num.remove(i) + num.remove(i));
		}
		else if (operation == '*') {
			num.add(i, num.remove(i) * num.remove(i));
		}
		else {
			num.add(i, num.remove(i) - num.remove(i));
		}
	}
	
	// 먼저 괄호를 씌워 계산하고 남은 연산자와 숫자들을 계산하여 결과값을 내는 함수 
	private static int calAll(LinkedList<Character> ope, LinkedList<Integer> num) {
		while (!ope.isEmpty()) {
			char operation = ope.poll();
			
			if (operation == '+') {
				num.addFirst(num.poll() + num.poll());
			}
			else if (operation == '*') {
				num.addFirst(num.poll() * num.poll());
			}
			else {
				num.addFirst(num.poll() - num.poll());
			}
		}
		return num.poll();
	}
	
	//연산자의 조합을 구하는 함수
	private static void comb(int[] numbers, int start, int N, int count, int ob) {
		//목표 괄호 개수에 도달하면
		if (count == ob) {
			//연산자 리스트, 숫자 리스트 가져옴
			LinkedList<Character> n_ope = new LinkedList<Character>(ope);
			LinkedList<Integer> n_num = new LinkedList<Integer>(num);
			
			//괄호 개수만큼 계산
			for (int i = numbers.length - 1; i >= 0 ; i--) {
				cal(n_ope, n_num, numbers[i]);
			}
			
			//남은 연산자와 숫자 이용하여 계산
			int temp = calAll(n_ope, n_num);
			
			//계산값이 더 크면 결과값과 교환
			result = temp > result ? temp : result;
			return;
		}
		
		//재귀 함수 호출
		for (int i = start; i < N; i++) {
			//괄호로 쓸 연산자 저장
			numbers[count] = i;
			//연속 되는 연산자가 오지 않도록 +2 해줌
			comb(numbers, i + 2, N, count + 1, ob);
		}
	}

}

