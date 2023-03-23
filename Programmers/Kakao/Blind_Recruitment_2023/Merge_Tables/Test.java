package Programmers.Kakao.Blind_Recruitment_2023.Merge_Tables;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        String[] commands = {"UPDATE 1 1 1", "PRINT 1 1"};

        Solution s = new Solution();

        System.out.println(Arrays.toString(s.solution(commands)));
    }
}
