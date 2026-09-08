class CustomStack {
    int ptr =-1;
    int[] s;

    public CustomStack(int maxSize) {
        s= new int [maxSize];
    }
    
    public void push(int x) {
        if(ptr >= s.length-1)return;
        ptr++;

        s[ptr] = x;
        
    }
     public int pop() {
        if (ptr == -1) return -1;
        return s[ptr--];
    }

    public void increment(int k, int val) {
        for (int i = 0; i <= Math.min(k - 1, ptr); i++)
            s[i] += val;
    }
}