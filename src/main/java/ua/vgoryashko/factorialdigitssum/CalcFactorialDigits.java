package ua.vgoryashko.factorialdigitssum;

import java.math.BigInteger;

/**
 * Class that calculates digits of 100!.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 31.05.18
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

//    TODO finish the java docs
    public int calcDigits() {
        int result = 0;
        String[] splitNumbers = CalcFactorialDigits.factorial(100).toString().split("");
        for (String s : splitNumbers) {
            result = result + Integer.valueOf(s);
        }
        return result;
    }

}