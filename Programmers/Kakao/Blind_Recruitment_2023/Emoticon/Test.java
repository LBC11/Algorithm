package Programmers.Kakao.Blind_Recruitment_2023.Emoticon;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};

        Solution s = new Solution();

        System.out.println(Arrays.toString(s.solution(users, emoticons)));
    }
}
