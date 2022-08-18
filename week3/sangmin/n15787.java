import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] trains;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    
	    trains = new int[n+1];
	    
	    for(int t=0;t<m;t++) {
	    	st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken());
	    	int i = Integer.parseInt(st.nextToken());
	    	int x;
	    	
	    	int train = trains[i];
	    	switch (a) {
			case 1:
				x = Integer.parseInt(st.nextToken());
				train = train | (1<<x);
				break;
			case 2:
				x = Integer.parseInt(st.nextToken());
				train = train & ~(1<<x);
				break;
			case 3:
				train = train<<1 & ((1<<21) - 1);
				break;
			case 4:
				train = train>>1 & ~1;
				break;
			}
	    	trains[i] = train;
	    }
	    
	    Set<Integer> arr = new HashSet<>();
	    
	    for(int i=1;i<=n;i++) {
	    	arr.add(trains[i]);
	    }
	    
	    System.out.println(arr.size());
	}
}
