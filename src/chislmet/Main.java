package chislmet;

public class Main {

    public static void main(String[] args) {
        int n1 = 10;
        int n2 = 20;
        double a = 0;
        double b = 1;
        double m0 = 0.0001;
        double y0 = 0.0;
        double betta = Math.E + 1/Math.E - 2.0;
        double fiShtrich = (Math.E*Math.E - 1) / (2.0 * Math.E);

        MethodShooting methodShooting = new MethodShooting(fiShtrich, m0, a, b);
        double[] y1 = methodShooting.RungeKutta(n1, y0);
        double[] fi1 = methodShooting.countFi(y1, betta);
        double[] mu1 = methodShooting.countMu(fi1);
        double[] result1 = methodShooting.RungeKutta(n1, mu1[2]);

        double[] y2 = methodShooting.RungeKutta(n2, y0);
        double[] fi2 = methodShooting.countFi(y2, betta);
        double[] mu2 = methodShooting.countMu(fi2);
        double[] result2 = methodShooting.RungeKutta(n2, mu2[2]);

        System.out.println("Метод стрельбы: 10 точек");
        for (int i = 0; i < n1; i++) {
            System.out.println(result1[i]);
        }
        System.out.println();
        System.out.println("Метод стрельбы: 20 точек");
        for (int i = 0; i < n2; i++) {
            System.out.println(result2[i]);
        }
        System.out.println("________________________");
        MethodRun run = new MethodRun(y0, betta);
        double[] lambdas1 = run.countLambda(n1);
        double[] muRun1 = run.countMu(n1, lambdas1);
        double[] res1 = run.progonka(n1, muRun1, lambdas1);

        double[] lambdas2 = run.countLambda(n2);
        double[] muRun2 = run.countMu(n2, lambdas2);
        double[] res2 = run.progonka(n2, muRun2, lambdas2);

        double x = 0;
        double h1 = 1.0/n1;
        System.out.println("Метод прогонки: 10 точек");
        for (int i = 0; i < n1 + 1; i++) {
            System.out.println(x + i * h1 + ";" + res1[i]);
        }
        System.out.println();
        System.out.println("Метод прогонки: 20 точек");
        double h2 = 1.0/n2;
        for (int i = 0; i < n2 + 1; i++) {
            System.out.println(x + i * h2 + ";" + res2[i]);
        }

        System.out.println();
        System.out.println("Точное решение : 500 точек");
        double[] resExact = exact(500);
        for (int i = 0; i < 500; i++) {
            System.out.println(x + i * (1.0/500.0) + ";" + resExact[i]);
        }
    }

    public static double[] exact(int n) {
        double[] y = new double[n];
        double x = 0;
        double h = 1.0/n;
        for (int i = 0; i < n; i++) {
           y[i] = 4.8 * x * x - 4.8 * x + Math.pow(Math.E, -x) +  Math.pow(Math.E, x) - 2;
           x += h;
        }
        return y;
    }
}
