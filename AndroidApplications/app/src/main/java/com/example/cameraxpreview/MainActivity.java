package com.example.cameraxpreview;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView_count);
    }

    public void showUpMessage(View view) {
        Toast toast = Toast.makeText(this, R.string.message_toast, Toast.LENGTH_LONG);
        toast.show();
    }

    public void countUp(View view) {
        mCount++;

        if (textView != null) {
            textView.setText(Integer.toString(mCount));
        }
    }

    public void resetCount(View view) {
        mCount = 0;
        textView.setText(Integer.toString(mCount));
    }
}