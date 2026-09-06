class Solution {
    public int numDistinct(String s, String t) {
        long[] dp = new long[t.length() + 1];
        dp[0] = 1;

        for (char c : s.toCharArray())
            for (int j = t.length(); j > 0; j--)
                if (c == t.charAt(j - 1))
                    dp[j] += dp[j - 1];

        return (int) dp[t.length()];
    }
}