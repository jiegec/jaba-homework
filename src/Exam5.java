import java.awt.*;
import java.util.*;
import java.lang.Integer;
import java.util.List;

class Pair {
    int x;
    int y;

    Pair(int xx, int yy) {
        x = xx;
        y = yy;
    }
}

class Exam5 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        List<Pair> points = new ArrayList<>();
        List<Pair> ans = new ArrayList<>();
        for (int i = 0;i < n;i++) {
            int x = scn.nextInt();
            int y = scn.nextInt();
            points.add(new Pair(x, y));
        }

        points.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.x < o2.x)
                    return -1;
                else if (o1.x > o2.x)
                    return 1;
                return 0;
            }
        });

        PriorityQueue<Integer> y = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2)
                    return 1;
                else if (o1 > o2)
                    return -1;
                return 0;
            }
        });
        for (int i = points.size() - 1;i >= 0;i --) {
            Pair p = points.get(i);
            if (y.size() == 0 || y.peek() < p.y) {
                ans.add(p);
            }
            y.offer(p.y);
        }

        Collections.reverse(ans);
        for (Pair p : ans) {
            System.out.print(p.x);
            System.out.print(' ');
            System.out.print(p.y);
            System.out.println();
        }
    }
}
