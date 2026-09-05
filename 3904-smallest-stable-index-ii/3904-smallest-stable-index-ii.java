class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length, max=Integer.MIN_VALUE, min[]= new int[n];

        for(int i = n - 1; i>=0;i--) min[i] = (i == n - 1) ? nums[i] : Math.min(nums[i], min[i + 1]);
        for (int i = 0; i < n; i++){
            if ((max = Math.max(max, nums[i])) - min[i] <= k) return i;
        }
        return -1;
    }
}