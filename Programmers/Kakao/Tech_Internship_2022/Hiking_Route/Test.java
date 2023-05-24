package Programmers.Kakao.Tech_Internship_2022.Hiking_Route;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        int n = 7;
        int[][] paths = {{1, 4, 3}, {2, 4, 2}, {3, 4, 1}, {4, 5, 1}};
        int[] gates = {1, 2, 3};
        int[] summits = {5};

        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(n, paths, gates, summits)));
    }
}
