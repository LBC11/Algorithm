package Programmers.Kakao.Blind_Recruitment_2023.Collection_Personal_Info;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};

        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(today, terms, privacies)));
    }
}
