import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		char arr[] = s.toCharArray();
		char check[] = {'q', 'u', 'a', 'c', 'k'};
		boolean visit[] = new boolean[s.length()];
		int cnt = 0, num = arr.length;
		
		//arr를 순서대로 돌면서 visit에 방문체크
		while(true) {
			int f = 0, x = 0, j = 0;
			List<Integer> tmp =  new ArrayList<>();
			//한 바퀴를 돌면서 순서대로 q,u,a,c,k를 탐색
			for (int i = 0; i < arr.length; i++) {
				if (!visit[i] && arr[i] == check[x]) {
					tmp.add(i);
					//k까지 탐색했으면 다시 q로 돌아가서 탐색
					if (x == check.length - 1) {
						f = 1; x = 0; 
					}
					else x++;
				}
			}
			//한 바퀴에 오리는 한 마리
			if (f == 1) cnt++;
			
			//오리임이 성립된 경우에만 방문체크 해줌
			if (f == 1 && x == 0)
				for (int i = 0 ; i < tmp.size(); i++)
					visit[tmp.get(i)] = true;
			
			//몇 개를 방문했는지 체크
			f = 0;
			for (int i = 0; i < visit.length; i++)
				if (!visit[i]) f++;
			
			//방문한 값의 변화가 없다면 더이상 탐색이 불가하기 때문에 멈춤
			if (num == f) {
				if (f != 0) //더 탐색할 값이 남았는데 오리가 더 없는 경우
					cnt = 0;
				break;
			}
			num = f;
		}
		
		//값이 남아있다면 처리
		for (int i = 0; i < visit.length; i++)
			if (!visit[i]) cnt = 0;
		
		if (cnt == 0) cnt = -1;
		System.out.println(cnt);
	}
}