package FindDecimal;

import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
    n을 2~(n/2)까지로 나누었을 때 나머지가 0이면 소수가 아니다.

2. 시간 복잡도
    O(n^2)
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int count;

    static void solution(int n) {

        // for 문의 예외사항들
        if(n == 1) return ;
        if(n == 2 || n == 3) {
            count++;
            return ;
        }

        // 2~n/2 로 나누었을 때 0인지 검증
        for(int i=2; i<=n/2; i++) {
            if(n%i==0) {
                return ;
            }
        }
        count++;

    }

    public static void main(String[] args) throws IOException {
        // t개의 숫자 검증
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<t; i++) {
            solution(Integer.parseInt(st.nextToken()));
        }

        System.out.println(count);

    }
}
