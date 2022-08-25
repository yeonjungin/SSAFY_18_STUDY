import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static StringBuilder sb = new StringBuilder();
	static int n, m, target, cnt;
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    st = new StringTokenizer(br.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    
	    if(m>0)
	    	st = new StringTokenizer(br.readLine());
	    target = 0;
	    for(int i=0;i<m;i++) {
	    	int num = Integer.parseInt(st.nextToken());
	    	target |= (1<<num);
	    }
	    // ex) target = 0110010010 이라면 
	    // => 1,4,7,8 이 활성화 되어 있으므로 이것을 사용해야 함
	    
	    cnt = 0;
	    permutation(0,0);
	    
	    System.out.println(cnt);
	}
	
	static void permutation(int time, int flag) {
		if(time==n) {
			if((flag&target) == target) {
				cnt++;
			}
			return;
		}
		
		for(int i=0;i<10;i++) {
			permutation(time+1, flag|(1<<i));
		}
	}
}