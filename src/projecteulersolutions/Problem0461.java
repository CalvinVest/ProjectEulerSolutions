package projecteulersolutions;

/*
Let fn(k) = e^(k/n) - 1 for k is Z > 0
We are given f200(6) + f200(75) + f200(89) + f200(226)
 = 3.141592644529 or almost pi.
It is the best approximation of pi for n = 200
with the expression fn(a) + fn(b) + fn(c) + fn(d)
 */
public class Problem0461 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        int[] coeffs = {0, 0, 0, 0};
        int[] solution;
        double almostPi = 0.0;
        int n = 200;
        int maxK = 1000;

        for (int k0 = 0; k0 <= maxK; k0++) {
            System.out.println("Primary counter " + k0);
            for (int k1 = k0 + 1; k0 + k1 <= maxK; k1++) {
                System.out.println("Secondary counter " + k1);
                for (int k2 = k1 + 1; k0 + k1 + k2 <= maxK; k2++) {
                    for (int k3 = k2 + 1; k0 + k1 + k2 + k3 <= maxK; k3++) {
                        double temp = myFunc(k0, n)
                                + myFunc(k1, n)
                                + myFunc(k2, n)
                                + myFunc(k3, n);
                        if(calcPiError(temp) < calcPiError(almostPi)) {
                            coeffs[0] = k0;
                            coeffs[1] = k1;
                            coeffs[2] = k2;
                            coeffs[3] = k3;
                            almostPi = temp;
                            System.out.println(k0 + " " + k1 + " " + k2 + " " + k3 + " " + almostPi);
                        }
                    }
                }
            }
        }
        /*for (int i = 0; i < coeffs.length; i++) {
            double delta = myFunc(coeffs[i], n);
            while (delta < Math.PI - almostPi) {
                coeffs[i]++;
                delta = myFunc(coeffs[i], n);
            }
            almostPi += myFunc(coeffs[i], n);
        }*/

        System.out.println("The closest approximization of pi for n = " + n + " is "
                + coeffs[0] + " "
                + coeffs[1] + " "
                + coeffs[2] + " "
                + coeffs[3] + " for f"
                + n + " = " + almostPi);
    }

    public double myFunc(int k, int n) {
        double eExp = (double) k / n;
        return Math.pow(Math.E, eExp) - 1.0;
    }
    
    public double calcPiError(double almostPi) {
        return Math.abs(Math.PI - almostPi);
    }
}
