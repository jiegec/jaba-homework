import java.util.Scanner;

// 最大连续子序列
class Homework1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int count = scn.nextInt();
        int[] data = new int[count];
        for (int i = 0;i < count;i++) {
            data[i] = scn.nextInt();
        }

        int sum = 0;
        int maxSum = 0;

        for (int i = 0;i < count;i++) {
            sum += data[i];
            if (sum < 0) {
                sum = 0;
            }

            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        System.out.println(maxSum);
    }
}
