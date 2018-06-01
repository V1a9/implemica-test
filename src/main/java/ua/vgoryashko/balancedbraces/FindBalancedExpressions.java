package ua.vgoryashko.balancedbraces;

import com.google.common.base.Strings;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that solves problem of the "Balanced Braces".
 * For a given number n of '(' and ')' finds a number of balanced expressions.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 31.05.18
 */
public class FindBalancedExpressions {

    /**
     * Variable that represents counting number of expression and used in generator of expressions.
     */
    private int counterOfExpressions;

    /**
     * Variable that stores qty of braces. Calculated as n*2 and used in formatting of expressions.
     */
    private int bracesQty;

    /**
     * Variable that stores number of balanced expressions.
     */
    private int counterOfBalancedExpressions = 0;

    /**
     * Constructor of the class.
     * @param n number of '(' and ')' braces.
     */
    public FindBalancedExpressions(double n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number has to be > 0");
        }
        this.counterOfExpressions = (int) Math.pow(2, n * 2) - 1;
        this.bracesQty = (int) n * 2;
    }

    /**
     * Method that checks if an expression is balanced.
     * @param expression String representation of an expression where all '(' replaced by '0' and ')' by '1'.
     * @return boolean "true" if an expression is balanced, "false" - otherwise.
     */
    private boolean isBalanced(String expression) {

        boolean balanced = true;
        byte[] expressionToBytes = expression.getBytes();
        List<Byte> stack = new LinkedList<>();

        for (byte expressionToByte : expressionToBytes) {
            if (expressionToByte == 48) {
                ((LinkedList<Byte>) stack).push(expressionToByte);
            } else if (!stack.isEmpty()) {
                ((LinkedList<Byte>) stack).pop();
            } else if (stack.isEmpty() && expressionToByte == 49) {
                balanced = false;
                break;
            }
        }

        if (!stack.isEmpty()) {
            balanced = false;
        }

        return balanced;
    }

    /**
     * Method that generates binary String representation of an Integer.
     * @param counter integer number that represents sequence of braces.
     * @return String representation of an int number padded with '0' from beginning
     */
    private String generateExpression(int counter) {
        return Strings.padStart(Integer.toBinaryString(counter), bracesQty, '0');
    }

    /**
     * Method that implements main logic of the class.
     * @return int number of balanced expressions for a given number of braces.
     */
    public int getNumberOfBalancedExpressions() {
        double numberOfExpressions = counterOfExpressions;
        for (double i = numberOfExpressions - 1; i > 0; i--) {
            while (counterOfExpressions != 0) {
                String s = this.generateExpression(counterOfExpressions);
                if (isBalanced(s)) {
                    counterOfBalancedExpressions++;
                }
                counterOfExpressions--;
            }
        }
        return counterOfBalancedExpressions;
    }
}
