package ua.vgoryashko.factorialdigitssum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalcFactorialDigitsTest {

    private CalcFactorialDigits calcFactorialDigits;

    @Before
    public void setUp() {
        calcFactorialDigits = new CalcFactorialDigits();
    }

    @Test
    public void calculate() {
        assertEquals(648, calcFactorialDigits.calcDigits());
    }
}