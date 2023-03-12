package Decimal;

import java.io.*;

/*
1. 아이디어
    boolean 배열을 이용해서 주어지는 범위 내의 수가 소수인지 검증한다.
    소수가 맞으면 false, 아니면 true
    합과 min 값은 배열을 이용해 구한다.

2. 시간 복잡도
    O(n)

 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();
    static void solution(int start, int end) {

        // 검증 정보를 저장할 배열
        boolean[] numbers = new boolean[end+1];

        // 소수면 false, 아니면 true
        numbers[1] = true;
        for(int i=2; i<=end; i++) {
            if(!numbers[i]) {
                for(int k=2*i; k<=end; k += i) {
                    numbers[k] = true;
                }
            }
        }

        // 소수의 합 구하기
        int sum = 0;
        for(int i=start; i<=end; i++) {
            if(!numbers[i]) sum += i;
        }

        // 해당 범위에 소수가 없을 경우
        if(sum == 0) {
            sb.append(-1);
            return;
        }

        // 해당 범위에 소수가 있을 경우
        sb.append(sum).append("\n");

        // 해당 범위에서 가장 작은 소수 구하기
        for(int i=start; i<=end; i++) {
            if(!numbers[i]) {
                sb.append(i);
                break;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        int start = Integer.parseInt(br.readLine());
        int end = Integer.parseInt(br.readLine());

        solution(start, end);

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
