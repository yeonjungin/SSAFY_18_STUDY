import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
	    int n = Integer.parseInt(br.readLine());
	    
	    int[] days = new int[367];
	    
	    int maxDay = 0;
	    
	    for(int i=0;i<n;i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken());
	    	int b = Integer.parseInt(st.nextToken());
	    	
	    	for(int j=a;j<=b;j++) {
	    		days[j]++;
	    	}
	    	maxDay = b>maxDay ? b : maxDay;
	    }
	    
	    maxDay++;
	    
	    int sum=0,w=0,h=0;
	    boolean isContinue = false;
	    for(int i=1;i<=maxDay;i++) {
	    	if(days[i]==0) {
	    		if(!isContinue) {
	    			continue;
	    		}
	    		// 연속된 일정의 끝
    			sum += w * h;
    			isContinue = false;	    		
	    	}
	    	if(days[i]>0) {
	    		if(isContinue) {
	    			w++;
	    			h = h<days[i] ? days[i] : h;
	    		}
	    		else { // 일정의 시작
	    			w = 1;
	    			h = 1;
	    			isContinue = true;
	    		}
	    	}
	    }
	    
	    System.out.println(sum);
	}
}
