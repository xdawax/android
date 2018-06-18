package se.umu.cs.johane.savestateexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StateActivity extends AppCompatActivity {

    Die die;

    private final String DIE = "Current die";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        die=new Die();


        if (savedInstanceState != null) {
            Log.i("savedInstanceStace", "restoring die");
            this.die = savedInstanceState.getParcelable(DIE);
        }

        setValueText();

    }

    private void setValueText() {
        TextView view=this.findViewById(R.id.value);
        view.setText(""+die.getValue());
    }

    public void rollDice(View src){
        die.roll();
        setValueText();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(DIE, die);
    }
}
