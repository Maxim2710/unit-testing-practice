package com.unittestingpractice.provider;

import com.unittestingpractice.interfaces.RandomNumberProvider;

import java.util.Random;

public class RandomNumberProviderImpl implements RandomNumberProvider {

    private final Random random = new Random();

    @Override
    public int getRandomNumber() {
        return random.nextInt(100);
    }
}
