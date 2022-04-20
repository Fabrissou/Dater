package com.core;

public enum Seed {
    Human(1), Bot(0);

    private final int value;

    Seed(int value) {
        this.value = value;
    }

}
