class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length, ans = 0;

        for (int x = -n + 1; x < n; x++)
            for (int y = -n + 1; y < n; y++) {
                int count = 0;
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        if (i + x >= 0 && i + x < n &&
                            j + y >= 0 && j + y < n &&
                            img1[i][j] == 1 && img2[i+x][j+y] == 1)
                            count++;
                ans = Math.max(ans, count);
            }

        return ans;
    }
}