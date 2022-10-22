package com.example.pecuniaprojectmk1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onButtonCLick(View view) {
        TextView txtHello = findViewById(R.id.textView);
        txtHello.setText("Heys, Pecunia is alive!");
    }
}
