package DP;
/*
아이디어
1. DP
2. after -> before 로 생각하니 복잡했는데, before -> after 로 접근하니 훨씬 간단하다.
 */
class Solution {

    int solution(int[] t, int[] p) {

        int length = t.length;

        int[] dp = new int[length + 1];

        for (int i = 0; i < length; i++) {
            if (i + t[i] <= length) {
                dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
            }
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        return dp[length];
    }
}
