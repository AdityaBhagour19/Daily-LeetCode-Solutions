class Solution {
    public int countCommas(int n) {
        int ans = 0;
        for (int i = 1000; i <= n; i *= 1000)
            ans += Math.max(0, n - i + 1);
        return ans;
    }
}