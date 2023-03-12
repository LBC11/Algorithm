package ColoredPaper;

import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
    2차원 배열을 이용해 하얀 종이 표현.
    검은색 색종이가 붙으면 true, 아니면 false.

2. 시간 복잡도
    O(n)
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static boolean[][] white = new boolean[100][100];

    static void paint(int n, int m) {
        for(int i=n; i<n+10; i++) {
            for(int j=m; j<m+10; j++) {
                white[i][j] = true;
            }
        }
    }
    static int solution(int[][] papers) {

        // 각 검은색 색종이 white paper 에 반영
        for (int[] paper : papers) {
            paint(paper[0], paper[1]);
        }

        // 검은색 종이 넓이
        int count = 0;
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(white[i][j]) count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {

        // t개의 종이
        int t = Integer.parseInt(br.readLine());
        int[][] papers = new int[t][2];

        for(int i=0; i<t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            papers[i][0] = Integer.parseInt(st.nextToken());
            papers[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(papers));

        br.close();
    }
}
