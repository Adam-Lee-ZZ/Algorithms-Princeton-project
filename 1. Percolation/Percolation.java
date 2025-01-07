import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF p;
    private String[] list;
    private int size;
    private int count = 0;

    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }

        this.list = new String[n * n];
        this.size = n;
        this.p = new WeightedQuickUnionUF(n * n + 1);

        for (int i = 0; i < list.length; i++) {
            list[i] = "Closed";
        }
    }

    private boolean ifInGrid(int row, int col) {
        return row >= 0 && col >= 0 && row < size && col < size;
    }

    public void open(int prow, int pcol) {

        int row = prow - 1;
        int col = pcol - 1;

        if (!ifInGrid(row, col)) {
            throw new java.lang.IllegalArgumentException("the given site (row, col) must in the n-by-n grid");
        }

        if (!list[row * size + col].equals("Open")) {
            list[row * size + col] = "Open";
            count += 1;
        }

        if (row == 0) {
            p.union(row * size + col, size * size);
        }

        if (ifInGrid(row, col + 1) && list[row * size + col + 1].equals("Open")) {
            p.union(row * size + col, row * size + col + 1);
        }

        if (ifInGrid(row, col - 1) && list[row * size + col - 1].equals("Open")) {
            p.union(row * size + col, row * size + col - 1);
        }

        if (ifInGrid(row + 1, col) && list[(row + 1) * size + col].equals("Open")) {
            p.union((row + 1) * size + col, row * size + col);
        }
        if (ifInGrid(row - 1, col) && list[(row - 1) * size + col].equals("Open")) {
            p.union((row - 1) * size + col, row * size + col);
        }

    }

    public boolean isOpen(int prow, int pcol) {

        int row = prow - 1;
        int col = pcol - 1;

        if (!ifInGrid(row, col)) {
            throw new java.lang.IllegalArgumentException("the given site (row, col) must in the n-by-n grid");
        }

        return list[row * size + col].equals("Open");
    }

    public boolean isFull(int prow, int pcol) {

        int row = prow - 1;
        int col = pcol - 1;

        if (!ifInGrid(row, col)) {
            throw new java.lang.IllegalArgumentException("the given site (row, col) must in the n-by-n grid");
        }

        return (list[row * size + col].equals("Open") && p.find(row * size + col) == p.find(size * size));

    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        for (int i = 0; i < size; i++) {
            if (isFull(size, i + 1)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(3, 1);
        p.open(2, 1);
        p.open(1, 3);
        p.open(2, 3);
        p.open(3, 3);
        System.out.println(p.isFull(3, 1));
    }

}
