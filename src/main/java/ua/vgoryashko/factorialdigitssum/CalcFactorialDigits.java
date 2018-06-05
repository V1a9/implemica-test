package ua.vgoryashko.factorialdigitssum;

import java.math.BigInteger;

/**
 * Class that calculates digits of 100!.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 04.06.18
 */
public class CalcFactorialDigits {

    /**
     * Method that calculates factorial for a number.
     * @param number that a factorial has to be calculated for.
     * @return BigInteger factorial of a given number
     */
    private static BigInteger factorial(int number){
        if (number <= 1) {
            return BigInteger.ONE;
        } else {
            return factorial(number-1).multiply(BigInteger.valueOf(number));
        }
    }

    /**
     * Method that converts factorial value to a String value and creates array of String[] filled
     * with String representation of separate digits.
     * After that all string values are converted to Integer and summed up.
     * @return int sum of digits
     */
    public int calcDigits() {
        int result = 0;
        String[] splitNumbers = CalcFactorialDigits.factorial(100).toString().split("");
        for (String s : splitNumbers) {
            result = result + Integer.valueOf(s);
        }
        return result;
    }

}