
/**
 * Short class used to try to find optimum values of n for 
 * the RiemannPiCalc.
 * 
 * @author James Commons
 * @version 1.0
 */
public class RiemannNOptimizer {

    // Which calculator to test
    private enum CalcVersion {
        UNDERESTIMATE,
        OVERESTIMATE,
        BOTH;
    }

    // I found that as n gets really really big, the accuracy of
    // the calculation drops off a bit. I have a couple of theories
    // why, but I think the most likely has to do with floating point
    // imprecision. Use this method to find the optimum value of n.
    // Right now, I know it is around 90 - 100 billion.
    private static long findOptimumNValue(CalcVersion calc) {
        double minDiff = Double.MAX_VALUE;
        double currentDiff = Math.PI; // Doesn't really matter what this is
        double pi;

        // Change this to the minimum value you know n is not.
        long n = 90_000_000_000L; // Also should be a multiple of 10

        // incrementer should be a multiple of 10. Change if you see fit.
        long incrementer = 1_000_000_000L; // First increment by 10,000,000,000

        while (incrementer > 0) {
            System.out.println("\rn is currently: " + n);

            switch (calc) {
                case UNDERESTIMATE: 
                    pi = RiemannPiCalc.underestimatePi(n);
                    break;
                case OVERESTIMATE:
                    pi = RiemannPiCalc.overestimatePi(n);
                    break;
                default:
                    pi = RiemannPiCalc.calcPi(n);
            }

            currentDiff = Math.abs(pi - Math.PI);
            System.out.println("\rDifference is currently: " + currentDiff);

            // Accuracy has dropped off if this is true
            if (currentDiff > minDiff) {
                n -= incrementer * 2; // Go back to second previous value of n
                incrementer /= 10;
                n += incrementer;
            } else {
                minDiff = currentDiff;
                n += incrementer;
            }
        }

        return n;
    }

    // Main
    public static void main(String[] args) {
        
        // Note: the progress given is not accurate
        System.out.println("*** Optimum n Value ***");

        // Change CalcVersion for different methods of calculating pi
        System.out.println("n = " + findOptimumNValue(CalcVersion.OVERESTIMATE));
    }
}
