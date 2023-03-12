package basic_math.ACMHotel;

import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
    n번째 손님이 제일 위층에 머무를 때, YY는 h가 되고 XX는 n/h가 된다.
    그 외, YY는 n%h 이고 XX는 n/h+1이다.

2. 시간 복잡도
    O(1)
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuffer sb = new StringBuffer();

    static int solution(int h, int w, int n) {

        // n번째 손님이 제일 위층에 머무를 때
        if(n%h==0) {
            return h*100 + n/h;
        }

        // 그 외 상황
        else {
            return (n%h)*100 + (n/h+1);
        }

    }
    public static void main(String[] args) throws IOException {

        // t번 반복
        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            sb.append(solution(h,w,n));
            sb.append("\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();

    }
}
