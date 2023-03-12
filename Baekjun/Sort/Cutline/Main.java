package Cutline;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1. 아이디어
    Arrays.sort() 이용

2. 시간 복잡도
    O(n^2)
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] scores = new int[n];
        for(int i=0; i<n; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(scores);
        System.out.println(scores[scores.length-k]);
    }
}
