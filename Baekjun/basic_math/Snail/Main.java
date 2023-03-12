package basic_math.Snail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 아이디어
    나무 막대를 오르는 마지막 날의 시작점은 (V-A)m 이상이어야 한다.
    마지막 날을 제외하면 A-B만큼 올라간다.
    => (V-A)/(A-B) + 1

2. 시간 복잡도
    O(1)
 */
public class Main {

    static int solution(int a, int b, int v) {
        if((v-a)%(a-b) == 0) {
            return (v-a)/(a-b) + 1;
        }
        else {
            return (v-a)/(a-b) + 2;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        System.out.println(solution(a,b,v));

    }
}
