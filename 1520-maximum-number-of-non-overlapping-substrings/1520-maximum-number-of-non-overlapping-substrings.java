class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26], last = new int[26];
        Arrays.fill(first, -1);
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();
        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue;
            int lo = first[c], hi = last[c];
            for (int i = lo; i <= hi; i++) {
                int cc = s.charAt(i) - 'a';
                if (first[cc] < lo) { lo = first[cc]; i = lo - 1; continue; }
                hi = Math.max(hi, last[cc]);
            }
            intervals.add(new int[]{lo, hi});
        }

        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> res = new ArrayList<>();
        int end = -1;
        for (int[] iv : intervals) {
            if (iv[0] > end) {
                res.add(s.substring(iv[0], iv[1] + 1));
                end = iv[1];
            }
        }
        return res;
    }
}