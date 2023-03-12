package basic_math.PresidentOfWomen;

import java.io.*;
import java.util.stream.IntStream;

/*
1. 아이디어
    i,i 번째 호에 살고 있는 사람 수는 i-1,i 번째 호 와 i,i-1번째 호에 살고있는 사람 수의 합이다.

2. 시간 복잡도
    O(n^2)
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();

    static int[][] apart = new int[15][15];

    static int solution(int k, int n) {
        return apart[k][n];
    }

    public static void main(String[] args) throws IOException {

        // apart 사람 수 채우기
        IntStream.range(0,15).forEach(i -> apart[0][i] = i);
        IntStream.range(0,15).forEach(i -> apart[i][0] = 0);

        for(int i=1; i<15; i++) {
            for(int j=1; j<15; j++) {
                apart[i][j] = apart[i-1][j] + apart[i][j-1];
            }
        }

        // t번 반복
        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            sb.append(solution(k,n)).append("\n");
        }

        bw.write(sb.deleteCharAt(sb.length()-1).toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
