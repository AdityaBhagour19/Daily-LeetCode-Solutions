class Solution {
    public int totalNumbers(int[] digits) {
        int ans = 0;

        for (int n = 100; n <= 998; n += 2) {
            int a = n / 100, b = n / 10 % 10, c = n % 10;
            int[] cnt = new int[10];

            for (int x : digits) cnt[x]++;

            if (--cnt[a] >= 0 && --cnt[b] >= 0 && --cnt[c] >= 0)
                ans++;
        }

        return ans;
    }
}