class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length, B = budget;

        // free copies: g = (#items whose factor is a multiple of mine) - myself
        int[] cntF = new int[1501];
        for (int[] it : items) cntF[it[0]]++;
        int[] mult = new int[1501];
        for (int f = 1; f <= 1500; f++) {
            int s = 0;
            for (int m = f; m <= 1500; m += f) s += cntF[m];
            mult[f] = s;
        }

        // counting sort by price
        int[] start = new int[1502];
        for (int[] it : items) start[it[1] + 1]++;
        for (int p = 1; p <= 1501; p++) start[p] += start[p - 1];
        int[] order = new int[n];
        for (int i = 0; i < n; i++) order[start[items[i][1]]++] = i;

        final int NEG = -1_000_000_000;
        int[] ex = new int[B + 1];            // ex[x] = best value spending exactly x on earlier (pricier) items
        java.util.Arrays.fill(ex, NEG);
        ex[0] = 0;

        int ans = 0;
        for (int k = n - 1; k >= 0; k--) {    // most expensive first
            int[] it = items[order[k]];
            int p = it[1], v = mult[it[0]];   // 1 + (mult - 1)
            int rem = B - p;
            if (rem < 0) continue;
            for (int x = 0; x <= rem; x++) {
                int cand = ex[x] + v + (rem - x) / p;
                if (cand > ans) ans = cand;
            }
            for (int x = B; x >= p; x--) {
                int c = ex[x - p] + v;
                if (c > ex[x]) ex[x] = c;
            }
        }
        return ans;
    }
}