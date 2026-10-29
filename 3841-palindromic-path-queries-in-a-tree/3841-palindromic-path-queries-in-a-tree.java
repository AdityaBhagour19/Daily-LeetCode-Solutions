class Solution {
    int[] bit;
    int n;

    void add(int i, int d) {
        for (; i <= n; i += i & -i) bit[i] ^= d;
    }

    int pre(int i) {
        int r = 0;
        for (; i > 0; i -= i & -i) r ^= bit[i];
        return r;
    }

    public java.util.List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        int[] hd = new int[n], nx = new int[2 * (n - 1)], to = new int[2 * (n - 1)];
        java.util.Arrays.fill(hd, -1);
        int ec = 0;
        for (int[] e : edges) {
            to[ec] = e[1]; nx[ec] = hd[e[0]]; hd[e[0]] = ec++;
            to[ec] = e[0]; nx[ec] = hd[e[1]]; hd[e[1]] = ec++;
        }

        int[] par = new int[n], depth = new int[n], order = new int[n], tin = new int[n];
        int[] size = new int[n], heavy = new int[n], head = new int[n], base = new int[n];
        int[] cur = new int[n];
        for (int i = 0; i < n; i++) cur[i] = s.charAt(i) - 'a';

        int[] stack = new int[n];
        int top = 0, cnt = 0;
        stack[top++] = 0;
        par[0] = -1;
        while (top > 0) {
            int u = stack[--top];
            tin[u] = cnt;
            order[cnt++] = u;
            base[u] = (par[u] >= 0 ? base[par[u]] : 0) ^ (1 << cur[u]);
            for (int e = hd[u]; e != -1; e = nx[e]) {
                int v = to[e];
                if (v == par[u]) continue;
                par[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        java.util.Arrays.fill(heavy, -1);
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] += 1;
            int p = par[u];
            if (p >= 0) {
                size[p] += size[u];
                if (heavy[p] == -1 || size[u] > size[heavy[p]]) heavy[p] = u;
            }
        }
        head[0] = 0;
        for (int i = 0; i < n; i++) {
            int u = order[i];
            for (int e = hd[u]; e != -1; e = nx[e]) {
                int v = to[e];
                if (v == par[u]) continue;
                head[v] = (v == heavy[u]) ? head[u] : v;
            }
        }

        bit = new int[n + 2];
        java.util.List<Boolean> ans = new java.util.ArrayList<>();

        for (String q : queries) {
            int len = q.length();
            if (q.charAt(0) == 'u') {
                int p = 7, u = 0;
                while (q.charAt(p) != ' ') u = u * 10 + (q.charAt(p++) - '0');
                int c = q.charAt(p + 1) - 'a';
                int d = (1 << cur[u]) ^ (1 << c);
                if (d != 0) {
                    cur[u] = c;
                    add(tin[u] + 1, d);
                    add(tin[u] + size[u] + 1, d);
                }
            } else {
                int p = 6, u = 0, v = 0;
                while (q.charAt(p) != ' ') u = u * 10 + (q.charAt(p++) - '0');
                for (p++; p < len; p++) v = v * 10 + (q.charAt(p) - '0');

                int a = u, b = v;
                while (head[a] != head[b]) {
                    if (depth[head[a]] > depth[head[b]]) a = par[head[a]];
                    else b = par[head[b]];
                }
                int lca = depth[a] < depth[b] ? a : b;

                int m = base[u] ^ pre(tin[u] + 1) ^ base[v] ^ pre(tin[v] + 1) ^ (1 << cur[lca]);
                ans.add((m & (m - 1)) == 0);
            }
        }
        return ans;
    }
}