package Programmers.Kakao.Tech_Internship_2022.Queue_Same_Sum;

/*
주요 아이디어
1. 두 개의 queue 를 이은 temp array 생성
2. two pointer 를 이용해서 해당 array 에서 부분합이 target(전체 합의 반) 이 되는 지점 찾기
3. 해당 부분의 start, end 지점에 따라 insert, pop 횟수 계산
3-1. end 가 temp array 를 벗어나서 종료되었다면 -1 반환
4. 어떤 queue 가 앞에 오는지에 따라 결과가 달라지기에 순서를 바꿔서 한번 더 계산
5. 두 값중 -1 을 제외한 가장 작은 값이 최종값

 */
class Solution {
    public int solution(int[] queue1, int[] queue2) {

        // queue1 이 앞의 순서일 떄 insert, pop 수행 횟수
        int num1 = countNum(queue1, queue2);

        // queue2 이 앞의 순서일 떄 insert, pop 수행 횟수
        int num2 = countNum(queue2, queue1);

//        System.out.println(num1+" "+num2);

        // 두 수중 -1을 제외하고 가장 작은 값 return
        if(num1 == -1 && num2 == -1) return -1;
        else if(num1 == -1) return num2;
        else if(num2 == -1) return num1;
        else return Math.min(num1, num2);
    }

    private int countNum(int[] queue1, int[] queue2) {

        // 모든 값이 들어갈 temp array
        int[] temp = new int[queue1.length * 2];

        // target 계산을 위한 sum
        long sum = 0;
        for(int i=0; i< queue1.length; i++) {
            temp[i] = queue1[i];
            sum += queue1[i];
        }

        for(int i=0; i< queue2.length; i++) {
            temp[i+ queue1.length] = queue2[i];
            sum += queue2[i];
        }

        long target = sum / 2;

        // two pointer 연산
        long tempSum = 0;
        int s = 0;
        int e = 0;
        while(true) {
            if(tempSum == target || e == temp.length) {
                break;
            }

            // 현재까지의 부분합이 target 보다 작다면 end++
            else if(tempSum < target) {
                tempSum += temp[e++];
            }

            // 현재까지의 부분합이 target 보다 크다면 start++
            else {
                tempSum -= temp[s++];
            }
        }

        // target 만큼의 부분합을 가진 구간이 존재하지 않을 때
        if(tempSum != target) return -1;

        // start, end 지점에 따라 insert, pop 횟수 계산
        if(s < queue1.length && e < queue1.length) {
            return (e + s + queue2.length);
        }

        else if(s < queue1.length && e >= queue1.length) {
            return (s + e - queue2.length);
        }

        else {
            return (e + s + queue2.length);
        }
    }
}