package study._220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class B17140 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//r,c,k 변수 입력받음
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());
		
		//리스트 만들고 크기 맞춰줌
		ArrayList<LinkedList<Integer>> arr = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			arr.add(new LinkedList<>());
		}
		
		//리스트 값 채움
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		//정렬 횟수 카운트하는 변수
		int count = 0;
		
		//r,c 값이 현재 배열 범위 내이고, 배열의 r,c 위치의 값이 k라면 0을 출력하고 메인함수 종료
		if (r < arr.size() && c < arr.get(0).size() && arr.get(r).get(c) == k) {
			System.out.println(count);
			return;
		}
		
		//r,c 값이 현재 배열의 범위보다 클 수도 있으므로, 무한루프로 해놓고 조건에 맞으면 안에서 break해줌
		while (true) {
			//현재 배열의 행이 배열의 열의 크기보다 같거나 크면 행 정렬 함수 호출
			if (arr.size() >= arr.get(0).size()) rowSort(arr);
			//현재 배열의 열이 행보다 크면 열 정렬 함수 호출
			else colSort(arr);
			//정렬 횟수 카운트++
			count++;
			
			//r,c 값이 현재 배열 범위 내이고, 배열의 r,c 값이 k라면 반복문 종료
			if (r < arr.size() && c < arr.get(0).size() && arr.get(r).get(c) == k) {
				break;
			}
			
			//정렬 횟수 카운트가 100을 넘어가면 카운트를 -1로 만들고 반복문 종료
			if (count > 100) {
				count = -1;
				break;
			}
		}
		
		//카운트 횟수 출력
		System.out.println(count);
	}
	
	//열 정렬하는 함수
	private static void colSort(ArrayList<LinkedList<Integer>> arr) {
		//열의 각 수와 등장 횟수를 세어줄 map선언
		HashMap<Integer, Integer> map;
		
		//열의 수만큼 반복
		for (int i = 0; i < arr.get(0).size(); i++) {
			
			//맵에 각 수를 키로 등장 횟수를 세어주고
			map = new HashMap<>();
			for (int j = 0; j < arr.size(); j++) {
				int key = arr.get(j).get(i);
				
				if (key == 0) continue;
				
				if (map.containsKey(key)) map.put(key, map.get(key) + 1);
				else map.put(key, 1);
			}
			
			//맵에 들어간 수와 등장 횟수를 정렬하기 쉽게 리스트로 만들어줌
			ArrayList<Integer[]> e = new ArrayList<>();
			for (Entry<Integer, Integer> entry : map.entrySet()) {
				e.add(new Integer[] {entry.getKey(), entry.getValue()});
			}
			
			//리스트로 만든 수와 등장 횟수를 조건에 맞게 정렬
			e.sort(new Comparator<Integer[]>() {

				@Override
				public int compare(Integer[] o1, Integer[] o2) {
					return o1[1] > o2[1] ? 1 : o1[1] == o2[1] ? o1[0] < o2[0] ? -1 : 1 : -1;
				}
			});
			
			//새로 정렬했을 때 생긴 값들의 수 즉, 새로운 행이 현재 배열의 행보다 크면
			int m = e.size() * 2 - arr.size();
			
			//그 횟수만큼 0으로 채워진 리스트를 현재 배열에 더해줌
			for (int j = 0; j < m; j++) {
				arr.add(new LinkedList<>());
				for (int k = 0; k < arr.get(0).size(); k++) {
					arr.get(arr.size()-1).add(0);
				}
			}
			
			//새로 정렬하여 만든 값을 원래 열에 채워줌
			int eIndex = 0;
			int j = 0;
			while (j < arr.size()) {
				if (eIndex < e.size()) {
					arr.get(j++).set(i, e.get(eIndex)[0]);
					arr.get(j++).set(i, e.get(eIndex)[1]);
				}
				else arr.get(j++).set(i, 0);
				eIndex++;
			}
		}
	}
	
	//행 정렬하는 함수
	private static void rowSort(ArrayList<LinkedList<Integer>> arr) {
		//행의 각 요소 개수를 세어줄 map
		HashMap<Integer, Integer> map;
		
		//각 행 중 가장 긴 열을 저장하는 변수
		int maxSize = 0;
		
		//행의 수만큼 반복
		for (int i = 0; i < arr.size(); i++) {
			map = new HashMap<>();
			
			//행의 수와 등장횟수를 map에 넣음
			while (!arr.get(i).isEmpty()) {
				//행은 열과 다르게 큐로 구현하여, 수와 등장횟수를 세면서 행을 비워줌
				int key = arr.get(i).poll();
				
				if (key == 0) continue;
				
				if (map.containsKey(key)) map.put(key, map.get(key) + 1);
				else map.put(key, 1);
			}
			
			//맵에 들어간 수와 등장 횟수를 정렬하기 쉽게 리스트로 만들어줌
			ArrayList<Integer[]> e = new ArrayList<>();
			for (Entry<Integer, Integer> entry : map.entrySet()) {
				e.add(new Integer[] {entry.getKey(), entry.getValue()});
			}
			
			//리스트로 만든 수와 등장 횟수를 조건에 맞게 정렬
			e.sort(new Comparator<Integer[]>() {

				@Override
				public int compare(Integer[] o1, Integer[] o2) {
					return o1[1] > o2[1] ? 1 : o1[1] == o2[1] ? o1[0] < o2[0] ? -1 : 1 : -1;
				}
			});
			
			//새로 정렬하여 만든 값을 원래 행에 채워줌
			for (Integer[] n : e) {
				arr.get(i).add(n[0]);
				arr.get(i).add(n[1]);
			}
			
			//현재 행의 열 크기를 비교하여 큰 값 저장
			maxSize = arr.get(i).size() < maxSize ? maxSize : arr.get(i).size();
		}
		
		//각 행을 돌면서 가장 긴 열만큼 0을 더해줌
		for (int i = 0; i < arr.size(); i++) {
			while (maxSize != arr.get(i).size()) {
				arr.get(i).add(0);
			}
		}
	}

}




