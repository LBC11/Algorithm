package Programmers.Kakao.Blind_Recruitment_2021.Menu_Renewal;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = {2, 3, 5};

        Solution s = new Solution();

        System.out.println(Arrays.toString(s.solution(orders, course)));
    }
}
