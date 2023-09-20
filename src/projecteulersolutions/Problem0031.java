package projecteulersolutions;

/*
The goal of problem 31 is to find the number of ways £2 can be expressed with
coins in general circulation.

They are:
1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p)
 */
public class Problem0031 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        // brute force solution
        int count = 0;

        int a = 1;
        int b = 2;
        int c = 5;
        int d = 10;
        int e = 20;
        int f = 50;
        int g = 100;
        int h = 200;

        for (int i = 0; i <= 200; i++) {
            for (int j = 0; j <= 100; j++) {
                for (int k = 0; k <= 40; k++) {
                    for (int l = 0; l <= 20; l++) {
                        for (int m = 0; m <= 10; m++) {
                            for (int n = 0; n <= 4; n++) {
                                for (int r = 0; r <= 2; r++) {
                                    for (int p = 0; p <= 1; p++) {
                                        if (i * a + j * b + k * c + l * d + m * e + n * f + r * g + p * h == 200) {
                                            count++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        System.out.println("2 British Pounds can be expressed with coins in " + count + " unique ways.");
    }
}
