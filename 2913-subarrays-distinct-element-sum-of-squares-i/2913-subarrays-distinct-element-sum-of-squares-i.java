class Solution {
    public int sumCounts(List<Integer> nums) {
        int answer = 0;
        int n = nums.size();
        int distinctCount, i, j;

        for (i = 0; i < n; ++i) {
            int[] count = new int[101];
            distinctCount = 0;

            for (j = i; j < n; ++j) {
                if (++count[nums.get(j)] == 1) {
                    distinctCount++;
                }

                answer += distinctCount * distinctCount;
            }
        }

        return answer;
    }
}