import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine(), ".");
        	String t = st.nextToken(); //파일 이름 처리용
        	String x = st.nextToken(); //확장자
        	
        	m.put(x, m.getOrDefault(x, 0) + 1);
        }
        List<String> keySet = new ArrayList<>(m.keySet());
		Collections.sort(keySet);
       
        for (int i = 0; i < keySet.size(); i++) 
    	   sb.append(keySet.get(i) + " " + m.get(keySet.get(i)) + "\n");

        bw.write(sb.toString());
        bw.flush(); bw.close(); br.close();
	}
}