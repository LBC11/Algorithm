package MaxValue;

import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
2. 시간 복잡도
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {

        int row = 0;
        int col = 0;
        int max = 0;
        for(int i=0; i<9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp > max) {
                    max = temp;
                    row = i;
                    col = j;
                }
            }
        }

        sb.append(max).append("\n").append(row + 1).append(" ").append(col + 1);

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
