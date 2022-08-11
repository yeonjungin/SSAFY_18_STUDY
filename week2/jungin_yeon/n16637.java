import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<Integer> numArray;
	static List<Character> operatorArray;
	static int answer;
	
	public static int calculate(int a, int b, char op) { // 계산하기
		if(op=='+') return a+b;
		else if(op=='-') return a-b;
		else return a*b;
	}
	
	public static void dfs(int opidx, int sum) {
		if(opidx==operatorArray.size()) { // 주어진 연산자의 개수를 초과했을 경우
			answer=Math.max(answer, sum);
			return;
		}
		// 괄호가 없는 경우
		int result1=calculate(sum, numArray.get(opidx+1), operatorArray.get(opidx));
		dfs(opidx+1, result1);
		
		// 괄호가 있는 경우
		if (opidx+1<operatorArray.size()) {
			int result2=calculate(numArray.get(opidx+1),numArray.get(opidx+2) , operatorArray.get(opidx+1));
			dfs(opidx+2,calculate(sum, result2, operatorArray.get(opidx)));
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		numArray=new ArrayList<>();
		operatorArray=new ArrayList<>();
		String input=br.readLine();
		
		for(int i=0; i<n; i++) {
			char c = input.charAt(i);
			if (c=='+' || c=='-' || c=='*') {
				operatorArray.add(c);
			}else {
				numArray.add(Character.getNumericValue(c)); // char -> int
			}
		}
		answer=Integer.MIN_VALUE;
		dfs(0,numArray.get(0));
		System.out.println(answer);
	}

}