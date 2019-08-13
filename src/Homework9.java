import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

// 文件查找
class Homework9 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String match = scn.next();
        File folder = new File("input/test/case");
        File[] files = folder.listFiles();
        ArrayList<String> ans = new ArrayList<String>();
        for (File file : files) {
            if (!file.isFile()) {
                continue;
            }
            String name = file.getName();
            if (name.toLowerCase().contains(match.toLowerCase())) {
                ans.add(name);
            }
        }
        ans.sort(String::compareTo);
        for (String name : ans) {
            System.out.println(name);
        }
    }
}
