package com.example.gabriel.mythirdapp;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout linearLayoutFeel = new LinearLayout(this);
        LinearLayout linearLayoutRead = new LinearLayout(this);
        LinearLayout linearLayoutLogo = new LinearLayout(this);

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                                    LinearLayout.LayoutParams.MATCH_PARENT));

        linearLayoutFeel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                                    LinearLayout.LayoutParams.WRAP_CONTENT));

        linearLayoutRead.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

        linearLayoutLogo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayoutFeel.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutRead.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutLogo.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams textParams= new LinearLayout.LayoutParams
                                                        (LinearLayout.LayoutParams.WRAP_CONTENT,
                                                        LinearLayout.LayoutParams.WRAP_CONTENT);

        textParams.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams checkBoxParams= new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView textFeel = new TextView(this);
        textFeel.setText("Hur trivs du på LiU?");
        textFeel.setLayoutParams(textParams);

        CheckBox checkBoxGood = new CheckBox(this);
        checkBoxGood.setText("Bra");

        CheckBox checkBoxVery = new CheckBox(this);
        checkBoxVery.setText("Mycket Bra");

        CheckBox checkBoxGreat= new CheckBox(this);
        checkBoxGreat.setText("Jättebra");
        checkBoxGreat.setChecked(true);

        checkBoxGood.setLayoutParams(checkBoxParams);
        checkBoxVery.setLayoutParams(checkBoxParams);
        checkBoxGreat.setLayoutParams(checkBoxParams);

        TextView textRead = new TextView(this);
        textRead.setLayoutParams(textParams);
        textRead.setText("Läser du på LiTH");

        CheckBox checkBoxRY = new CheckBox(this);
        checkBoxRY.setText("Ja");

        CheckBox checkBoxRN = new CheckBox(this);
        checkBoxRN.setText("Nej");
        checkBoxRN.setChecked(true);

        checkBoxRY.setLayoutParams(checkBoxParams);
        checkBoxRN.setLayoutParams(checkBoxParams);

        ImageView logo = new ImageView(this);
        logo.setLayoutParams(textParams);
        logo.setImageResource(R.drawable.lkpg);

        TextView textLogo = new TextView(this);
        textLogo.setLayoutParams(textParams);
        textLogo.setText("Är detta LiUs logotyp?");

        CheckBox checkBoxLY = new CheckBox(this);
        checkBoxLY.setText("Ja");
        checkBoxLY.setChecked(true);

        CheckBox checkBoxLN = new CheckBox(this);
        checkBoxLN.setText("Nej");

        checkBoxLY.setLayoutParams(checkBoxParams);
        checkBoxLN.setLayoutParams(checkBoxParams);


        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                                        LinearLayout.LayoutParams.WRAP_CONTENT);
        Button sendButton = new Button(this);
        sendButton.setText("Skicka in");
        sendButton.setLayoutParams(buttonParams);

        linearLayout.addView(textFeel);

        linearLayoutFeel.addView(checkBoxGood);
        linearLayoutFeel.addView(checkBoxVery);
        linearLayoutFeel.addView(checkBoxGreat);
        linearLayout.addView(linearLayoutFeel);

        linearLayout.addView(textRead);

        linearLayoutRead.addView(checkBoxRY);
        linearLayoutRead.addView(checkBoxRN);
        linearLayout.addView(linearLayoutRead);

        linearLayout.addView(logo);
        linearLayout.addView(textLogo);

        linearLayoutLogo.addView(checkBoxLY);
        linearLayoutLogo.addView(checkBoxLN);
        linearLayout.addView(linearLayoutLogo);

        linearLayout.addView(sendButton);

        setContentView(linearLayout);
        //setContentView(R.layout.activity_my);

    }
}
