import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.math.BigInteger;

class Exam3 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        long n = scn.nextInt();
        BigInteger m = new BigInteger(scn.next());
        m = m.subtract(BigInteger.ONE);
        BigInteger fact = BigInteger.ONE;
        List<Long> num = new ArrayList<>();
        for (long i = 1;i <= n;i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
            num.add(i);
        }
        for (long i = 1;i <= n;i++) {
            fact = fact.divide(BigInteger.valueOf(n - i + 1));
            int index = m.divide(fact).intValue();
            m = m.mod(fact);
            System.out.print(num.get(index));
            System.out.print(' ');
            num.remove(index);
        }
    }
}
