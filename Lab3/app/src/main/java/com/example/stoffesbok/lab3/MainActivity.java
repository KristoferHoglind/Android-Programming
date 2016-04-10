package com.example.stoffesbok.lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private int n = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Create the layout params for the interactive searcher
        LinearLayout.LayoutParams interactiveSearcherParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        // Test button
        Button button = new Button(this);
        button.setText("Test!");
        linearLayout.addView(button);

        // Create the interactive searcher
        InteractiveSearcher interactiveSearcher = new InteractiveSearcher(this, n);
        linearLayout.addView(interactiveSearcher, interactiveSearcherParams);

        // Test text view
        TextView textView = new TextView(this);
        textView.setText("Test!");
        linearLayout.addView(textView);

        // Set content view
        linearLayout.setPadding(40,40,40,40);
        setContentView(linearLayout);
    }
}
