class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1, c2 = cost2, cb = costBoth;
        long a = need1 * c1 + need2 * c2;
        long b = need1 * cb + Math.max(0, need2 - need1) * c2;
        long c = need2 * cb + Math.max(0, need1 - need2) * c1;
        return Math.min(a, Math.min(b, c));
    }
}