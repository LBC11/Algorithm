package basic_math.BreakEvenPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 아이디어
    A: 고정 비용, B: 가변 비용, C: 판매 가격
    B>=C 이면 이익이 발생할 수 없다.
    answer = A/(C-B) + 1

2. 시간 복잡도
    O(1)
 */
public class Main {

    static long solution(long fixedCost, long prodCost, long price) {
        // B>=C일 때
        if(prodCost >= price) return -1;

        // B<C일 때
        return fixedCost/(price-prodCost) +1;
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        long fixedCost = Long.parseLong(st.nextToken());
        long prodCost = Long.parseLong(st.nextToken());
        long price = Long.parseLong(st.nextToken());

        System.out.println(solution(fixedCost, prodCost, price));

    }
}
