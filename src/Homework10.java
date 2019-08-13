import java.util.Scanner;
import java.lang.Integer;

class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        Integer[] data = new Integer[n];
        int a, b, c, num;
        while (scn.hasNext()) {
            String op = scn.next();
            try {
                switch(op) {
                    case "=":
                        a = scn.nextInt();
                        b = scn.nextInt();
                        num = scn.nextInt();
                        for (int i = a;i < b;i++) {
                            data[i] = num;
                        }
                        break;
                    case "+":
                        a = scn.nextInt();
                        b = scn.nextInt();
                        c = scn.nextInt();
                        data[c] = data[a] + data[b];
                        break;
                    case "-":
                        a = scn.nextInt();
                        b = scn.nextInt();
                        c = scn.nextInt();
                        data[c] = data[a] - data[b];
                        break;
                    case "*":
                        a = scn.nextInt();
                        b = scn.nextInt();
                        c = scn.nextInt();
                        data[c] = data[a] * data[b];
                        break;
                    case "/":
                        a = scn.nextInt();
                        b = scn.nextInt();
                        c = scn.nextInt();
                        data[c] = data[a] / data[b];
                        break;
                    case "?":
                        a = scn.nextInt();
                        if (data[a] != null) {
                            System.out.println(data[a]);
                        } else {
                            System.out.println("null");
                        }
                        break;
                    default:
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Illegal Address");
            } catch (NullPointerException e) {
                System.out.println("Null Number");
            } catch (ArithmeticException e) {
                System.out.println("Divided By Zero");
            }
        }
    }
}
