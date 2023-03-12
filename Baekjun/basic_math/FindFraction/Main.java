package basic_math.FindFraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
1. 아이디어
    배열을 대각선으로 보았을 때 k-1번째 열에는 K-1개의 분수가 존재한다.
    K-1번째 열의 분수와 분모의 합은 k이다.
    짝수번째 열의 N번째 분수는 N + "/" + (k-N)이다.
    홀수번째 열의 N번째 분수는 (k-N) + "/" + N이다.

2. 시간 복잡도
    O(n)
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String solution(int n) {
        if(n==1) return "1/1";

        int i = 1;
        int k = 2;

        // n이 속한 k-1번째 열 찾기
        while(i<n) {
            i+=k++;
        }

        // 해당 열에서 몇번째 분수 인지
        int N = n-(i-(k-1));
        // k-1번째 열이 짝수 일때
        if((k-1)%2==0) {
            return N + "/" + (k-N);
        }
        // k-1번째 열이 홀수 일때
        else {
            return (k-N) + "/" + N;
        }
    }
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        System.out.println(solution(n));
    }
}
