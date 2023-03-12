package basic_math.BigNumber;

import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
    char 배열을 이용하여 각 자리의 숫자를 더한다.
    각 자리의 숫자는 (char) num - '0' 을 통해 구할 수 있다.
    합이 10을 넘기면 다음 자리의 수로 1을 넘긴다.

2. 시간 복잡도
    O(n) , n은 각 숫자의 길이
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();

    static void solution(char[] longer, char[] shorter) {

        // 합의 결과가 10이상일 경우 temp 사용
        int temp = 0;

        // shorter length 내 계산
        for(int i=0; i < shorter.length; i++) {

            // 각 자리의 숫자와 temp 의 합
            int sum = (longer[i] - '0') + (shorter[i] - '0') + temp;

            // temp 초기화
            temp = 0;

            // sum 이 10이상일 경우
            if(sum>=10) {
                sum -= 10;
                temp++;
            }

            sb.append(sum);
        }

        // shorter length 외 계산
        for(int i=shorter.length; i < longer.length; i++) {

            // 각 자리의 숫자와 temp 의 합
            int sum = (longer[i] - '0') + temp;

            // temp 초기화
            temp = 0;

            // sum 이 10이상일 경우
            if(sum>=10) {
                sum -= 10;
                temp++;
            }

            sb.append(sum);
        }

        if(temp != 0) {
            sb.append(temp);
        }

        // 다시 정상적인 순서로
        sb.reverse();

    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 계산의 편의성을 위해 역순으로
        char[] a = new StringBuffer(st.nextToken()).reverse().toString().toCharArray();
        char[] b = new StringBuffer(st.nextToken()).reverse().toString().toCharArray();

        if(a.length >= b.length) {
            solution(a,b);
        } else {
            solution(b,a);
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
