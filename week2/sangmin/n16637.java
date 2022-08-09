import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();
	// 수식의 길이 n, 결과의 최대값 maxRes
	static int n, maxRes;
	// 수식 배열
	static char[] expression;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		// n 입력
		n = Integer.parseInt(br.readLine());
		// 결과의 최대값 초기화
		maxRes = Integer.MIN_VALUE;
	
		// 수식 입력
		expression = br.readLine().toCharArray();
		
		// 괄호 추가 메서드
		parenthesis(2,expression[0]-'0');
		
		// 결과 출력
		System.out.println(maxRes);
		
		br.close();		
	}

	/**
	 * index : 현재 인덱스
	 * total : 이전까지 수식의 결과값
	 * 
	 * 1. 현재 숫자에 괄호를 추가하지 않고 수식을 계산한다		
	 * 2. 현재 숫자에 괄호를 넣어 수식을 계산한다
	 * 
	 * 수식에서 숫자 하나하나 탐색하며 생길 수 있는 경우의 수를 따진다
	 */
	public static void parenthesis(int index, int total) {
		if(index >= n ) {
			maxRes = (maxRes < total) ? total : maxRes;
			return;
		}
		
		// 1. 현재 숫자에 괄호를 추가하지 않고 수식을 계산한다		
		// 이전까지의 total값과 [현재 index]값을 연산해준다
		parenthesis(index+2, calc(total, expression[index-1], expression[index]-'0'));
		
		// 다음 숫자가 있을 경우
		// 2. 현재 숫자에 괄호를 넣어 수식을 계산한다
		if(index+2<n) {
			// [현재 index]값과 [다음 index]값을 괄호처리하여 연산한다
			int nextExp = calc(expression[index]-'0', expression[index+1], expression[index+2]-'0');
			
			// 이전까지의 total값과 괄호처리로 연산한 값을 연산한다
			parenthesis(index+4, calc(total, expression[index-1], nextExp));
		}		
	}
	
	// A op B 연산을 진행한다
	public static int calc(int A, char OP, int B) {
		if(OP=='+') 
			return A+B;		
		else if(OP=='-') 
			return A-B;		
		else 
			return A*B;		
	}
}