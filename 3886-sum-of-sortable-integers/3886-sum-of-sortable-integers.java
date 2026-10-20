class Solution {
    private boolean check(int[] a, int k) {
        int n = a.length, prev = 0;

        for (int i = 0; i < n; i += k) {
            int first = a[i], last = first;
            int min = first, max = first, desc = 0;

            for (int j = i + 1, end = i + k; j < end; j++) {
                int x = a[j];

                if (last > x && ++desc > 1) return false;

                if (x < min) min = x;
                if (x > max) max = x;
                last = x;
            }

            if (last > first && ++desc > 1) return false;

            if (prev > min) return false;
            prev = max;
        }
        return true;
    }

    public int sortableIntegers(int[] nums) {
        int n = nums.length, ans = 0;

        for (int k = 1; k * k <= n; k++) {
            if (n % k != 0) continue;

            if (check(nums, k)) ans += k;

            int other = n / k;
            if (other != k && check(nums, other)) ans += other;
        }
        return ans;
    }
}