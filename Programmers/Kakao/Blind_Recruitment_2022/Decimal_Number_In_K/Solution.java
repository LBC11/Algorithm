package Programmers.Kakao.Blind_Recruitment_2022.Decimal_Number_In_K;

class Solution {

    public int solution(int n, int k) {

        String s = Integer.toString(n, k);

        String[] numbers = s.split("0");

        int answer = 0;

        for (String number : numbers) {
            if (!number.equals("") && isDecimal(Long.parseLong(number))) answer++;
        }

        return answer;
    }

    private boolean isDecimal(long num) {

        if(num == 1) return false;

        for(long i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }

        return true;
    }
}
