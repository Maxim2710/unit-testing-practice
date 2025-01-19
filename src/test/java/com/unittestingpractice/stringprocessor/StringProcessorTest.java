package com.unittestingpractice.stringprocessor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringProcessorTest {
    private final StringProcessor stringProcessor = new StringProcessor();

    @Test
    void testToUpperCaseValidInput() {
        String input = "hello";
        String expected = "HELLO";

        String result = stringProcessor.toUpperCase(input);

        assertEquals(expected, result);
    }

    @Test
    void testToUpperCaseNullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stringProcessor.toUpperCase(null);
        });

        assertEquals("Input cannot be null", exception.getMessage());
    }

    @Test
    void testReverseValidInput() {
        String input = "hello";
        String expected = "olleh";

        String result = stringProcessor.reverse(input);

        assertEquals(expected, result);
    }

    @Test
    void testReverseNullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stringProcessor.reverse(null);
        });

        assertEquals("Input cannot be null", exception.getMessage());
    }

    @Test
    void testIsPalindromeValidPalindrome() {
        String input = "madam";

        boolean result = stringProcessor.isPalindrome(input);

        assertTrue(result);
    }

    @Test
    void testIsPalindromeValidNonPalindrome() {
        String input = "hello";

        boolean result = stringProcessor.isPalindrome(input);

        assertFalse(result);
    }

    @Test
    void testIsPalindromeWithSpacesAndCase() {
        String input = "A man a plan a canal Panama";

        boolean result = stringProcessor.isPalindrome(input);

        assertTrue(result);
    }

    @Test
    void testIsPalindromeNullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stringProcessor.isPalindrome(null);
        });

        assertEquals("Input cannot be null", exception.getMessage());
    }

    @Test
    void testConcatenateValidInputs() {
        String first = "Hello";
        String second = "World";
        String expected = "HelloWorld";

        String result = stringProcessor.concatenate(first, second);

        assertEquals(expected, result);
    }

    @Test
    void testConcatenateFirstInputNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stringProcessor.concatenate(null, "World");
        });

        assertEquals("Neither input can be null", exception.getMessage());
    }

    @Test
    void testConcatenateSecondInputNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stringProcessor.concatenate("Hello", null);
        });

        assertEquals("Neither input can be null", exception.getMessage());
    }

    @Test
    void testConcatenateBothInputsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stringProcessor.concatenate(null, null);
        });

        assertEquals("Neither input can be null", exception.getMessage());
    }
}
