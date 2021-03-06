
/**
 * A simple algorithm for calculating pi. This program uses a Riemann sum to estimate
 * the area under the curve sqrt(1 - x^2) between 0 and 1, which will equal π / 4.
 * In the future, I might try to add multi-threading to this.
 * 
 * @author James Commons
 * @version 1.0
 */
public class RiemannPiCalc {

    // CHANGE THIS VALUE FOR MORE OR LESS ACCURACY
    // Changing this value will change execution time if command line
    // argument is not given.
    public static final long DEFAULT_N = 1_000_000_000L; // 1_000_000_000L I like this one

    /**
     * Estimates the value of pi by finding the area of a circle with radius 1.
     * This is my own algorithm. I'm sure others have come up with it, but I 
     * did not look this up. <code>n</code> is the number of iterations to compute. 
     * MAXIMUM VALUE ALLOWED: Long.MAX_VALUE - 1
     * @param n The number of iterations the loop should go through.
     *          Higher is more accurate.
     * @return An estimated value for pi.
     */
    public static double calcPi(long n) {

        // Check that n is an appropriate value
        if (n < 0 || n > Long.MAX_VALUE - 1) {
            throw new IllegalArgumentException(
                    "n cannot be less than 0 or greater than or equal to Long.MAX_VALUE"
            );
        }

        // Understimate
        System.out.println("*** Underestimate ***");
        double piUnder = underestimatePi(n);
        System.out.println("\rUnderstimate of π: " + piUnder);
        System.out.println("Difference: " + (piUnder - Math.PI));
        System.out.println(); // Print newline

        // Overestimate
        System.out.println("*** Overestimate ***");
        double piOver = overestimatePi(n);
        System.out.println("\rOverestimate of π: " + piOver);
        System.out.println("Difference: " + (piOver - Math.PI));
        System.out.println();

        // Return average
        return (piUnder + piOver) / 2;
    }

    /**
     * Calls the underestimate version of the calculator.
     * @param n Number of iterations.
     * @return Calculated value for pi.
     */
    public static double underestimatePi(long n) {
        
        // Check that n is an appropriate value
        if (n < 0 || n > Long.MAX_VALUE - 1) {
            throw new IllegalArgumentException(
                    "n cannot be less than 0 or greater than or equal to Long.MAX_VALUE"
            );
        }

        double sum = 0;
        double iDivN; // i divided by n
        double invN = 1.0 / n; // Inverse of n for width of rectangles
        for (long i = 1; i <= n; i++) {
            iDivN = (double) i / n;
            sum += Math.sqrt(1 - (iDivN * iDivN));

            // Print status
            if (i % 1_000_000 == 0) {
                System.out.format("\rProgress: %.2f%%", iDivN * 100);
            }
        }

        // Return 4 * (1 / n) * sum
        return sum * invN * 4;
    }

    /**
     * Calls the overestimate version of the calculator.
     * @param n Number of iterations.
     * @return Calculated value for pi.
     */
    public static double overestimatePi(long n) {
        
        // Check that n is an appropriate value
        if (n < 0 || n > Long.MAX_VALUE - 1) {
            throw new IllegalArgumentException(
                    "n cannot be less than 0 or greater than or equal to Long.MAX_VALUE"
            );
        }

        double sum = 0;
        double iDivN; // i divided by n
        double invN = 1.0 / n; // Inverse of n for width of rectangles
        for (long i = 0; i < n; i++) {
            iDivN = (double) i / n;
            sum += Math.sqrt(1 - (iDivN * iDivN));

            // Print status
            if (i % 1_000_000 == 0) {
                System.out.format("\rProgress: %.2f%%", iDivN * 100);
            }
        }

        // Return 4 * (1 / n) * sum
        return sum * invN * 4;
    }

    // Main
    public static void main(String[] args) {
        System.out.println(
                "This program will first calculate an underestimate for π.\n" + 
                "It will then calculate on overestimate and average the two\n" + 
                "to get a final approximate value for pi."
        );
        System.out.println();

        long startTime = System.currentTimeMillis();
        double pi = -1;
        if (args.length > 0) { // If user provided a commandline argument
            try {
                pi = calcPi(Long.parseLong(args[0]));
            } catch (NumberFormatException e) {
                System.err.println("Command line argument should be an int or long value.");
                e.printStackTrace();
                System.exit(1);
            }
        } else {
            pi = calcPi(DEFAULT_N); 
        }

        // Print results
        System.out.println("*** Results ***");
        System.out.println("Calculated π to be: " + pi);
        System.out.println("π actually equals:  " + Math.PI);
        System.out.println("Difference: " + (pi - Math.PI));
        System.out.println();

        // Print out elapsed time
        System.out.println("*** Elapsed Time ***");
        double elapsedTime = (System.currentTimeMillis() - startTime) / 1000.0;
        if (elapsedTime > 3600) {
            System.out.format("%.2f hours.%n", elapsedTime / 3600);
        } else if (elapsedTime > 60) {
            System.out.format("%.2f minutes.%n", elapsedTime / 60);
        } else {
            System.out.format("%.2f seconds.%n", elapsedTime);
        }
        
        // Print newline for end of program
        System.out.println();
    }
}
