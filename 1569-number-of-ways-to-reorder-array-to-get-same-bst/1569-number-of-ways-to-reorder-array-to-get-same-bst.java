import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;
    long[] fact, invFact;

    static class Node {
        int val, idx, size;
        Node left, right;

        Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public int numOfWays(int[] nums) {
        int n = nums.length;
        fact = new long[n + 1];
        invFact = new long[n + 1];

        fact[0] = 1;
        for (int i = 1; i <= n; i++)
            fact[i] = fact[i - 1] * i % MOD;

        invFact[n] = pow(fact[n], MOD - 2);
        for (int i = n; i > 0; i--)
            invFact[i - 1] = invFact[i] * i % MOD;

        TreeMap<Integer, Node> map = new TreeMap<>();
        Node root = new Node(nums[0], 0);
        map.put(nums[0], root);

        for (int i = 1; i < n; i++) {
            int x = nums[i];
            Map.Entry<Integer, Node> lo = map.lowerEntry(x);
            Map.Entry<Integer, Node> hi = map.higherEntry(x);

            Node parent;
            if (lo == null) parent = hi.getValue();
            else if (hi == null) parent = lo.getValue();
            else parent = lo.getValue().idx > hi.getValue().idx
                    ? lo.getValue() : hi.getValue();

            Node cur = new Node(x, i);
            if (x < parent.val) parent.left = cur;
            else parent.right = cur;
            map.put(x, cur);
        }

        return (int) ((dfs(root) - 1 + MOD) % MOD);
    }

    long dfs(Node node) {
        if (node == null) return 1;

        long left = dfs(node.left);
        long right = dfs(node.right);

        int l = node.left == null ? 0 : node.left.size;
        int r = node.right == null ? 0 : node.right.size;

        node.size = l + r + 1;
        return choose(l + r, l) * left % MOD * right % MOD;
    }

    long choose(int n, int r) {
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }

    long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }
}