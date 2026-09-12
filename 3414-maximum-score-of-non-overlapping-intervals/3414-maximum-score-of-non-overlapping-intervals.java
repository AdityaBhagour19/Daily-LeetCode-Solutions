class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4]; // l, r, w, origIdx
        for (int i = 0; i < n; i++) {
            List<Integer> iv = intervals.get(i);
            arr[i] = new int[]{iv.get(0), iv.get(1), iv.get(2), i};
        }
        Arrays.sort(arr, (a, b) -> a[1] - b[1]);

        long[][] score = new long[n + 1][5];
        List<Integer>[][] pick = new List[n + 1][5];
        for (int k = 0; k <= 4; k++) pick[0][k] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            int l = arr[i - 1][0], w = arr[i - 1][2], id = arr[i - 1][3];
            int lo = 0, hi = i - 2, p = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (arr[mid][1] < l) { p = mid; lo = mid + 1; } else hi = mid - 1;
            }
            for (int k = 0; k <= 4; k++) {
                score[i][k] = score[i - 1][k];
                pick[i][k] = pick[i - 1][k];
                if (k >= 1) {
                    long cand = score[p + 1][k - 1] + w;
                    List<Integer> cl = new ArrayList<>(pick[p + 1][k - 1]);
                    cl.add(id);
                    Collections.sort(cl);
                    if (cand > score[i][k] || (cand == score[i][k] && smaller(cl, pick[i][k]))) {
                        score[i][k] = cand;
                        pick[i][k] = cl;
                    }
                }
            }
        }

        long best = -1;
        List<Integer> ans = new ArrayList<>();
        for (int k = 0; k <= 4; k++)
            if (score[n][k] > best || (score[n][k] == best && smaller(pick[n][k], ans))) {
                best = score[n][k]; ans = pick[n][k];
            }

        int[] res = new int[ans.size()];
        for (int i = 0; i < res.length; i++) res[i] = ans.get(i);
        return res;
    }

    private boolean smaller(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < Math.min(a.size(), b.size()); i++)
            if (!a.get(i).equals(b.get(i))) return a.get(i) < b.get(i);
        return a.size() < b.size();
    }
}