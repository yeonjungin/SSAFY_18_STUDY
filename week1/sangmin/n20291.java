import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 파일의 개수
		int n = Integer.parseInt(br.readLine());

		// 자동 정렬이 되는 TreeMap 생성 <확장자, 파일 개수>
		TreeMap<String,Integer> map = new TreeMap<>();
		
		for(int i=0;i<n;i++) {
			// 줄마다 입력을 받아와서
			st = new StringTokenizer(br.readLine(),".");
			// 파일의 이름을 날리고
			st.nextToken();
			// 확장자를 key값으로 저장
			String key = st.nextToken();
			// map에 확장자가 있다면 개수를 추가
			if(map.containsKey(key))
				map.put(key,map.get(key)+1);
			// 없다면 확장자를 추가
			else 
				map.put(key, 1);
		}
		
		for(String key : map.keySet()) {
			sb.append(key).append(" ").append(map.get(key)).append("\n");
		}
		System.out.println(sb.toString());
		
		br.close();
	}
}