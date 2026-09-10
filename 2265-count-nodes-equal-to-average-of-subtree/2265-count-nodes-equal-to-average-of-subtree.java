class Solution {
    int ans = 0;

    int[] dfs(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        int[] l = dfs(root.left);
        int[] r = dfs(root.right);

        int sum = l[0] + r[0] + root.val;
        int cnt = l[1] + r[1] + 1;

        if (sum / cnt == root.val) ans++;

        return new int[]{sum, cnt};
    }

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }
}