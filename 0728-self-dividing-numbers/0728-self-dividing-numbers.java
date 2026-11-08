import java.util.*;

class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int num = left; num <= right; num++) {
            int x = num;
            while (x > 0) {
                int d = x % 10;
                if (d == 0 || num % d != 0) break;
                x /= 10;
            }
            if (x == 0) res.add(num);
        }
        return res;
    }
}