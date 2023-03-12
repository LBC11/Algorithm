package FindPrimeNumber;

import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
    prime number 의 배수는 prime number 가 아니다.
    boolean 배열을 이용해 prime number 인지 검증한 정보 저장

2. 시간 복잡도
    O(n^2)

 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();

    static void solution(int start, int end) {

        boolean[] numbers = new boolean[end+1];

        numbers[1] = true;

        for(int i=2; i<=end; i++) {
            if(!numbers[i]) {
                for (int j=2*i; j<=end; j+=i) {
                    numbers[j] = true;
                }
            }
        }

        for(int i=start; i<=end; i++) {
            if(!numbers[i]) sb.append(i).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        solution(start, end);

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
