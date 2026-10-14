class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) return "";

        StringBuilder s = new StringBuilder();
        while (sum > 0) {
            int d = Math.min(9, sum);
            s.append(d);
            sum -= d;
        }
        while (s.length() < num) s.append('0');
        return s.toString();
    }
}