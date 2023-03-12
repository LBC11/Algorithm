package RepresentativeValue2;

import java.io.*;
import java.util.Arrays;

/*
1. 아이디어
    Arrays.sort() 한 후 3번째 수가 중앙값, Arrays.stream().sum() 이용

2. 시간 복잡도
    O(n^2)
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();

    static void solution(int[] numbers) {

        Arrays.sort(numbers);
        sb.append(Arrays.stream(numbers).sum()/5).append("\n").append(numbers[2]);
    }

    public static void main(String[] args) throws IOException {
        int[] numbers = new int[5];
        for(int i=0; i<5; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        solution(numbers);

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
