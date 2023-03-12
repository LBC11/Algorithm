package Bertrand_postulate;

import java.io.*;

/*
1. 아이디어
    소수의 배수는 소수가 아니다.
    위의 문장을 이용해서 2*n 까지 숫자를 소수인지 검증한다.
    n ~ 2*n 사이에서 소수가 몇개인지 count 하기

2. 시간 복잡도
    O(n^2)

 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();

    static int solution(int n) {

        if(n==1) {
            return 1;
        }

        int count = 0;

        boolean[] numbers = new boolean[2*n+1];

        numbers[1] = true;
        for(int i=2; i<=2*n; i++) {
            if(!numbers[i]) {
                for(int j=2*i; j<=2*n; j+=i) {
                    numbers[j] = true;
                }
            }
        }

        for(int i=n+1; i<=2*n; i++) {
            if(!numbers[i]) count++;
        }

        return count;
    }

    public static void main(String[] args) throws IOException {

        while(true) {
            int n = Integer.parseInt(br.readLine());

            // n 이 0이면 종료
            if(n == 0) break;

            // n 이 아닐 경우
            sb.append(solution(n)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }
}
