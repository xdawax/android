package com.example.dawa1.intentnewview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mButtonNewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonNewActivity = (Button) findViewById(R.id.button_new_activity);

        mButtonNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewActivity.class);

                i.putExtra("val1", "Hello kitty!");
                i.putExtra("val2", "Hello Doggo!");

                startActivityForResult(i, 0);
            }
        });
    }
}
