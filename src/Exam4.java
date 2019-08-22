import java.util.*;
import java.lang.Integer;

class Tuple implements Comparable {
    int a;
    int b;
    int c;

    Tuple(int aa, int bb, int cc) {
        a = aa;
        b = bb;
        c = cc;
    }

    @Override
    public int compareTo(Object o) {
        Tuple oo = (Tuple) o;
        if (a > oo.a)
            return 1;
        else if (a < oo.a)
            return -1;
        else if (b > oo.b)
            return 1;
        else if (b < oo.b)
            return -1;
        else if (c > oo.c)
            return 1;
        else if (c < oo.c)
            return -1;
        return 0;
    }
}

class Exam4 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        List<Integer> data = new ArrayList<>();
        Set<Tuple> ans = new TreeSet<>();
        for (int i = 0;i < n;i++) {
            data.add(scn.nextInt());
        }
        Collections.sort(data);
        Set<Integer> nums = new HashSet<>(data);
        for (int i = 0;i < data.size();i++) {
            for (int j = i + 1;j < data.size();j++) {
                int target = 0 - data.get(i) - data.get(j);
                if (!nums.contains(target)) {
                    continue;
                }
                for (int k = j + 1;k < data.size();k++) {
                    if (data.get(k) == target) {
                        ans.add(new Tuple(data.get(i), data.get(j), target));
                        break;
                    }
                }
            }
        }
        for (Tuple t : ans) {
            System.out.print(t.a);
            System.out.print(' ');
            System.out.print(t.b);
            System.out.print(' ');
            System.out.print(t.c);
            System.out.println();
        }
    }
}
