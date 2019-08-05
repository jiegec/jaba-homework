import java.util.Scanner;

// 数独
class Homework4 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = n * n;
        int[][] matrix = new int[m][m];
        for (int i = 0;i < m;i++) {
            for (int j = 0;j < m;j++) {
                matrix[i][j] = scn.nextInt();
            }
        }
        boolean good = true;
        // row
        for (int i = 0;i < m;i++) {
            boolean[] flag = new boolean[m+1];
            for (int j = 0;j < m;j++) {
                if (flag[matrix[i][j]]) {
                    good = false;
                }
                flag[matrix[i][j]] = true;
            }
        }
        // col
        for (int i = 0;i < m;i++) {
            boolean[] flag = new boolean[m+1];
            for (int j = 0;j < m;j++) {
                if (flag[matrix[j][i]]) {
                    good = false;
                }
                flag[matrix[j][i]] = true;
            }
        }
        // submatrix
        for (int i = 0;i < n;i++) {
            for (int j = 0;j < n;j++) {
                boolean[] flag = new boolean[m+1];
                for (int ii = 0;ii < n;ii ++) {
                    for (int jj = 0;jj < n;jj ++) {
                        if (flag[matrix[i * n + ii][j * n + jj]]) {
                            good = false;
                        }
                        flag[matrix[i * n + ii][j * n + jj]] = true;
                    }
                }
            }
        }

        System.out.println(good ? "yes" : "no");
    }
}
