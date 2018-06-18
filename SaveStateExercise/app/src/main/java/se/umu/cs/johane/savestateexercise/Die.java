package se.umu.cs.johane.savestateexercise;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * Created by johane on 2018-01-09.
 * A representation of a 6 sided Die
 */

public class Die implements Parcelable{

    public static final Parcelable.Creator<Die> CREATOR = new Parcelable.Creator<Die>() {
        public Die createFromParcel(Parcel in) {
            return new Die(in);
        }

        public Die[] newArray(int size) {
            return new Die[size];
        }
    };

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

    public Die(Parcel in){
        this.value = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.value);
    }
}
