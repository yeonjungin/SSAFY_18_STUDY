package week01;

import java.util.*;

public class b20291_fileOrganize {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		HashMap<String, Integer> dic = new HashMap<String, Integer>();
		
		for (int i = 0; i < n; i++) {
			String str = sc.next().split("[.]")[1];
			if (dic.containsKey(str)) dic.put(str, dic.get(str) + 1);
			else dic.put(str, 1);
		}
		
		List<String> keySet = new ArrayList<>(dic.keySet());
		Collections.sort(keySet);
		for (String key : keySet) {
			System.out.println(key + " " + dic.get(key));
		}
	}
}