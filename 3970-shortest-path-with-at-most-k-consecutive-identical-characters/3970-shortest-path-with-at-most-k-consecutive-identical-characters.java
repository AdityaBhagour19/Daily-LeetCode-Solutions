class Solution {
    long[] heap = new long[1 << 16];
    int hs = 0;

    void push(long x) {
        if (hs == heap.length) heap = java.util.Arrays.copyOf(heap, hs << 1);
        int i = hs++;
        while (i > 0) {
            int p = (i - 1) >> 1;
            if (heap[p] <= x) break;
            heap[i] = heap[p];
            i = p;
        }
        heap[i] = x;
    }

    long pop() {
        long top = heap[0];
        long x = heap[--hs];
        int i = 0;
        int half = hs >> 1;
        while (i < half) {
            int c = 2 * i + 1;
            if (c + 1 < hs && heap[c + 1] < heap[c]) c++;
            if (heap[c] >= x) break;
            heap[i] = heap[c];
            i = c;
        }
        if (hs > 0) heap[i] = x;
        return top;
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        int m = edges.length;
        int[] start = new int[n + 1];
        for (int[] e : edges) start[e[0] + 1]++;
        for (int i = 0; i < n; i++) start[i + 1] += start[i];
        int[] pos = new int[n];
        int[] to = new int[m], w = new int[m];
        for (int[] e : edges) {
            int idx = start[e[0]] + pos[e[0]]++;
            to[idx] = e[1];
            w[idx] = e[2];
        }

        char[] lab = labels.toCharArray();
        int S = n * k;
        long[] dist = new long[S];
        java.util.Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        push(0);

        while (hs > 0) {
            long key = pop();
            int st = (int) (key % S);
            long d = key / S;
            if (d > dist[st]) continue;
            int u = st / k;
            if (u == n - 1) return (int) d;
            int r = st - u * k + 1;
            char cu = lab[u];
            for (int e = start[u]; e < start[u + 1]; e++) {
                int v = to[e];
                int nr = lab[v] == cu ? r + 1 : 1;
                if (nr > k) continue;
                int ns = v * k + nr - 1;
                long nd = d + w[e];
                if (nd < dist[ns]) {
                    dist[ns] = nd;
                    push(nd * S + ns);
                }
            }
        }
        return -1;
    }
}