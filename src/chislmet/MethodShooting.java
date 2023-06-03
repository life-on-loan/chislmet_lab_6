package chislmet;

public class MethodShooting {
    private double fiShtrich;
    private double m0;
    private double a;
    private double b;

    public MethodShooting(double fiShtrich, double m0, double a, double b) {
        this.fiShtrich = fiShtrich;
        this.m0 = m0;
        this.a = a;
        this.b = b;
    }

    public double[] RungeKutta(int n, double y0) {
        double h = (b - a) / n;
        double xi = a;
        double[] y = new double[n];
        y[0] = y0;
        for (int i = 0; i < n - 1; i++) {
            double k1 = f(xi, y[i]);
            double k2 = f(xi + h, y[i] + k1/2.0);
            double k3 = f(xi + h/2.0, y[i] - k1 + 2*k2);
            y[i + 1] = y[i] + h*(k1 + 4.0*k2 +k3)/6.0;
            xi += h;
        }
        return y;
    }

    public double[] countFi (double[] y, double betta) {
        double[] fi = new double[y.length];
        for (int i = 0; i < y.length; i++) {
            fi[i] = y[i] - betta;
        }
        return fi;
    }

    public double[] countMu (double[] fi) {
        double[] mu = new double[fi.length];
        mu[0] = m0;
        for (int i = 1; i < fi.length; i++) {
            mu[i] = mu[i-1] - fi[i-1]/fiShtrich;
        }
        return mu;
    }

    private double f(double x, double y) {
        return y + 4.8 * x * (1 - x) + 11.6;
    }
}
