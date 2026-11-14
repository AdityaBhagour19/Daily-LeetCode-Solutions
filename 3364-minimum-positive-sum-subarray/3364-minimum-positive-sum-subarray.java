import java.util.*;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] p = new int[n + 1];
        for (int i = 0; i < n; i++) p[i + 1] = p[i] + nums.get(i);

        int best = Integer.MAX_VALUE;
        for (int j = l; j <= n; j++) {
            int hi = Math.min(r, j);
            for (int len = l; len <= hi; len++) {
                int s = p[j] - p[j - len];
                if (s > 0 && s < best) best = s;
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }
}