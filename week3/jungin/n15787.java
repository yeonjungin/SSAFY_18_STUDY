import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static boolean[][] trainArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken()); // 기차의 개수
		M=Integer.parseInt(st.nextToken()); // 명령의 개수
		trainArr=new boolean[N+1][21]; // 1~20번 좌석
		
		for (int i = 0; i < M; i++) {
			// 명령 번호, 기차 번호, 좌석 번호
			st=new StringTokenizer(br.readLine());
			int mNum=Integer.parseInt(st.nextToken());
			int trainNum=Integer.parseInt(st.nextToken());
			
			if (mNum==1) { // i번째 기차, x번째 좌석에 사람 태우기 (이미 사람 있으면 x)
				int seatNum=Integer.parseInt(st.nextToken());
				trainArr[trainNum][seatNum]=true;
				
			}else if(mNum==2) { // i번째 기차, x번째 좌석에 앉은 사람 하차 (사람이 없으면 x)
				int seatNum=Integer.parseInt(st.nextToken());
				trainArr[trainNum][seatNum]=false;
				
			}else if(mNum==3) { // k번째 앉은 사람 -> k+1번째로 고객 모두 이동
				for (int j = 20; j >=2 ; j--) {
					trainArr[trainNum][j]=trainArr[trainNum][j-1];
				}
				trainArr[trainNum][1]=false;
			}else if(mNum==4){ // k번째 앉은 사람 -> k-1번째로 고객 모두 이동
				for (int j = 1; j <20; j++) {
					trainArr[trainNum][j]=trainArr[trainNum][j+1];
				}
				trainArr[trainNum][20]=false;
			}
		}
		Set<String> set = new HashSet<>();
		
		for (int i = 1; i <= N; i++) {
			String temp="";
			for (int j = 1; j <= 20; j++) {
				if(trainArr[i][j]) temp+="1";
				else temp+="0";
			}
			set.add(temp);
		}
		System.out.println(set.size());
	}
}