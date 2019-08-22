import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = scn.nextInt();
        int[] nums = new int[n];
        for (int i = 0;i < n;i++) {
            nums[i] = scn.nextInt();
        }
        Arrays.sort(nums);
        for (int i = 0;i < n - 1;i++) {
            if (nums[i] == nums[i+1] && nums[i] > 1) {
                System.out.println('N');
                return;
            }
        }

        int[] smallPrimes = {2, 3, 5, 7, 11};
        for (int i = 0;i < smallPrimes.length;i++) {
            int count = 0;
            for (int j = 0;j < n;j++) {
                if (nums[j] % smallPrimes[i] == 0) {
                    count ++;
                    if (count > 1) {
                        System.out.println('N');
                        return;
                    }
                }
            }
        }

        int max = 300010 / 10;
        boolean[] flag = new boolean[max];
        int[] primes = new int[30000];
        boolean[] primeFound = new boolean[30000];
        int primeNum = 0;
        for (int i = 2;i < max;i++) {
            if (flag[i])
                continue;
            primes[primeNum++] = i;
            // about sqrt(300010)
            if (i < 600) {
                for (int j = i * i;j < max; j += i) {
                    flag[j] = true;
                }
            }
        }
        //System.out.println(primeNum);

        for (int i = 0;i < n;i++) {
            for (int j = 0;j < primeNum; j++) {
                if (nums[i] % primes[j] == 0) {
                    if (primeFound[j]) {
                        System.out.println('N');
                        return;
                    }
                    primeFound[j] = true;
                }
                if (nums[i] < primes[j]) {
                    break;
                }
            }
        }
        System.out.println('Y');
    }
}
