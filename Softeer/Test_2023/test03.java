package Softeer.Test_2023;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class test03 {

    static Car[] cars;

    public static String solution(String param0, int param1) {

        PriorityQueue<String> pq = new PriorityQueue<>();

        int time = toMonth(param0);
        int num = param1;

        StringBuilder sb;

        for(int i = 0; i < 10; i++) {
            if(time >= cars[i].start && time <= cars[i].end && num <= cars[i].num) {

                sb = new StringBuilder();

                sb.append(cars[i].name);
                if(cars[i].produceEnd) sb.append("*");
                sb.append(cars[i].type).append(",");

                pq.add(sb.toString());
            }
        }

        sb = new StringBuilder();

        while(!pq.isEmpty()) {
            sb.append(pq.poll());
        }

        if(sb.length() > 0) sb.deleteCharAt(sb.length() - 1);

        if(sb.length() == 0) return "!";

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init();

        String[] cmd = br.readLine().split(",");
        String time = cmd[0];

        int num = Integer.parseInt(cmd[1].trim());

        System.out.println(solution(time, num));
    }

    private static void init() {
        cars = new Car[10];
        cars[0] = new Car("Tuscani", "(Coupe)", true, toMonth("200109"), toMonth("200810"), 2);
        cars[1] = new Car("Porter", "(Truck)", true, toMonth("197702"), toMonth("200405"), 3);
        cars[2] = new Car("Cortina", "(Sedan)", true, toMonth("196801"), toMonth("198004"), 5);
        cars[3] = new Car("Elantra", "(Sedan)", true, toMonth("199010"), toMonth("199512"), 5);
        cars[4] = new Car("Equus", "(Sedan)", true, toMonth("199904"), toMonth("200912"), 5);
        cars[5] = new Car("Grandeur", "(Sedan)", false, toMonth("198607"), toMonth("202305"), 5);
        cars[6] = new Car("Pony", "(Sedan)", true, toMonth("197512"), toMonth("198201"), 5);
        cars[7] = new Car("SantaFe", "(RV)", false, toMonth("200006"), toMonth("202305"), 7);
        cars[8] = new Car("Aerotown", "(Bus)", false, toMonth("199406"), toMonth("202305"), 30);
        cars[9] = new Car("Universe", "(Bus)", false, toMonth("200612"), toMonth("202305"), 45);
    }

    private static int toMonth(String s) {
        return Integer.parseInt(s.substring(0, 4)) * 12 + Integer.parseInt(s.substring(4));
    }
}

class Car {
    String name;
    String type;
    boolean produceEnd;
    int start;
    int end;
    int num;

    public Car(String name, String type, boolean produceEnd, int start, int end, int num) {
        this.name = name;
        this.type = type;
        this.produceEnd = produceEnd;
        this.start = start;
        this.end = end;
        this.num = num;
    }
}