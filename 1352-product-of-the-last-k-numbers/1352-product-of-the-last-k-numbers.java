class ProductOfNumbers {
    private final int[] pre = new int[40002];
    private int size;

    public ProductOfNumbers() {
        pre[0] = 1;
    }

    public void add(int num) {
        if (num == 0) {
            size = 0;
        } else {
            pre[size + 1] = pre[size] * num;
            size++;
        }
    }

    public int getProduct(int k) {
        if (k > size) return 0;
        return pre[size] / pre[size - k];
    }
}