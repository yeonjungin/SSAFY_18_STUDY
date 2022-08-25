import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int n, cnt;
	static char[] temp;
	static int[] alpabet;
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    String input = br.readLine();
	    
	    n = input.length();
	    temp = new char[n];
	    alpabet = new int['z'-'a'+1];

	    for(int i=0;i<n;i++) {
	    	alpabet[input.charAt(i)-'a']++;
	    }
	    cnt = 0;
	    permutation(0);
	    System.out.println(cnt);
	}
	
	static void permutation(int time) {
		if(time>1 && temp[time-2]==temp[time-1]) {
			return;
		}

		if(time==n) {
			cnt++;
			return;
		}
		

		for(int i=0,j='z'-'a'+1;i<j;i++) {
			if(alpabet[i]>0) {
				alpabet[i]--;
				temp[time] = (char)('a'+i);
				permutation(time+1);
				alpabet[i]++;
			}

		}
	}
}