package study._220804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B17413 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		
		//문장 받아옴
		String sentence = bf.readLine();
		
		//문자들을 저장해놓을 임시 저장소
		StringBuilder temp = new StringBuilder();
		
		/**
		 * 문장에서 문자를 하나씩 가져오면서 작업을 수행함
		 */
		int i = 0;
		while (i < sentence.length()) {
			if (sentence.charAt(i) == '<') { // 문자가 '<'일 경우  
				result.append(temp.reverse()); // 이전 임시 저장소에 있던 문자들을 거꾸로 하여 결과 저장소에 넣음
				temp = new StringBuilder(); // 새로운 임시 저장소 생성
				
				while (sentence.charAt(i) != '>') { // 그리고 문자가 '>'가 나올때까지 
					temp.append(sentence.charAt(i)); // 문자들을 임시 저장소에 넣음
					i++;
				}
				temp.append(sentence.charAt(i)); // '>'문자까지 임시 저장소에 넣고
				result.append(temp); // 거꾸로 하지 않고 결과 저장소에 넣음
				temp = new StringBuilder(); 
			}
			else if (sentence.charAt(i) == ' ') { // 문자가 공백 문자일 경우
				result.append(temp.reverse()); // 이전 임시 저장소에 있던 문자들을 거꾸로 하여 결과 저장소에 넣음
				result.append(" "); // 공백을 결과 저장소에 넣음
				temp = new StringBuilder();
			}
			else {
				temp.append(sentence.charAt(i)); // 일반 문자들일 경우 임시 저장소에 넣음
			}
			i++; //인덱스 더해줌
		}
		
		if (temp != null) {
			result.append(temp.reverse()); // 마지막으로 임시 저장소에 남아있던 값들 거꾸로 하여 결과 저장소에 넣음
		}
		
		System.out.println(result.toString());
	}
}
