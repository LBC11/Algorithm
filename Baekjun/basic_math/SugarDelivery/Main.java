package basic_math.SugarDelivery;

import java.util.Scanner;

/*
1. 아이디어
    3kg 봉지 수가 가장 적을 때 배달하는 봉지의 개수가 최소가 된다.
    => (N-3K) % 5 = 0

2. 시간 복잡도
    O(n)
 */
public class Main {

    static int solution(int n) {
        int k=0;
        for(int i=0; i<=n/3; i++) {
            // 봉지의 최소 개수
            if ((n-i*3) % 5 == 0) {
                return ((n-i*3) / 5) + i;
            }
        }
        // 성립X
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        System.out.println(solution(n));
    }
}
