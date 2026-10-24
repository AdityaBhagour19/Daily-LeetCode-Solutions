class Solution {
    private static final int[] PREF = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    private int toDays(String s) {
        int m = (s.charAt(0) - '0') * 10 + (s.charAt(1) - '0');
        int d = (s.charAt(3) - '0') * 10 + (s.charAt(4) - '0');
        return PREF[m - 1] + d;
    }

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int start = Math.max(toDays(arriveAlice), toDays(arriveBob));
        int end = Math.min(toDays(leaveAlice), toDays(leaveBob));
        return Math.max(0, end - start + 1);
    }
}