package DivideConquer.Fractal;

import java.io.*;
import java.util.StringTokenizer;

/*
아이디어
1. s를 증가시키면서 검은부분을 칠하는 것은 array 를 확장시켜야 하기에 반대로 생각하는 것이 편하다.
2. 모든 칸을 검사하는 것은 비효율적이고 크기가 매우 크기 때문에 memory 문제가 발생하므로
   출력하고자 하는 부분만 검사한다.
3. check 가 한번 true 가 되면 size 가 1이 될때까지 계속 true 라는 것이 핵심이다.

 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int[][] arr = new int[51][51];
    static int s, n, k, r1, r2, c1, c2;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        sol(0, 0, (long) Math.pow(n, s), false);

        for (int i = 0; i <= r2 - r1; i++) {
            for (int j = 0; j <= c2 - c1; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }

    private static void sol(int x, int y, long size, boolean check) {

        // 출력을 위한 범위 밖이라면 계산할 필요가 없다.
        if (x > c2 || x + size <= c1 || y > r2 || y + size <= r1) return;

        if (size == 1) {
            if(check) {
                arr[y - r1][x - c1] = 1;
            }

            return;
        }

        long next_size = size / n;
        int start = (n - k) / 2;
        int end = (n + k) / 2;

        for (int i = 0; i < n; i++) {
            int next_y = (int) (y + next_size * i);

            for (int j = 0; j < n; j++) {
                int next_x = (int) (x + next_size * j);
                sol(next_x, next_y, next_size, check || (i >= start && i < end) && (j >= start && j < end));
            }
        }
    }
}
