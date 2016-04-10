package com.example.gabriel.myfirstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.RatingBar;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Layout parameters
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                                            LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams starParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                                                            LinearLayout.LayoutParams.WRAP_CONTENT);
        starParams.weight = 0;
        LinearLayout.LayoutParams textTopParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                                            LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams textBotParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);






        // Button
        Button btn = new Button(this);
        btn.setText("Knapp");

        // Ska textTop ha en fixerad height?? Den klarar också flera rader text.
        // Edit text
        EditText textTop = new EditText(this);
        textTop.setHint("Text fält");
        textTop.setSingleLine();

        EditText textBot = new EditText(this);
        textBot.setHint("Text fält");
        textBot.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        textBot.setSingleLine(false);
        textBot.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
        // Ratingbar
        RatingBar starBar = new RatingBar(this);
        starBar.setNumStars(5);

        // Layout
        LinearLayout layout = new LinearLayout(this);

        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(btn, btnParams);
        layout.addView(textTop, textTopParams);
        layout.addView(starBar, starParams);
        layout.addView(textBot, textBotParams);
        //setContentView(layout);
        setContentView(R.layout.activity_my);
    }


}
