import java.util.Scanner;

// 文本抽取
class Homework11 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        System.out.println(input.replaceAll("&[#a-zA-Z0-9]+;|</?[! a-zA-Z0-9-]+( [a-zA-Z0-9]+=\"[a-zA-Z0-9: -;]*\")*/?>", ""));
    }
}
