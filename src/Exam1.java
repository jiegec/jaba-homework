import java.util.Scanner;
import java.math.BigInteger;

class Exam1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String a = scn.next();
        String b = scn.next();
        BigInteger aa = new BigInteger(a);
        BigInteger bb = new BigInteger(b);
        if (aa.compareTo(bb) < 0) {
            BigInteger temp = aa;
            aa = bb;
            bb = temp;
        }
        while (bb.compareTo(BigInteger.ZERO) > 0) {
            BigInteger temp = aa.mod(bb);
            aa = bb;
            bb = temp;
        }
        System.out.println(aa.toString());
    }
}
