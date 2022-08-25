import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int k, n;
	static int[] input;
	static ArrayList<ArrayList<Integer>> tree;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		k = Integer.parseInt(br.readLine());
		n = (int)Math.pow(2, k)-1;
		input = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		tree = new ArrayList<>();
		for(int i=0;i<k;i++) {
			tree.add(new ArrayList<Integer>());
		}
		
		func(0,n,0);
		
		for(int i=0;i<k;i++) {
			for(int j:tree.get(i)) {
				sb.append(j).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void func(int l, int r, int level) {
		if(level==k) {
			return;
		}
		
		int root = (l+r)/2;
		tree.get(level).add(input[root]);
		
		func(l,root-1,level+1);
		func(root+1,r,level+1);
	}
}
