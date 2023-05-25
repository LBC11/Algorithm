package Programmers.Kakao.Blind_Recruitment_2022.Decimal_Number_In_K;

/*
1. 아이디어
    Integer.toString 을 이용해 k 진수로 변환
    split("0") 을 이용해 string 안의 숫자 추충
    각 숫자가 decimal 인지 검사
    decimal 검사시 Math.sqrt(num) 까지만 나누어보면 된다.

2. 실패 분석
    처음에 num 의 크기 상한이 1000000 이길래 array 를 이용해 백만까지의 소수를 미리 구했었다.
    -> 2진수로 변환시 백만도 매우 큰 string 으로 변환 가능!!!, 심지어 Long 으로 받아야 하는 경우도 생긴다.
 */
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
