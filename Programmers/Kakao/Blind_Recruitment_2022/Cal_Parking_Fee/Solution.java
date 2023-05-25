package Programmers.Kakao.Blind_Recruitment_2022.Cal_Parking_Fee;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/*
1. 아이디어
    간단한 구현 문제, 어떠한 자료구조를 사용할지가 중요
    주차장 역할을 할 parking, 각 자동차의 주차장 이용시간 저장할 durations
    동시성 문제가 발생했던 점을 잘 봐둬라!!!
 */
class Solution {

    // car_number, 입실 시간
    HashMap<Integer, Integer> parking;

    // car_number, car_duration
    HashMap<Integer, Integer> durations;

    public int[] solution(int[] fees, String[] records) {

        parking = new HashMap<>();

        durations = new HashMap<>();

        for(String record : records) {

            String[] rec = record.split(" ");

            int num = Integer.parseInt(rec[1]);
            int minTime = ToMinute(rec[0]);

            // 주차장에 들어가는 경우
            if(rec[2].equals("IN")) {
                parking.put(num, minTime);
            }

            // 주차장에서 나오는 경우
            else {
                durations.put(num,durations.getOrDefault(num, 0 ) + minTime - parking.get(num));
                parking.remove(num);
            }
        }

        // 23:59 에 출차한 것으로 간주하는 경우
        for(int num : parking.keySet()) {
            durations.put(num,durations.getOrDefault(num, 0 ) + ToMinute("23:59") - parking.get(num));

            // parking.keySet 을 순회하는 중에 parking 의 element 를 remove 하는 행위는 동시성 문제를 일으킨다.
            // parking.remove(num);
        }

        List<Integer> list = durations.keySet().stream().sorted().collect(Collectors.toList());

        int[] answer = new int[list.size()];

        int idx = 0;
        for(int num : list) {

            int duration  = durations.get(num);
            int fee;

            // 기본 요금만 내는 경우
            if(duration <= fees[0]) fee = fees[1];

            // 초과 요금을 내는 경우
            else {
                fee = (fees[1] + ((duration - fees[0]) / fees[2]) * fees[3]);

                if((duration - fees[0]) % fees[2] != 0) fee += fees[3];
            }

            answer[idx++] = fee;
        }
        return answer;
    }

    public int ToMinute(String s) {

        String[] time = s.split(":");

        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
}
