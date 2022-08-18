import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int n, cnt, minDiff;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] map, num;
	static boolean[] isA;
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	    
	    StringTokenizer st; 	    
	  
	    n = Integer.parseInt(br.readLine());
	    
	    graph = new ArrayList<>();
	    map = new int[n+1];
	    isA = new boolean[n+1];
	    
	    num = new int[n+1];
	    st = new StringTokenizer(br.readLine());
	    for(int i=1;i<=n;i++) {
	    	num[i] = Integer.parseInt(st.nextToken());
	    }	    

	    graph.add(new ArrayList<>());
	    for(int i=1;i<=n;i++) {
	    	graph.add(new ArrayList<>());
		    st = new StringTokenizer(br.readLine());
		    int l = Integer.parseInt(st.nextToken());
		    for(int j=0;j<l;j++) {
		    	graph.get(i).add(Integer.parseInt(st.nextToken()));
		    }	    	
	    }
	    
	    minDiff = Integer.MAX_VALUE;
	    subSet(1);
	    System.out.println(minDiff==Integer.MAX_VALUE ? -1 : minDiff);
	}
	
	public static void subSet(int now) {
		if(now>n) {
			if(!isConnected()) return;
			int sumA = 0, sumB = 0;
			for(int i=1;i<=n;i++) {
				if(isA[i]) sumA += num[i];
				else sumB += num[i];
			}
			minDiff = Math.abs(sumA-sumB) < minDiff ? Math.abs(sumA-sumB) : minDiff;
			return;
		}
		
		isA[now] = true;
		subSet(now+1);
		
		isA[now] = false;
		subSet(now+1);
	}
	
	public static boolean isConnected() {
		// map 1이면 A, -1이면 B
		for(int i=1;i<=n;i++) {
			map[i] = isA[i] ? 1 : -1;
		}

		int cntA = 0, cntB = 0;
		int maxCnt = 0;
		for(int i=1;i<=n;i++) {
			cnt = 0;
			if(map[i]==1) {
				cnt++;
				dfs(i,1);
				cntA++;
			}
			else if(map[i]==-1) {
				cnt++;
				dfs(i,-1);
				cntB++;
			}
			maxCnt = cnt>maxCnt ? cnt : maxCnt;
		}	
		
		cnt = maxCnt;
		return (cntA==1 && cntB==1) ? true : false;
	}
	
	public static void dfs(int x, int num) {
		map[x] = 0;
		
		for(int i=0;i<graph.get(x).size();i++) {
			int nx = graph.get(x).get(i);
			
			if(map[nx]==num) {
				cnt++;
				dfs(nx,num);
			}			
		}
	}
}