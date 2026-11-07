class Solution {
    public int pivotInteger(int n) {
        int s = n * (n + 1) / 2;
        int x = (int) Math.sqrt(s);
        return x * x == s ? x : -1;
    }
}