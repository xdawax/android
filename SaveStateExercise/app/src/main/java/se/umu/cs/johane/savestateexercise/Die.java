package se.umu.cs.johane.savestateexercise;

import java.util.Random;

/**
 * Created by johane on 2018-01-09.
 * A representation of a 6 sided Die
 */

public class Die {
    private int value;
    private Random rand;

    public Die() {
        rand=new Random();
        roll();
    }

    public int getValue() {
        return value;
    }

    public void roll() {
        value=rand.nextInt(6)+1;
    }
}
