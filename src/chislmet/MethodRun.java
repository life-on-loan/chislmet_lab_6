package chislmet;

public class MethodRun {
    private double y0;
    private double yn;

    public MethodRun(double y0, double yn) {
        this.y0 = y0;
        this.yn = yn;
    }

    public double[] progonka(int n, double[] mu, double[] lambdas) {
        double[] y = new double[n + 1];
        y[n] = (mu[n] + Math.E + 1/Math.E - 2) / (1.0 - lambdas[n]);
        for (int i = n - 1; i > -1; i--) {
            y[i] = lambdas[i + 1] * y[i + 1] + mu[i + 1];
        }
        return y;
    }

    public double[] countLambda(int n) {
        double[] lambdas = new double[n + 1];
        lambdas[0] = 0;
        for (int i = 1; i < n; i++) {
            lambdas[i] = 1.0 / (2 + 1.0 / (n * n) - lambdas[i - 1]);
        }
        return lambdas;
    }

    public double[] countMu(int n, double[] lambdas) {
        double h = 1.0 / n;
        double[] mu = new double[n + 1];
        mu[0] = 0;
        double x = 0;
        for (int i = 1; i < n; i++) {
            mu[i] = (mu[i - 1] - B(x, h)) / (2 + h * h - lambdas[i]);
            x += h;
        }
        return mu;
    }

    private double B(double x, double h) {
        return h * h * (4.8 * x - 4.8 * x * x + 11.6);
    }
}
