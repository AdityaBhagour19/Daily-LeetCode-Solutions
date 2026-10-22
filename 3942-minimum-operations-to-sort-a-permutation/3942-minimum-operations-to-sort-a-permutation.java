class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 2) return (n == 1 || nums[0] == 0) ? 0 : 1;

        int s = nums[0];
        int e = s;
        boolean inc = true;
        for (int i = 1; i < n; i++) {
            if (++e == n) e = 0;
            if (nums[i] != e) { inc = false; break; }
        }
        if (inc) return s == 0 ? 0 : Math.min(n - s, s + 2);

        e = s;
        boolean dec = true;
        for (int i = 1; i < n; i++) {
            if (--e < 0) e = n - 1;
            if (nums[i] != e) { dec = false; break; }
        }
        if (dec) {
            int u = (s + 1) % n;
            return Math.min(u + 1, 1 + (n - u) % n);
        }
        return -1;
    }
}