class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int cur = 0, dir = 1; cur < n; cur += dir) {
            char c = s.charAt(cur);
            if (c == '(' || c == ')') {
                cur = pair[cur];
                dir = -dir;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}