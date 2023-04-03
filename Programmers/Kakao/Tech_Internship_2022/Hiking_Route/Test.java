package Programmers.Kakao.Tech_Internship_2022.Hiking_Route;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        int n = 6;
        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[] gates = {1, 3};
        int[] summits = {5};

        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(n, paths, gates, summits)));
    }
}
