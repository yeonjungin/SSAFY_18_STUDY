import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

        int m = s.nextInt();

        int[] a = new int[14];
        for (int i = 0; i < 14; i++)
            a[i] = s.nextInt();

        int mj = m, ms = m, t = a[0], sell = 0, buy = 0, tj = 0, ts = 0;
        for(int i = 0; i < 14; i++) {
        	//준현
        	if (mj >= a[i]) {
        		int j = 1;
        		while(true) {
        			if (mj >= a[i] * j) j++;
        			else break;
        		}
        		mj -= a[i] * j;
        		tj += j;
        	}
        	
        	//성민
        	if (t < a[i]) {
        		sell++; buy = 0;
        	}
        	else if(t > a[i]){
        		buy++; sell = 0;
        	}
        	
        	if (t != a[i] && buy >= 3) {
        		int j = 1;
        		while(true) {
        			if (ms >= a[i] * j) j++;
        			else break;
        		}
        		ms -= a[i] * --j;
        		ts += j;
        	}
        	
        	if (ts > 0 && sell == 3) {
        		ms += ts * a[i];
        		ts = 0;
            }
        	t = a[i];
        }
        ms += ts * a[13];
        mj += tj * a[13];

        if (ms == mj) System.out.println("SAMESAME");
        if (ms > mj) System.out.println("TIMING");
        if (ms < mj) System.out.println("BNP");

	}
}