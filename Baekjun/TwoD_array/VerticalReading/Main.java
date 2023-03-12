package TwoD_array.VerticalReading;

import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        char[][] array = new char[5][15];

        String s;

        int row = 0;

        // 각 array 에 char 할당
        while (row < 5) {

            s = br.readLine();

            for(int i=0; i<s.length(); i++) {
                array[row][i] = s.charAt(i);
            }

            row++;
        }

        // array[][] 가 default value 가 아닐 때 세로로 sb 에 더한다.
        for (int i=0; i< array[0].length; i++) {
            for(int j=0; j< array.length; j++) {

                if(array[j][i] != '\u0000') sb.append(array[j][i]);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
