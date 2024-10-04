package projecteulersolutions.problems;

/*
The goal of problem 31 is to find the number of ways £2 can be expressed with
coins in general circulation.

They are:
1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p)
 */
public class Problem0031 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        // brute force solution
        int count = 0;

        int total = 200;
        int twopound = 200;
        int onepound = 100;
        int fiftypence = 50;
        int twentypence = 20;
        int tenpence = 10;
        int fivepence = 5;
        int twopence = 2;
        int onepence = 1;

        // two pounds
        for (int i = 0; i <= total / twopound; i++) {
            int sum = i * twopound;
            if (sum > total) {
                break;
            }
            // one pound
            for (int j = 0; j <= total / onepound; j++) {
                sum = i * twopound
                        + j * onepound;
                if (sum > total) {
                    break;
                }
                // fifty pence
                for (int k = 0; k <= total / fiftypence; k++) {
                    sum = i * twopound
                            + j * onepound
                            + k * fiftypence;
                    if (sum > total) {
                        break;
                    }
                    // twenty pence
                    for (int l = 0; l <= total / twentypence; l++) {
                        sum = i * twopound
                                + j * onepound
                                + k * fiftypence
                                + l * twentypence;
                        if (sum > total) {
                            break;
                        }
                        // ten pence
                        for (int m = 0; m <= total / tenpence; m++) {
                            sum = i * twopound
                                    + j * onepound
                                    + k * fiftypence
                                    + l * twentypence
                                    + m * tenpence;
                            if (sum > total) {
                                break;
                            }
                            // five pence
                            for (int n = 0; n <= total / fivepence; n++) {
                                sum = i * twopound
                                        + j * onepound
                                        + k * fiftypence
                                        + l * twentypence
                                        + m * tenpence
                                        + n * fivepence;
                                if (sum > total) {
                                    break;
                                }
                                // two pence
                                for (int r = 0; r <= total / twopence; r++) {
                                    sum = i * twopound
                                            + j * onepound
                                            + k * fiftypence
                                            + l * twentypence
                                            + m * tenpence
                                            + n * fivepence
                                            + r * twopence;
                                    if (sum > total) {
                                        break;
                                    }
                                    // one pence
                                    for (int p = 0; p <= total / onepence; p++) {
                                        sum = i * twopound
                                                + j * onepound
                                                + k * fiftypence
                                                + l * twentypence
                                                + m * tenpence
                                                + n * fivepence
                                                + r * twopence
                                                + p * onepence;
                                        if (sum > total) {
                                            break;
                                        }
                                        if (sum == total) {
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
