import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Integer;

// 城市规划
class UnionFind {
    private int[] parent;
    UnionFind(int num) {
        parent = new int[num];
        for (int i = 0;i < num;i++) {
            parent[i] = i;
        }
    }

    int parent(int num) {
        ArrayList<Integer> history = new ArrayList<>();
        int cur = num;
        while (cur != parent[cur]) {
            history.add(cur);
            cur = parent[cur];
        }
        for (int n : history) {
            parent[n] = cur;
        }
        return cur;
    }

    void link(int a, int b) {
        int pa = parent(a);
        int pb = parent(b);
        parent[pa] = pb;
    }
}

class Homework12 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        UnionFind uf = new UnionFind(n);
        for (int i = 0;i < n - 1;i++) {
            int l = scn.nextInt();
            int r = scn.nextInt();
            uf.link(l - 1, r - 1);
        }

        int p = uf.parent(0);
        boolean flag = true;
        for (int i = 1;i < n;i++) {
            if (uf.parent(i) != p) {
                flag = false;
                break;
            }
        }
        System.out.println(flag ? "YES" : "NO");
    }
}
