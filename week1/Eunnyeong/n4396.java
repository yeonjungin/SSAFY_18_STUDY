import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        
        String x[] = new String[n];
        for (int i = 0; i < n; i++)
        	x[i] = br.readLine();
        
        String a[] = new String[n];
        for (int i = 0; i < n; i++)
        	a[i] = br.readLine();
        
        int f = 0;
        for (int i = 0; i < n; i++) 
        	for (int j = 0; j < n; j++) 
        		if (a[i].charAt(j) == 'x' && x[i].charAt(j) == '*')
        			f = 1;
        
        String ans[] = new String[n];
        int[] dx = {0, 1, -1, 0, 1, 1, -1, -1}, dy = {1, 0, 0, -1, 1, -1, -1, 1};
        for (int i = 0; i < n; i++) {
        	String str = "";
        	for (int j = 0; j < n; j++) {
        		if (a[i].charAt(j) == 'x' && x[i].charAt(j) != '*') {
        			int cnt = 0;
            		for (int k = 0; k < 8; k++) {
            			int nx = i + dx[k], ny = j + dy[k];
            			if (nx >= 0 && nx < n && ny >= 0 && ny < n && x[nx].charAt(ny) == '*')
            				cnt++;
            		}
            		str += (char)(cnt + '0');
        		}	
        		else {
        			if (f == 1 && x[i].charAt(j) == '*') str += '*';
        			else str += '.';
				}
        	}
        	ans[i] = str;
        }
        for (int i = 0; i < n; i++)
        	System.out.println(ans[i]);
	}
}