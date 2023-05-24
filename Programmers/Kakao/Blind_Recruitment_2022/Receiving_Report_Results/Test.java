package Programmers.Kakao.Blind_Recruitment_2022.Receiving_Report_Results;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        Solution s = new Solution();

        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] reports = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;

        System.out.println(Arrays.toString(s.solution(id_list, reports, k)));

    }
}
