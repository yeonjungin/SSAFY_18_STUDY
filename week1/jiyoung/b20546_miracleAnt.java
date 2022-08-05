package week01;

import java.util.Scanner;

public class b20546_miracleAnt {

	public static void main(String[] args) {
		int money = 0;			// 입력받을 초기 현금
		int [] juga = new int[15];	// MachineDuck 주가 변동
		int day = 1;
		
		Scanner sc = new Scanner(System.in);
		money = sc.nextInt();
		for (int i = 1; i < juga.length; i++) {
			juga[i] = sc.nextInt();
		}
		
		int jhMoney = money;	// 준현의 현금
		int jhStock = 0;		// 준현의 보유 주식 수
		int smMoney = money;	// 성민의 현금
		int smStock = 0;		// 성민의 보유 주식 수
		
		// BNP, Buy and Pray - 준현의 매매법
		while (day <= 13) {
			if (jhMoney / juga[day] > 0) {
				jhStock += jhMoney / juga[day];
				jhMoney = jhMoney % juga[day]; 
			}
			day++;
		}
		// 준현의 14일의 자산
		int jhAsset = jhMoney + (juga[14] * jhStock);
		
		// TIMING - 성민의 매매법
		day = 2; // 날짜 초기화
		int smUpCnt = 0;	// 연속으로 오른 횟수
		int smDownCnt = 0;	// 연속으로 내려간 횟수
		while (day <= 13) {
			// 3일 연속으로 주가가 오르거나 내리는 변동 확인
			if (juga[day] > juga[day - 1]) {
				smUpCnt++;
				smDownCnt = 0;
			}
			else if (juga[day] < juga[day - 1]) {
				smUpCnt = 0;
				smDownCnt++;
			}
			else if (juga[day] == juga[day - 1]) {
				smUpCnt = 0;
				smDownCnt = 0;
			}
			
			// 3일 연속 가격이 전일 대비 하락 시 전량 매수
			if (smDownCnt >= 3) {
				if (smMoney / juga[day] > 0) {
					smStock += smMoney / juga[day];
					smMoney = smMoney % juga[day]; 
				}
			}
			// 3일 연속 가격이 전일 대비 상승 시 전량 매도
			else if (smUpCnt >= 3) {
				if (smStock > 0) {
					smMoney = smMoney + (juga[day] * smStock);
					smStock = 0;
				}
			}
			day++;
		}
		
		// 성민의 14일의 자산
		int smAsset = smMoney + (juga[14] * smStock);
		
		// 최종적으로 둘의 자산 비교
		if (jhAsset > smAsset) System.out.println("BNP");
		else if (jhAsset < smAsset) System.out.println("TIMING");
		else if (jhAsset == smAsset) System.out.println("SAMESAME");
	}
}