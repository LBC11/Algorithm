package MatrixAddition;

import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
    각 항목의 수를 int[n][m] 배열에 +=으로 저장한다.

2. 시간 복잡도
    O(n)
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] sum = new int[n][m];

        // a행렬
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                sum[i][j] += Integer.parseInt(st.nextToken());
            }
        }

        // b행렬
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                sum[i][j] += Integer.parseInt(st.nextToken());
            }
        }

        // 출력
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(sum[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
