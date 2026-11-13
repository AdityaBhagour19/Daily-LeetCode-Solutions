class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] val = new int[n];
        int[] cnt = new int[n];
        int head = 0, tail = 0;

        long sumMax = 0, sumB = 0, ans = 0;
        int l = 0;
        for (int r = 0; r < n; r++) {
            int x = nums[n - 1 - r];
            int c = 1;
            while (tail > head && val[tail - 1] <= x) {
                tail--;
                sumMax -= (long) val[tail] * cnt[tail];
                c += cnt[tail];
            }
            val[tail] = x;
            cnt[tail] = c;
            tail++;
            sumMax += (long) x * c;
            sumB += x;

            while (sumMax - sumB > k) {
                sumMax -= val[head];
                if (--cnt[head] == 0) head++;
                sumB -= nums[n - 1 - l];
                l++;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}