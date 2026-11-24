class Solution {
    public int maxLength(int[] nums) {
        int[] mask = {0, 0, 1, 2, 1, 4, 3, 8, 1, 2, 5};
        int n = nums.length, ans = 2;
        for (int i = 0; i < n; i++) {
            int used = 0;
            for (int j = i; j < n; j++) {
                int m = mask[nums[j]];
                if ((used & m) != 0) break;
                used |= m;
                if (j - i + 1 > ans) ans = j - i + 1;
            }
        }
        return ans;
    }
}