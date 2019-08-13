import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 匹配日期
class Homework13 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String data = scn.nextLine();
        Pattern p = Pattern.compile("(199[0-9]|200[0-9]|201[0-7])-((01|03|05|07|08|10|12)-(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)-(0[1-9]|[1-2][0-9]|30)|02-(0[1-9]|1[0-9]|2[0-8]))");
        Matcher m = p.matcher(data);
        boolean flag = true;
        while (m.find()) {
            System.out.println(m.group());
            m = m.region(m.start()+1, data.length());
            flag = false;
        }
        if (flag) {
            System.out.println("NotMatch");
        }
    }
}
