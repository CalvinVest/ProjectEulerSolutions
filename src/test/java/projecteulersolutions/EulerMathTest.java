package projecteulersolutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EulerMathTest {

    @Test
    void testIsPalindrome() {
        assertTrue(EulerMath.isPalindrome("kayak"), "kayak should be a palindrome");
        assertTrue(EulerMath.isPalindrome("racecar"), "racecar should be a palindrome");
        assertFalse(EulerMath.isPalindrome("lorem"), "lorem should NOT be a palindrome");
        assertFalse(EulerMath.isPalindrome("to"), "to should NOT be a palindrome");
    }

    @Test
    void testIsPandigital() {
        assertTrue(EulerMath.isPandigital(1234), "1234 should be pandigital");
        assertTrue(EulerMath.isPandigital(12), "12 should be pandigital");
        assertFalse(EulerMath.isPandigital(2345), "2345 should NOT be pandigital");
        assertFalse(EulerMath.isPandigital(420123), "420123 should NOT be pandigital");
    }

    @Test
    void testIsPrime() {
        assertTrue(EulerMath.isPrime(2), "2 should be prime");
        assertTrue(EulerMath.isPrime(3), "3 should be prime");
        assertTrue(EulerMath.isPrime(47), "47 should be prime");
        assertFalse(EulerMath.isPrime(4), "4 should NOT be prime");
        assertFalse(EulerMath.isPrime(0), "0 should NOT be prime");
        assertFalse(EulerMath.isPrime(1), "1 should NOT be prime");
    }

    @Test
    void testIsPythagorean() {
        assertTrue(EulerMath.isPythagorean(3, 4, 5), "3 4 5 should be pythagorean");
        assertTrue(EulerMath.isPythagorean(5, 12, 13), "5 12 13 should be pythagorean");
        assertFalse(EulerMath.isPythagorean(1, 2, 3), "1 2 3 should NOT be pythagorean");
    }

    @Test
    void testContainsInt() {

    }

    @Test
    void testGetDigitArray() {

    }

    @Test
    void testGetDigitCount() {

    }

    @Test
    void testGetFactorial() {

    }

    @Test
    void testGetGCD() {

    }

    @Test
    void testGetLCM() {

    }

    @Test
    void testGetNextPrime() {

    }

    @Test
    void testGetSieveOfEratosthenes() {

    }
}
