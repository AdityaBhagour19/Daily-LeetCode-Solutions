import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;
        int[] bitCount = new int[30];

        for (int num : nums) {
            for (int bit = 0; bit < 30; bit++) {
                if (((num >> bit) & 1) != 0) {
                    bitCount[bit]++;
                }
            }
        }

        long result = 0;

        for (int i = 0; i < k; i++) {
            long currentNum = 0;

            for (int bit = 0; bit < 30; bit++) {
                if (bitCount[bit] > 0) {
                    currentNum |= (1L << bit);
                    bitCount[bit]--;
                }
            }

            result = (result + currentNum * currentNum) % MOD;
        }

        return (int) result;
    }
}