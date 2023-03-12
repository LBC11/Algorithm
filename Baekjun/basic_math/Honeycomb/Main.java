package basic_math.Honeycomb;

import java.util.Scanner;

/*
1. 아이디어
    k번째 원의 마지막 숫자는 그 전의 원의 마지막 숫자와 k*6만큼 차이난다.

2. 시간 복잡도
    O(n)
 */
public class Main {

    static int solution(int n) {
        // n이 1인 경우
        if(n==1) return 1;

        // 그 외
        int i = 1;
        int k = 1;
        while(i < n) {
            i += 6*k++;
        }
        return k;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(solution(n));
    }
}
