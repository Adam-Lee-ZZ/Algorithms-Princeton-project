import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] list;
    private int t;

    public PercolationStats(int n, int trials) {

        if ((n <= 0) || (trials <= 0))
            throw new IllegalArgumentException();

        this.list = new double[trials];
        this.t = trials;

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                p.open(StdRandom.uniformInt(n) + 1, StdRandom.uniformInt(n) + 1);
            }
            list[i] = (double) p.numberOfOpenSites() / (n * n);
        }

    }

    public double mean() {
        return StdStats.mean(list);
    }

    public double stddev() {
        return StdStats.stddev(list);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(t);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    }

    public static void main(String[] args) {
        PercolationStats percStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + percStats.mean());
        System.out.println("stddev                  = " + percStats.stddev());
        System.out.println(
                "95% confidence interval = [" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");
    }
}
