package Programmers.Kakao.Blind_Recruitment_2023.Emoticon;

public class temp {

    public static void main(String[] args) {
        int n = 3/* n 값 */;
        int count = 0; // 탐색한 횟수

        int[] nodes = new int[n]; // 현재 깊이에서 탐색한 노드 번호를 저장하는 배열

        for (int i = 0; i < n; i++) {
            nodes[i] = 0; // 초기값 설정
        }

        while (true) {
            count++; // 탐색 횟수 증가
            int depth = n - 1; // 현재 깊이
            nodes[depth]++; // 현재 깊이에서 탐색한 노드 번호 증가

            while (nodes[depth] == 4) { // 현재 깊이에서 4방향 모두 탐색한 경우
                nodes[depth] = 0; // 다음 노드를 탐색하기 위해 초기화
                depth--; // 이전 깊이로 이동
                if (depth < 0) { // 더 이상 탐색할 깊이가 없는 경우
                    System.out.println("탐색한 횟수: " + count);
                    return;
                }
                nodes[depth]++; // 이전 깊이에서 탐색한 노드 번호 증가
            }
        }

    }
}
