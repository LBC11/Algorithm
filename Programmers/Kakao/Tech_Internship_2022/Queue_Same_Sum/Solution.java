package Programmers.Kakao.Tech_Internship_2022.Queue_Same_Sum;

class Solution {
    public int solution(int[] queue1, int[] queue2) {

        int num1 = countNum(queue1, queue2);
        int num2 = countNum(queue2, queue1);

        return Math.max(num1, num2);
    }

    private int countNum(int[] queue1, int[] queue2) {

        int[] temp = new int[queue1.length * 2];

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

        long tempSum = 0;
        int s = 0;
        int e = 0;
        while(true) {
            if(tempSum < target) {
                tempSum += temp[e++];
            }

            else if(tempSum == target || e == temp.length) {
                break;
            }

            else {
                tempSum -= temp[s++];
            }
        }

        if(tempSum != target) return -1;

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