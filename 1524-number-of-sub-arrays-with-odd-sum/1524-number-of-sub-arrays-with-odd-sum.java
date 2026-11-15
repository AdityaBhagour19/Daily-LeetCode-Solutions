class Solution {
    public int numOfSubarrays(int[] arr) {
        long ans = 0;
        int even = 1, odd = 0, par = 0;
        for (int v : arr) {
            par ^= v & 1;
            if (par == 1) {
                ans += even;
                odd++;
            } else {
                ans += odd;
                even++;
            }
        }
        return (int) (ans % 1_000_000_007L);
    }
}