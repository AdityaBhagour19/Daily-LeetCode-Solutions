class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int n = arr.length;
        int min = arr[0], max = arr[0];
        for (int v : arr) {
            if (v < min) min = v;
            if (v > max) max = v;
        }
        if (min == max) return true;
        int span = max - min;
        if (span % (n - 1) != 0) return false;
        int d = span / (n - 1);
        boolean[] seen = new boolean[n];
        for (int v : arr) {
            int off = v - min;
            if (off % d != 0) return false;
            int k = off / d;
            if (seen[k]) return false;
            seen[k] = true;
        }
        return true;
    }
}