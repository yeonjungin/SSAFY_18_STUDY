package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n20546 { 
	public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int money = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] arrays = new int[14];
        int index = 0;
        while(stringTokenizer.hasMoreTokens()){
            arrays[index++] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int j = 0;
        int jMoney = money;

        int s = 0;
        int sMoney = money;

        for (int i = 0; i < arrays.length; i++) {
            int array = arrays[i];

            j += jMoney/array;
            jMoney %= array;

            if(isHigh(arrays,i)){
                sMoney += s * array;
                s = 0;
            }
            if(isLow(arrays,i)){
                s += sMoney/array;
                sMoney %= array;
            }
        }

        jMoney += j * arrays[13];
        sMoney += s * arrays[13];

        System.out.println(jMoney == sMoney ? "SAMESAME" : (jMoney > sMoney ? "BNP" : "TIMING"));
    }

    private static boolean isLow(int[] arrays, int i) {
        if(i < 3){
            return false;
        }

        return arrays[i-3] > arrays[i-2] && arrays[i-2] > arrays[i-1] && arrays[i-1] > arrays[i];
    }

    private static boolean isHigh(int[] arrays, int i) {
        if(i < 3){
            return false;
        }

        return arrays[i-3] < arrays[i-2] && arrays[i-2] < arrays[i-1] && arrays[i-1] < arrays[i];
    }
}