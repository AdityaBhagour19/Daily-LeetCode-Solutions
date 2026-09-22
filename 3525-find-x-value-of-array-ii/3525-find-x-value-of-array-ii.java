class Solution {
    private int n, k;
    private int[] prod;   // size 2n, node -> product mod k
    private int[] cnt;    // size 2n*k, node u's k values live at cnt[u*k .. u*k+k-1]

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        n = nums.length;
        this.k = k;
        prod = new int[2 * n];
        cnt = new int[2 * n * k];

        for (int i = 0; i < n; i++) {
            int v = nums[i] % k;
            prod[n + i] = v;
            cnt[(n + i) * k + v] = 1;
        }
        for (int u = n - 1; u >= 1; u--) pushUp(u);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0], val = queries[i][1], start = queries[i][2], x = queries[i][3];
            update(idx, val);
            ans[i] = query(start, n - 1, x);
        }
        return ans;
    }

    // merges node "left" and node "right" into node u's slot
    private void pushUp(int u) {
        int l = u << 1, r = l | 1;
        int p = (prod[l] * prod[r]) % k;
        int lo = l * k, ro = r * k, uo = u * k;
        int[] tmp = new int[k];
        for (int i = 0; i < k; i++) tmp[i] = cnt[lo + i];
        for (int i = 0; i < k; i++) {
            int c = cnt[ro + i];
            if (c != 0) tmp[(prod[l] * i) % k] += c;
        }
        prod[u] = p;
        System.arraycopy(tmp, 0, cnt, uo, k);
    }

    private void update(int idx, int val) {
        int u = n + idx, v = val % k;
        prod[u] = v;
        int off = u * k;
        for (int i = 0; i < k; i++) cnt[off + i] = 0;
        cnt[off + v] = 1;
        for (u >>= 1; u >= 1; u >>= 1) pushUp(u);
    }

    // merge (prodA,cntA) with node b's data -> result written into out (length k+1: [0]=prod, [1..k]=cnt)
    private void merge(int prodA, int[] cntA, int prodB, int[] cntB, int[] out) {
        out[0] = (prodA * prodB) % k;
        for (int i = 0; i < k; i++) out[1 + i] = cntA[i];
        for (int i = 0; i < k; i++) {
            int c = cntB[i];
            if (c != 0) out[1 + (prodA * i) % k] += c;
        }
    }

    private int query(int l, int r, int x) {
        l += n; r += n + 1;
        int prodL = 1, prodR = 1;
        int[] cntL = new int[k], cntR = new int[k], tmp = new int[k + 1], nodeCnt = new int[k];

        while (l < r) {
            if ((l & 1) == 1) {
                System.arraycopy(cnt, l * k, nodeCnt, 0, k);
                merge(prodL, cntL, prod[l], nodeCnt, tmp);
                prodL = tmp[0];
                System.arraycopy(tmp, 1, cntL, 0, k);
                l++;
            }
            if ((r & 1) == 1) {
                r--;
                System.arraycopy(cnt, r * k, nodeCnt, 0, k);
                merge(prod[r], nodeCnt, prodR, cntR, tmp);
                prodR = tmp[0];
                System.arraycopy(tmp, 1, cntR, 0, k);
            }
            l >>= 1; r >>= 1;
        }
        merge(prodL, cntL, prodR, cntR, tmp);
        return tmp[1 + x];
    }
}