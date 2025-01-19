package com.unittestingpractice.stringprocessor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringProcessorTest {
    private final StringProcessor stringProcessor = new StringProcessor();

    @Test
    void testToUpperCase_ValidInput() {
        String input = "hello";
        String expected = "HELLO";

        String result = stringProcessor.toUpperCase(input);

        assertEquals(expected, result);
    }

    @Test
    void testToUpperCase_NullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stringProcessor.toUpperCase(null);
        });

        assertEquals("Input cannot be null", exception.getMessage());
    }

    @Test
    void testReverse_ValidInput() {
        String input = "hello";
        String expected = "olleh";

        String result = stringProcessor.reverse(input);

        assertEquals(expected, result);
    }

    @Test
    void testReverse_NullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stringProcessor.reverse(null);
        });

        assertEquals("Input cannot be null", exception.getMessage());
    }
}
