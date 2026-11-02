class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;
        int k = n >> 1;
        int lo = 0, hi = n - 1;

        while (lo < hi) {
            int pivot = nums[(lo + hi) >>> 1];
            int lt = lo, i = lo, gt = hi;
            while (i <= gt) {
                int v = nums[i];
                if (v < pivot) {
                    nums[i++] = nums[lt];
                    nums[lt++] = v;
                } else if (v > pivot) {
                    nums[i] = nums[gt];
                    nums[gt--] = v;
                } else {
                    i++;
                }
            }
            if (k < lt) hi = lt - 1;
            else if (k > gt) lo = gt + 1;
            else break;
        }

        int median = nums[k];
        long moves = 0;
        for (int x : nums) moves += Math.abs(x - median);
        return (int) moves;
    }
}