package day_0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n20207 {
	static int[] cal;
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(br.readLine()); // 일정의 개수
		cal=new int[366];
		
		// 시작일, 종료일 입력받기 (일정)
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			for (int j = start; j <=end; j++) {
				cal[j]+=1; // 코팅지 높이 저장
			}
		}
		
		int answer=0; // 코팅지 최종 면적
		int width=0; // 코팅지 가로 길이
		int height=0; // 코팅지 세로 길이
		
		for (int i = 1; i < cal.length; i++) {
			if(cal[i]==0) {
				if (width>0) {
					answer+=width*height;
				}
				width=0; height=0;
			}else {
				width+=1;
				height=Math.max(height, cal[i]);
			}
		}
		if(width>0) answer+=width*height;
		System.out.print(answer);
	}
}

/*


*/