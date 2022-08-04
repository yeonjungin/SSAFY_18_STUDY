import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 준현(JH)의 보유 현금
	static int JH; 
	// 준현(JH)의 보유 주식 수
	static int JHCnt; 
	// 성민(SM)의 보유 현금
	static int SM;
	// 성민(SM)의 보유 주식 수
	static int SMCnt;
	// 당일 주가
	static int stock;
	// 전날 주가
	static int preStock;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 주어진 현금 입력
		int n = Integer.parseInt(br.readLine());

		// 준현(JH), 성민(SM)의 보유 현금 초기화
		JH = n;
		SM = n;
		// 준현(JHCnt), 성민(SMCnt)의 보유 주식 수 초기화
		JHCnt = 0;
		SMCnt = 0;

		// 1일부터 14일까지의 주가들을 공백으로 구분하여 st에 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/**
		 *  1일차
		 *  성민은 1일차에 아무것도 할 수 없기 때문에
		 *  준현만 주식을 거래한다
		 */
		
		// 당일 주가
		stock = Integer.parseInt(st.nextToken()); 
		// 전날 주가
		preStock = stock;		
		// 준현의 주식 구매
		jhStock();
		
		
		// 2일차 ~ 14일차		
		for(int i=1;i<14;i++) {
			// 당일 주가
			stock = Integer.parseInt(st.nextToken());	
			
			// 준현의 주식 구매
			jhStock();
			
			// 성민의 주식 구매
			smStock();
		}
		
		// 14일 준현과 성민의 보유 자산 계산
		JH += JHCnt * stock;
		SM += SMCnt * stock;

		// 준현이 이겼을 경우
		if(JH>SM) {
			System.out.println("BNP");
		}
		// 성민이 이겼을 경우
		else if(JH<SM) {
			System.out.println("TIMING");
		}
		// 무승부일 경우
		else {
			System.out.println("SAMESAME");
		}
	}
	
	public static void jhStock() {
		// 준현의 보유 현금으로 당일 주가를 구매
		JHCnt += JH / stock;
		// 주가 구매로 준현의 보유 현금 갱신
		JH %= stock;
	}
	
	public static void smStock() {
		// 가격이 연속으로 상승하거나 하락한 일 수
		// 상승하면 +값, 하락하면 -값
		// 절대값이 연속된 일 수이다
		int cnt = 0;
		// 현재 가격이 상승하고 있으면 true, 하락하고 있으면 false
		boolean isUp = true;
		
		// 가격이 상승하고 있었는데 오늘 가격이 전날에 비해 상승한 경우
		if(isUp && stock>preStock) {
			// 가격이 연속으로 상승한 일 수를 증가시킨다 
			cnt++;
		}
		// 가격이 하락하고 있었는데 오늘 가격이 전날에 비해 상승한 경우
		else if(!isUp && stock>preStock) {
			// 가격이 상승하고 있는 것으로 갱신하고
			isUp = true;
			// 가격이 연속으로 상승한 일 수를 1로 초기화한다
			cnt = 1;
		}
		// 가격이 상승하고 있었는데 오늘 가격이 전날에 비해 하락한 경우
		else if(isUp && stock<preStock) {
			// 가격이 하락하고 있는 것으로 갱신하고
			isUp = false;
			// 가격이 연속으로 하락한 일 수를 -1로 초기화한다
			cnt = -1;
		}
		// 가격이 하락하고 있었는데 오늘 가격이 전날에 비해 하락한 경우
		else if(!isUp && stock<preStock) {
			// 가격이 연속으로 하락한 일 수를 증가시킨다 (하락이므로 -1을 해줘야 일 수가 증가한 것이다)
			cnt--;
		}
		
		// 전날 주가 갱신
		preStock = stock;
		
		// 3일 이상 연속으로 주가가 상승했다면
		if(cnt>=3 && isUp) {
			// 성민의 보유 주가를 전량 매도
			SM += SMCnt * stock;
			// 주가 매도로 성민의 보유 주식 수 갱신
			SMCnt = 0;
		}
		else if(cnt<=-3 && !isUp) {
			// 성민의 보유 현금으로 당일 주가를 구매
			SMCnt += SM / stock;
			// 주가 구매로 성민의 보유 현금 갱신
			SM %= stock;
		}
	}
}
