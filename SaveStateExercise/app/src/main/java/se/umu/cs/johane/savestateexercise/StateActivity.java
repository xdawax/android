package se.umu.cs.johane.savestateexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StateActivity extends AppCompatActivity {

    Die die;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        die=new Die();
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
}
