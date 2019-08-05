import java.util.Scanner;

// 翻转字符串中的单词
class Homework3 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String d = scn.nextLine();
        int curIndex = -1;
        for (int i = 0; i < d.length(); i++) {
            if (d.charAt(i) != '\'' && !((d.charAt(i) >= 'a' && d.charAt(i) <= 'z') || (d.charAt(i) >= 'A' && d.charAt(i) <= 'Z'))) {
                System.out.print(new StringBuffer(d.substring(curIndex + 1, i)).reverse());
                System.out.print(d.charAt(i));
                curIndex = i;
            }
        }
        System.out.print(new StringBuffer(d.substring(curIndex + 1, d.length())).reverse());
    }
}
