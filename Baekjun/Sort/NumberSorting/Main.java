package NumberSorting;

import java.io.*;
import java.util.Arrays;

/*
1. 아이디어
    Arrays.sort() 이용

2. 시간 복잡도
    O(n^2)
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        int[] num = new int[t];
        for(int i=0; i<t; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(num);
        for (int i : num) {
            sb.append(i).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }
}
