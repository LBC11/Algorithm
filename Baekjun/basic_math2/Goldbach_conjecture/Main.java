package Goldbach_conjecture;

import java.io.*;

/*
1. 아이디어
    i, n-i 둘다 소수여야 한다.
    둘의 차가 적은 조합을 한번에 찾으려면 n/2부터 --로 탐색하면 된다.

2. 시간 복잡도
    O(n)
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();
    static boolean[] numbers = new boolean[10001];

    static String solution(int n) {

        // 두 소수의 차가 적은 것을 찾기 위해 중간부터 시작
        for(int i=n/2; i>=2; i--) {
            // 두 수 모두 소수인 경우
            if(!numbers[i] && !numbers[n-i]) {
                return i+" "+(n-i);
            }
        }

        return " ";
    }

    public static void main(String[] args) throws IOException {

        // 소수 검증
        numbers[1] = true;
        for(int i=2; i < numbers.length; i++) {
            if(!numbers[i]) {
                for(int j=2*i; j < numbers.length; j+=i) {
                    numbers[j] = true;
                }
            }
        }

        // t번 반복
        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            sb.append(solution(Integer.parseInt(br.readLine()))).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
