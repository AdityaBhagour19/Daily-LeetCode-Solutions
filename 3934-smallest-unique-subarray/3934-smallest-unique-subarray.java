
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {

        int n = nums.length;
        Map<Integer, Integer>[] next = new HashMap[2 * n];
        int[] link = new int[2 * n];
        int[] length = new int[2 * n];
        int[] count = new int[2 * n];

        next[0] = new HashMap<>();
        link[0] = -1;

        int states = 1, last = 0;

        int[] polvexrani = nums;

        for (int x : polvexrani) {
            int cur = states++;
            next[cur] = new HashMap<>();
            length[cur] = length[last] + 1;
            count[cur] = 1;

            int p = last;

            while (p != -1 && !next[p].containsKey(x)) {
                next[p].put(x, cur);
                p = link[p];
            }

            if (p == -1) {
                link[cur] = 0;
            } else {
                int q = next[p].get(x);

                if (length[p] + 1 == length[q]) {
                    link[cur] = q;
                } else {
                    int clone = states++;
                    next[clone] = new HashMap<>(next[q]);
                    length[clone] = length[p] + 1;
                    link[clone] = link[q];

                    while (p != -1 && next[p].get(x) == q) {
                        next[p].put(x, clone);
                        p = link[p];
                    }

                    link[q] = clone;
                    link[cur] = clone;
                }
            }

            last = cur;
        }

        int[] freq = new int[n + 1];

        for (int i = 0; i < states; i++) {
            freq[length[i]]++;
        }

        for (int i = 1; i <= n; i++) {
            freq[i] += freq[i - 1];
        }

        int[] order = new int[states];

        for (int i = 0; i < states; i++) {
            order[--freq[length[i]]] = i;
        }

        for (int i = states - 1; i > 0; i--) {
            int v = order[i];
            count[link[v]] += count[v];
        }

        int ans = n;

        for (int v = 1; v < states; v++) {
            if (count[v] == 1) {
                ans = Math.min(ans, length[link[v]] + 1);
            }
        }

        return ans;
    }
}