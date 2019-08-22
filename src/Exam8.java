import java.util.Scanner;

class Exam8 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int s = scn.nextInt();
        long p = scn.nextInt();
        long dp[][][] = new long[n+1][m+1][s+1];
        for (int i = 0;i <= n;i++) {
            dp[i][0][0] = 1;
        }
        for (int i = 1;i <= n;i++) {
            for (int j = 1;j <= m;j++) {
                for (int k = 1;k <= s;k++) {
                    dp[i][j][k] = (i * dp[i-1][j-1][k-1] + k * dp[i][j-1][k]) % p;
                }
            }
        }
        System.out.println(dp[n][m][s]);
    }
}
