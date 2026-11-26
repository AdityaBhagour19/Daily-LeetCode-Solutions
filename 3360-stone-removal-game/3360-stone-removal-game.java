class Solution {
    public boolean canAliceWin(int n) {
        return ((0xE1F03F807FC00L >> n) & 1L) != 0;
    }
}