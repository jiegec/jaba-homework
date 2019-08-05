import java.util.Scanner;

// 大整数相加
class Homework2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String a = scn.next();
        String b = scn.next();
        if (a.equals("0") && b.equals("0")) {
            System.out.println('0');
            return;
        }
        int maxLength = Math.max(a.length(), b.length()) + 1;
        int[] numA = new int[maxLength];
        for (int i = 0;i < a.length();i++) {
            numA[a.length() - i - 1] = Integer.parseInt(a.substring(i, i+1));
        }
        for (int i = 0;i < b.length();i++) {
            numA[b.length() - i - 1] += Integer.parseInt(b.substring(i, i+1));
        }
        for (int i = 0;i < maxLength - 1;i++) {
            numA[i+1] += numA[i] / 10;
            numA[i] %= 10;
        }

        boolean flag = false;
        for (int i = maxLength-1;i >= 0;i--) {
            if (numA[i] != 0) {
                flag = true;
            }
            if (flag) {
                System.out.print((char)(numA[i] + '0'));
            }
        }
        System.out.println();
    }
}
