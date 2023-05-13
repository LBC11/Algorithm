package DP;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

//        System.setIn(new java.io.FileInputStream("C:/Users/LBC/Desktop/Algorithm/Baekjun/DP/testcase.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Solution sol = new Solution();

        int TC = Integer.parseInt(br.readLine());

        for (int testcase = 1; testcase <= TC; testcase++) {
            int n = Integer.parseInt(br.readLine());

            int[] t = new int[n];
            int[] p = new int[n];

            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                t[i] = Integer.parseInt(st.nextToken());
                p[i] = Integer.parseInt(st.nextToken());
            }

            if (sol.solution(t, p) == Integer.parseInt(br.readLine())) {
                System.out.println("#" + testcase + " 100");
            } else {
                System.out.println("#" + testcase + " 0");
            }
        }
    }
}
