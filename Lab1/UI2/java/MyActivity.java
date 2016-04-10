package com.example.gabriel.mysecondapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GridLayout gridLayout = new GridLayout(this);
        GridLayout.Spec row1 = GridLayout.spec(0);
        GridLayout.Spec col1 = GridLayout.spec(0);
        GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row1, col1);

        lp.width = MATCH_PARENT;

        gridLayout.setPadding(50, 50, 50, 50);
        gridLayout.setLayoutParams(lp);
        gridLayout.setOrientation(GridLayout.HORIZONTAL);
        gridLayout.setRowCount(4);
        gridLayout.setColumnCount(2);

        TextView textName = new TextView(this);
        textName.setText("Namn");

        TextView textPass = new TextView(this);
        textPass.setText("Lösenord");

        TextView textMail = new TextView(this);
        textMail.setText("Mail");

        TextView textAge = new TextView(this);
        textAge.setText("Ålder");

        EditText editName = new EditText(this);
        editName.setHint("Skriv ditt namn");
        editName.setSingleLine();

        EditText editPass = new EditText(this);
        editPass.setSingleLine();
        PasswordTransformationMethod pass = new PasswordTransformationMethod();
        editPass.setTransformationMethod(pass.getInstance());
        editPass.setHint("Skriv in ditt lösenord");


        EditText editMail = new EditText(this);
        editMail.setHint("Skriv in din mail");
        editMail.setSingleLine();

        SeekBar slider = new SeekBar(this);
        LayoutParams sliderParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        slider.setLayoutParams(sliderParams);

        gridLayout.addView(textName);
        gridLayout.addView(editName);
        gridLayout.addView(textPass);
        gridLayout.addView(editPass);
        gridLayout.addView(textMail);
        gridLayout.addView(editMail);
        gridLayout.addView(textAge);
        gridLayout.addView(slider);
        //setContentView(gridLayout);
        setContentView(R.layout.activity_my);

    }

}
