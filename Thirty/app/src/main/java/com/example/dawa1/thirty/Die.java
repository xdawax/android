package com.example.dawa1.thirty;

import java.util.Random;

public class Die {

    private Random rnd;

    private int value;

    public Die() {
        rnd = new Random();
        roll(); // sets a value between 1-6
    }

    public int getValue() {
        return this.value;
    }

    public void roll() {
        this.value = rnd.nextInt(6) + 1;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
