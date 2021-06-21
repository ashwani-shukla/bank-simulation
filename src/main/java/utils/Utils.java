package utils;

import java.util.SplittableRandom;
import org.apache.commons.math3.distribution.BetaDistribution;

/**
 * Utils class
 */
public class Utils {

    /**
     * Method to get an exponentially distributed random number based on a mean and a uniformly distributed random number
     * @param mean - Mean
     * @return exponentially distributed random number
     */
    public static double getRandomNumber(Double mean) {
        return -mean * Math.log(1 - new SplittableRandom().nextDouble());
    }

    /**
     * Method to get a beta distribution random number
     * @param p - p
     * @param a - alpha
     * @param b - beta
     * @return beta distributed random number
     */
    public static double getServiceTime(Integer p, Integer a, Integer b) {
        double v = new BetaDistribution(a, b).inverseCumulativeProbability(new SplittableRandom().nextDouble());
        return p * v;
    }
}
