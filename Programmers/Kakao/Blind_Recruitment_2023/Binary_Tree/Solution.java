package Programmers.Kakao.Blind_Recruitment_2023.Binary_Tree;

class Solution {

    long log_base_two(long num) {
        return (long) (Math.log(num) / Math.log(2));
    }

    boolean check(long num, long middle_idx, long height) {

        // idx 가 짝수면 leaf node 이다.
        if (height == 2) {

            return true;
        }

        boolean left = true;
        boolean right = true;

        // root_idx 에서 child 까지의 거리
        long dist = (long) Math.pow(2, height - 2);

        if (((num >> middle_idx - dist) & 1) == 0) {

            for (long i = middle_idx - 2 * dist; i < middle_idx; i++) {

                if (((num >> middle_idx - dist) & 1) == 1) return false;
            }
        }

        else {
            left = check(num, middle_idx - dist, height - 1);
        }

        if (((num >> middle_idx + dist) & 1) == 0) {

            for (long i = middle_idx + 1; i <= middle_idx + 2 * dist; i++) {

                if (((num >> middle_idx - dist) & 1) == 1) return false;
            }
        }

        else {
            right = check(num, middle_idx + dist, height - 1);
        }

        return left && right;
    }

    boolean binary_check(long num) {

        long largest_bit = log_base_two(num);

        long height = log_base_two(largest_bit) + 1;

        long total_node = ((long) Math.pow(2, height)) - 1;

        // total_node / 2 가 이진 트리의 root idx
        // root node 가 0이라면 이진트리로 표현할 수 없다.
        if (((num >> total_node / 2) & 1) == 0) return false;

        return check(num, total_node / 2 , height);
    }

    public int[] solution(long[] numbers) {

        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            if (binary_check(numbers[i])) answer[i] = 1;
        }
        return answer;
    }
}