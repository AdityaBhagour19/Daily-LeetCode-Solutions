class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean isSorted = true;

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }

        if (isSorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        int minPos = 0;
        int maxPos = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) < s.charAt(minPos)) {
                minPos = i;
            }

            if (s.charAt(i) >= s.charAt(maxPos)) {
                maxPos = i;
            }
        }

        if (minPos == 0 || maxPos == n - 1) {
            return 1;
        }

        if (minPos == n - 1 && maxPos == 0) {
            return 3;
        }

        return 2;
    }
}