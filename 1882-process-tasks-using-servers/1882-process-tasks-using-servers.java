class Solution {
    static int push(long[] h, int n, long v) { int i = n, p; for (; i > 0 && h[p = (i - 1) >> 1] > v; i = p) h[i] = h[p]; h[i] = v; return n + 1; }
    static long pop(long[] h, int n) { long top = h[0], v = h[--n]; int i = 0, c;
        for (; (c = 2 * i + 1) < n; i = c) { if (c + 1 < n && h[c + 1] < h[c]) c++; if (h[c] >= v) break; h[i] = h[c]; } h[i] = v; return top; }
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length, m = tasks.length, fn = 0, bn = 0, ans[] = new int[m]; long t = 0, free[] = new long[n], busy[] = new long[n];
        for (int i = 0; i < n; i++) fn = push(free, fn, (long) servers[i] << 18 | i);
        for (int i = 0; i < m; i++) {
            t = Math.max(t, i);
            while (bn > 0 && (fn == 0 || busy[0] >> 18 <= t)) { t = Math.max(t, busy[0] >> 18); int s = (int) (pop(busy, bn--) & 262143); fn = push(free, fn, (long) servers[s] << 18 | s); }
            int s = (int) (pop(free, fn--) & 262143); ans[i] = s; bn = push(busy, bn, (t + tasks[i]) << 18 | s);
        }
        return ans;
    }
}