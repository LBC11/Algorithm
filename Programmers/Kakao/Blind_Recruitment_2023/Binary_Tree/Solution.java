package Programmers.Kakao.Blind_Recruitment_2023.Binary_Tree;
/*
주요 아이디어
1. 이진수로 표현된 것에서 완전 이진 트리의 규칙 추정 -> 111 = 7
2. 각 숫자를 이진수로 표현할 때 tree 의 height 가 몇인지 추론
3. 재귀 방식으로 이진수를 root 를 기준으로 left 와 right 로 분할하여 검사
    child 가 1인 경우: child 를 root 로 가지는 subtree 에서 탐색 지속
    child 가 0인 경우: child 를 root 로 가지는 subtree 에서 0이 있는지 확인

실패 분석
1. num = 8 인 경우처럼 딱 2의 제곱수에 맞으면서 root 인 경우를 대비하여 largest_bit = log_two(num) + 1 해야 한다.
2. largest_bit 가 1 이하인 경우 log_two(0) 은 -9..... 수로 규격외의 수가 나온다.

 */
class Solution {

    long log_base_two(long num) {
        return (long) (Math.log(num) / Math.log(2));
    }

    // middle_idx 의 모든 자손이 0인지 확인
    // true -> 1인 자손 존재 o
    // false -> 1인 자손 존재 x
    boolean check(long num, long middle_idx, long height) {

        // height = 2 에서 이미 해당 idx 가 0이라고 검증되었기 때문에
        if (height == 1) return false;

        // root_idx 에서 child 까지의 거리
        long dist = (long) Math.pow(2, height - 2);

        boolean left;
        boolean right;

        // right child 가 1일 경우
        if (((num >> middle_idx - dist) & 1) == 1) {

            return true;
        }

        // right child 가 0인 경우
        else {
            // right child 가 root 인 sub tree 에서 탐색 계속
            right = check(num, middle_idx - dist, height - 1);
        }

        //  left child 가 1일 경우
        if (((num >> middle_idx + dist) & 1) == 1) {

            return true;
        }

        //  left child 가 0일 경우
        else {

            // right child 가 root 인 sub tree 에서 탐색 계속
            left = check(num, middle_idx + dist, height - 1);
        }

        return left || right;
    }

    // 해당 숫자를 이진 트리로 표현할 수 있는 지 check
    boolean isTree(long num, long middle_idx, long height) {

        // leaf node 는 1이든 0이든 상관없다.
        if (height == 1) return true;

        boolean left = true;
        boolean right = true;

        // root_idx 에서 child 까지의 거리
        long dist = (long) Math.pow(2, height - 2);

        // right child 가 1일 경우
        if (((num >> middle_idx - dist) & 1) == 1) {

            // right child 가 root 인 sub tree 에서 탐색 계속
            right = isTree(num, middle_idx - dist, height - 1);
        }

        // right child 가 0인 경우
        else {

            // right child 의 자손 중 1 이 있는 경우
            if (check(num, middle_idx - dist, height - 1)) return false;
        }

        // left child 가 1인 경우
        if (((num >> middle_idx + dist) & 1) == 1) {

            // left child 가 root 인 sub tree 에서 탐색 계속
            left = isTree(num, middle_idx + dist, height - 1);
        }

        // left child 가 0인 경우
        else {

            // left child 의 자손 중 1 이 있는 경우
            if (check(num, middle_idx + dist, height - 1)) return false;
        }

        return left && right;
    }

    boolean binary_check(long num) {

        // 해당 숫자를 이진수로 변환했을 때 1이면서 가장 왼쪽인 bit 의 위치
        long largest_bit = log_base_two(num) + 1;

        // 이진 트리로 표현했을 때 tree 의 height
        long height;

        // largest bit 가 1 이하면 매우 작은 수가 나온다.
        if(largest_bit < 1) height = 1;
        else height = log_base_two(largest_bit) + 1;

        // 이진 트리로 표현했을 때 tree 의 total node 개수
        long total_node = ((long) Math.pow(2, height)) - 1;

        // total_node / 2 가 이진 트리의 root idx
        // root node 가 0이라면 이진트리로 표현할 수 없다.
        if (((num >> total_node / 2) & 1) == 0) return false;

        // tree 의 root node idx 는 total node / 2 한 값이다.
        return isTree(num, total_node / 2, height);
    }

    public int[] solution(long[] numbers) {

        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            if (binary_check(numbers[i])) answer[i] = 1;
        }
        return answer;
    }
}