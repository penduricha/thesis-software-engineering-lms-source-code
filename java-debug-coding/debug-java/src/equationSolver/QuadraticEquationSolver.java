package equationSolver;

import java.util.Arrays;

public class QuadraticEquationSolver {

    public double[] calculate_Quadratic_Equation(double a, double b, double c) {
        if (a == 0) {
            return new double[]{0,0};
        }

        double delta = b * b - 4 * a * c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return new double[]{x1, x2};
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return new double[]{x};
        } else {
            return new double[]{};
        }
    }

    public static void main(String[] args) {
        QuadraticEquationSolver solver = new QuadraticEquationSolver();

        // Ví dụ sử dụng
        double[] roots = solver.calculate_Quadratic_Equation(0, 0,0);
        if (roots.length == 2) {
            System.out.println(Arrays.toString(roots));
        } else if (roots.length == 1) {
            System.out.println(Arrays.toString(roots));
        } else {
            System.out.println(Arrays.toString(roots));
        }
    }
}