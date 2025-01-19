package com.unittestingpractice.calculator;

import com.unittestingpractice.provider.RandomNumberProviderImpl;

public class Calculator {

    private RandomNumberProviderImpl randomNumberProvider;

    public int add(int a, int b) {
        return Math.addExact(a, b);
    }

    public int subtract(int a, int b) {
        return Math.subtractExact(a, b);
    }

    public int multiply(int a, int b) {
        return Math.multiplyExact(a, b);
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }

        return a / b;
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public int addWithRandom(int a) {
        int random = randomNumberProvider.getRandomNumber();
        return a + random;
    }
}
