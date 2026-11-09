import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int levels = 1;
        int sz = (k + 63) >> 6;
        int[] lens = new int[4];
        lens[0] = sz;
        while (sz > 1) {
            sz = (sz + 63) >> 6;
            lens[levels++] = sz;
        }
        long[][] a = new long[levels][];
        for (int l = 0; l < levels; l++) a[l] = new long[lens[l]];
        for (int s = 0; s < k; s++) {
            int x = s;
            for (int l = 0; l < levels; l++) {
                a[l][x >> 6] |= 1L << (x & 63);
                x >>= 6;
            }
        }

        long[] heap = new long[k];
        int hs = 0;
        int[] cnt = new int[k];
        int n = arrival.length;

        for (int i = 0; i < n; i++) {
            int t = arrival[i];
            while (hs > 0 && (heap[0] >> 17) <= t) {
                int id = (int) (heap[0] & 0x1FFFF);
                long x = heap[--hs];
                int p = 0, half = hs >> 1;
                while (p < half) {
                    int c = 2 * p + 1;
                    if (c + 1 < hs && heap[c + 1] < heap[c]) c++;
                    if (heap[c] >= x) break;
                    heap[p] = heap[c];
                    p = c;
                }
                if (hs > 0) heap[p] = x;
                int y = id;
                for (int l = 0; l < levels; l++) {
                    a[l][y >> 6] |= 1L << (y & 63);
                    y >>= 6;
                }
            }

            int s = next(a, levels, i % k);
            if (s < 0) s = next(a, levels, 0);
            if (s < 0) continue;

            int y = s;
            for (int l = 0; l < levels; l++) {
                a[l][y >> 6] &= ~(1L << (y & 63));
                if (a[l][y >> 6] != 0) break;
                y >>= 6;
            }
            cnt[s]++;

            long v = ((long) (t + load[i]) << 17) | s;
            int p = hs++;
            while (p > 0) {
                int par = (p - 1) >> 1;
                if (heap[par] <= v) break;
                heap[p] = heap[par];
                p = par;
            }
            heap[p] = v;
        }

        int best = 0;
        for (int c : cnt) if (c > best) best = c;
        List<Integer> res = new ArrayList<>();
        for (int s = 0; s < k; s++) if (cnt[s] == best) res.add(s);
        return res;
    }

    private static int next(long[][] a, int levels, int i) {
        for (int l = 0; l < levels; l++) {
            int w = i >> 6;
            if (w >= a[l].length) return -1;
            long bits = a[l][w] & (-1L << (i & 63));
            if (bits != 0) {
                int idx = (w << 6) + Long.numberOfTrailingZeros(bits);
                for (int d = l - 1; d >= 0; d--) idx = (idx << 6) + Long.numberOfTrailingZeros(a[d][idx]);
                return idx;
            }
            i = w + 1;
        }
        return -1;
    }
}