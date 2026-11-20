class Solution {
    public int maxSelectedElements(int[] nums) {
        int max = 0, min = Integer.MAX_VALUE;
        for (int v : nums) {
            if (v > max) max = v;
            if (v < min) min = v;
        }
        byte[] cnt = new byte[max + 1];
        for (int v : nums) if (cnt[v] < 2) cnt[v]++;

        int a = 0, b = 0, ans = 1;
        for (int x = min; x <= max; x++) {
            int c = cnt[x];
            if (c == 0) {
                a = b;
                b = 0;
            } else if (c == 1) {
                int left = a + 1, up = b + 1;
                a = left;
                b = up;
                if (up > ans) ans = up;
                if (left > ans) ans = left;
            } else {
                int left = a + 1;
                a = left;
                b = left + 1;
                if (b > ans) ans = b;
            }
        }
        return ans;
    }
}