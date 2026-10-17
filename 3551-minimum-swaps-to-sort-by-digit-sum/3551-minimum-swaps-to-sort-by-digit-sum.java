class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        long[] key = new long[n];
        for (int i = 0; i < n; i++) {
            int v = nums[i], s = 0;
            for (int t = v; t > 0; t /= 10) s += t % 10;
            key[i] = ((long) s << 47) | ((long) v << 17) | i;
        }
        Arrays.sort(key);

        int[] p = new int[n];
        for (int j = 0; j < n; j++) p[j] = (int) (key[j] & 0x1FFFF);

        int cycles = 0;
        for (int i = 0; i < n; i++) {
            if (p[i] < 0) continue;
            cycles++;
            int j = i;
            while (p[j] >= 0) {
                int nx = p[j];
                p[j] = -1;
                j = nx;
            }
        }
        return n - cycles;
    }
}