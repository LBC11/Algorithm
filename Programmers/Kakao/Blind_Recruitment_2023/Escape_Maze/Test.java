package Programmers.Kakao.Blind_Recruitment_2023.Escape_Maze;

public class Test {

    public static void main(String[] args) {
        Solution_DFS s = new Solution_DFS();

        int n = 5;
        int m = 4;
        int x = 3;
        int y = 2;
        int r = 2;
        int c = 3;
        int k = 4;

        System.out.println(s.solution(n, m, x, y, r, c, k));
    }
}
