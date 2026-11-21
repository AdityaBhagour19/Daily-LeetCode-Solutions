class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        char[] c = s.toCharArray();

        int[] start = new int[n + 2];
        for (int i = 1; i < n; i++) start[parent[i] + 2]++;
        for (int i = 2; i <= n + 1; i++) start[i] += start[i - 1];
        int[] adj = new int[n];
        for (int i = 1; i < n; i++) adj[start[parent[i] + 1]++] = i;

        int[] last = new int[26];             
        java.util.Arrays.fill(last, -1);
        int[] np = new int[n], order = new int[n], saved = new int[n];
        int[] stack = new int[n], ptr = new int[n];
        for (int v = 0; v < n; v++) ptr[v] = start[v];

        int sp = 0, k = 0;
        np[0] = -1;
        saved[0] = last[c[0] - 'a'];
        last[c[0] - 'a'] = 0;
        order[k++] = 0;
        stack[sp++] = 0;
        while (sp > 0) {
            int v = stack[sp - 1];
            if (ptr[v] < start[v + 1]) {
                int u = adj[ptr[v]++];
                int ch = c[u] - 'a';
                np[u] = last[ch] >= 0 ? last[ch] : v;  
                saved[u] = last[ch];
                last[ch] = u;
                order[k++] = u;
                stack[sp++] = u;
            } else {
                sp--;
                last[c[v] - 'a'] = saved[v];          
            }
        }

        int[] ans = new int[n];
        java.util.Arrays.fill(ans, 1);
        for (int i = n - 1; i > 0; i--) {
            int v = order[i];
            ans[np[v]] += ans[v];
        }
        return ans;
    }
}