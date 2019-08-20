import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Homework14 {
    public static void main(String[] args) {
        try {
            // URL.setURLStreamHandlerFactory(TUProxy::new);
            Scanner scn = new Scanner(System.in);
            String url = scn.nextLine();
            String keyword = scn.nextLine();
            URL target = new URL(url);
            URLConnection conn = target.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            Pattern p = Pattern.compile("([%0-9A-Za-z.@]+\\.com)");
            Set<String> names = new HashSet<>();
            while ((line = in.readLine()) != null) {
                // handle line
                line = line.toLowerCase();
                StringBuilder escaped = new StringBuilder();
                for (int i = 0;i < line.length();i++) {
                    if (i + 2 < line.length() && line.charAt(i) == '%') {
                        boolean flag = false;
                        char ch = 0;
                        if (line.charAt(i+1) >= '0' && line.charAt(i+1) <= '9') {
                            ch += line.charAt(i+1) - '0';
                            flag = true;
                        } else if (line.charAt(i+1) >= 'a' && line.charAt(i+1) <= 'f') {
                            ch += line.charAt(i+1) - 'a' + 10;
                            flag = true;
                        }

                        if (!flag) {
                            escaped.append(line.charAt(i));
                            continue;
                        }

                        ch = (char)(ch * 16);
                        flag = false;
                        if (line.charAt(i+2) >= '0' && line.charAt(i+2) <= '9') {
                            ch += line.charAt(i+2) - '0';
                            flag = true;
                        } else if (line.charAt(i+2) >= 'a' && line.charAt(i+2) <= 'f') {
                            ch += line.charAt(i+2) - 'a' + 10;
                            flag = true;
                        }

                        if (!flag) {
                            escaped.append(line.charAt(i));
                            continue;
                        }
                        escaped.append(ch);
                        i += 2;
                        continue;
                    }
                    escaped.append(line.charAt(i));
                }
                String escapedString = escaped.toString();
                Matcher m = p.matcher(escapedString);
                while (m.find()) {
                    String match = m.group(1);
                    while (match.length() > 0 && (match.charAt(0) == '@' || match.charAt(0) == '%' || match.charAt(0) == '.')) {
                        match = match.substring(1);
                    }
                    if (match.contains(keyword)) {
                        names.add(match);
                    }
                }
            }

            ArrayList<String> list = new ArrayList<>(names);
            list.sort(String::compareTo);
            for (String name : list) {
                System.out.println(name);
            }
        } catch (IOException e) {
            // should never happen
            e.printStackTrace();
        }
    }
}
