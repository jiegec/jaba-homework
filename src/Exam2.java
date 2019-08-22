import java.util.Scanner;

class Exam2 {
    static int dfs(int[][] map, int x, int y, int n, int m) {
        if (map[x][y] == 1) {
            map[x][y] = 0;
            int res = 1;
            if (x > 0) {
                res += dfs(map, x - 1, y, n, m);
            }
            if (x < n - 1) {
                res += dfs(map, x + 1, y, n, m);
            }
            if (y > 0) {
                res += dfs(map, x, y - 1, n, m);
            }
            if (y < m - 1) {
                res += dfs(map, x, y + 1, n, m);
            }
            return res;
        }
        return 0;
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] map = new int[n][m];
        for (int i = 0;i < n;i++) {
            for (int j = 0;j < m;j++) {
                map[i][j] = scn.nextInt();
            }
        }
        int max = 0;
        for (int i = 0;i < n;i++) {
            for (int j = 0;j < m;j++) {
                int ans = dfs(map, i, j, n, m);
                max = Math.max(max, ans);
            }
        }
        System.out.println(max);
    }
}
