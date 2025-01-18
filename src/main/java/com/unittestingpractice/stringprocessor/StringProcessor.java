package com.unittestingpractice.stringprocessor;

public class StringProcessor {
    public String toUpperCase(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        return input.toUpperCase();
    }

    public String reverse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return new StringBuilder(input).reverse().toString();
    }

    public boolean isPalindrome(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        String sanitized = input.replaceAll("\\s+", "").toLowerCase();
        String reversed = new StringBuilder(sanitized).reverse().toString();
        return sanitized.equals(reversed);
    }

    public String concatenate(String first, String second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Neither input can be null");
        }
        return first + second;
    }
}
