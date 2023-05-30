package Softeer.Test_2023;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class test04 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringBuilder sb;

        Stack<String> operator = new Stack<>();

        String[] cmd = input.split(" ");
        for (int i = 1; i < cmd.length; i += 2) {
            operator.add(cmd[i]);
        }

        if (operator.isEmpty()) System.out.println(start(cmd[0]) + end(cmd[0]));

        int idx = 0;

        while (!operator.isEmpty() && idx < cmd.length) {

            String op = operator.pop();

            if (op.equals(">")) {

            } else if (op.equals("+")) {

                sb = new StringBuilder();
                sb.append(start(cmd[idx])).append(end(cmd[idx])).append("\n");
                idx += 2;
                sb.append(start(cmd[idx])).append(end(cmd[idx])).append("\n");

                System.out.println(sb);

            } else if (op.equals("*")) {

                sb = new StringBuilder();

                for(int i = 0; i < Integer.parseInt(cmd[idx + 2]); i++) {
                    sb.append(start(cmd[idx])).append(end(cmd[idx])).append("\n");
                }

                System.out.println(sb);

            } else {
                System.out.println("\"" + op + "\"" + " is invalid operator");
                break;
            }
        }
    }

    private static String start(String s) {
        return "<" + s + ">";
    }

    private static String end(String s) {
        return "</" + s + ">";
    }
}

