
/**
 * A simple algorithm for calculating pi. This program uses a Maclauren series 
 * to estimate π / 4. In the future, I might try to add multi-threading to this.
 * The difference seems to be constant after a certain value of n. From DEFAULT_N
 * on, I think pi is always calculated to be the same which is a little weird.
 * 
 * @author James Commons
 * @version 1.0
 */
public class MaclaurenPiCalc {
    
    // CHANGE THIS VALUE FOR MORE OR LESS ACCURACY
    // Changing this value will change execution time if command line
    // argument is not given.
    public static final long DEFAULT_N = 1_000_000_000L; // 1_000_000_000L I like this one

    /**
     * Estiamates pi using a simple maclauren series. N is the number of iterations
     * (sums) the algorithm will do. However, since this does both the adding and
     * subtracting, the number of iterations will actually b n * 2. This is important
     * for comparisions with other methods, but computation time is sufficiently 
     * different already.
     * @param n Half the number of iterations you want to do.
     * @return An estimate of pi.
     */
    public static double calcPi(long n) {

        // Check that n is an appropriate value
        if (n < 0 || n > Long.MAX_VALUE - 1) {
            throw new IllegalArgumentException(
                    "n cannot be less than 0 or greater than or equal to Long.MAX_VALUE"
            );
        }

        double sum = 0;
        long dividend = 1;
        for (long i = 0; i < n; i++) {

            // Actually does n * 2 iterations because this takes
            // care of both the adding and subtracting portions of
            // the sum.
            sum += (1.0 / dividend) - (1.0 / (dividend + 2));
            dividend += 4;

            // Print status
            if (i % 1_000_000 == 0) {
                System.out.format("\rProgress: %.2f%%", ((double) i / n) * 100);
            }
        }

        // Wipe out progress bar
        System.out.print("\r                                                 ");

        // Return the sum times 4 since it should equal π / 4
        return sum * 4;
    }

    // Main
    public static void main(String[] args) {
        System.out.println(
                "*** This program calculates an estimate for π ***"
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
