package ua.vgoryashko.balancedbraces;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindBalancedExpressionsTest {

    private FindBalancedExpressions findBalancedExpressions;

    @Before
    public void setUp() {
        findBalancedExpressions = new FindBalancedExpressions(2);
    }

    /**
     * Test that evaluates a case when number of balanced expressions checked for a correct number.
     */
    @Test
    public void whenFindBalancedExpressionsInvokedForCorrectNThenNumberOfExpressionsReturned() {
        assertThat(findBalancedExpressions.getNumberOfBalancedExpressions(), is(2));
    }

    /**
     * Test that evaluates a case when number of balanced expressions checked for a wrong number.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenFindBalancedExpressionsInvokedForNotCorrectNThenExceptionThrown() {
        new FindBalancedExpressions(0).getNumberOfBalancedExpressions();
    }
}