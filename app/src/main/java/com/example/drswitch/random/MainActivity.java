package com.example.drswitch.random;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyRandom mr = new MyRandom();

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(mr.outtext());

    }
}
