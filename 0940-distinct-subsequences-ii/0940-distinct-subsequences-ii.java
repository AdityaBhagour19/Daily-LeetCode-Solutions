class Solution {
    public int distinctSubseqII(String s) {
        long[] dp = new long[26];
        long sum = 0, mod = 1000000007;

        for (char c : s.toCharArray()) {
            int i = c - 'a';
            long old = dp[i];
            dp[i] = (sum + 1) % mod;
            sum = (sum + dp[i] - old + mod) % mod;
        }

        return (int)sum;
    }
}