import java.util.Scanner;

class Exam7 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        long a = scn.nextLong();
        long b = scn.nextLong();
        int[] cost = new int[n];
        long[][] dp = new long[n][n];
        int[] sum = new int[n+1];
        for(int i = 0;i < n;i++) {
            cost[i] = scn.nextInt();
            sum[i+1] = cost[i];
            sum[i+1] += sum[i];
        }

        for (int len = 1;len <= n;len++) {
            for (int i = 0;i < n - len + 1;i++) {
                if (len == 1) {
                    dp[i][i] = 0;
                    continue;
                }

                int j = i + len - 1;
                dp[i][j] = java.lang.Long.MAX_VALUE;
                for (int k = i;k < j;k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + a * (sum[k+1] - sum[i]) + b * (sum[j+1] - sum[k + 1]));
                }
                /*
                System.out.print(i);
                System.out.print(' ');
                System.out.print(j);
                System.out.print(' ');
                System.out.println(dp[i][j]);
                */
            }
        }

        System.out.println(dp[0][n-1]);
    }
}
