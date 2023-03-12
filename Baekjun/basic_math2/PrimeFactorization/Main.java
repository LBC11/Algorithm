package PrimeFactorization;

import java.io.*;

/*
1. 아이디어
    n을 i로 나누었을 때 0이면 n/i를 하고 i는 String 에 저장한다.
    위의 과정을 n 이 1이 될 때까지 진행한다.

2. 시간 복잡도
    O(n)
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();

    static void solution(int n) {

        while(n != 1) {
            for(int i=2; i<=n; i++) {
                if(n%i==0) {
                    sb.append(i).append("\n");
                    n /= i;
                    break;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());

        solution(n);

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }
}
