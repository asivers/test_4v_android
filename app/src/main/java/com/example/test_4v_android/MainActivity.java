package com.example.test_4v_android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    GridLayout runLayout;
    ImageView bigImage;
    Button runButton;

    GridLayout testLayout;
    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;
    ImageView imageButtonFrame1;
    ImageView imageButtonFrame2;
    ImageView imageButtonFrame3;
    ImageView imageButtonFrame4;
    Button chooseButton;

    int numberOfRounds = 7;
    int currRound;
    String currPic;
    String[] allChosenPics = new String[numberOfRounds + 1];

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runLayout = (GridLayout) findViewById(R.id.runLayout);
        bigImage = (ImageView) findViewById(R.id.bigImage);
        runButton = (Button) findViewById(R.id.runButton);

        testLayout = (GridLayout) findViewById(R.id.testLayout);
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        imageButtonFrame1 = (ImageView) findViewById(R.id.imageButtonFrame1);
        imageButtonFrame2 = (ImageView) findViewById(R.id.imageButtonFrame2);
        imageButtonFrame3 = (ImageView) findViewById(R.id.imageButtonFrame3);
        imageButtonFrame4 = (ImageView) findViewById(R.id.imageButtonFrame4);
        chooseButton = (Button) findViewById(R.id.chooseButton);

        addListenerOnRunButton();
        addListenerOnImageButton1();
        addListenerOnImageButton2();
        addListenerOnImageButton3();
        addListenerOnImageButton4();
        addListenerOnChooseButton();

    }

    public void addListenerOnRunButton() {
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                runLayout.setVisibility(View.GONE);
                testLayout.setVisibility(View.VISIBLE);
                currRound = 1;
                enableAll();
                disableChooseButton();
                refreshPics(currRound);
            }
        });
    }

    public void addListenerOnImageButton1() {
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                enableAll();
                imageButtonFrame1.setVisibility(View.VISIBLE);
                imageButton1.setEnabled(false);
                currPic = "1";
            }
        });
    }

    public void addListenerOnImageButton2() {
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                enableAll();
                imageButtonFrame2.setVisibility(View.VISIBLE);
                imageButton2.setEnabled(false);
                currPic = "2";
            }
        });
    }

    public void addListenerOnImageButton3() {
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                enableAll();
                imageButtonFrame3.setVisibility(View.VISIBLE);
                imageButton3.setEnabled(false);
                currPic = "3";
            }
        });
    }

    public void addListenerOnImageButton4() {
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                enableAll();
                imageButtonFrame4.setVisibility(View.VISIBLE);
                imageButton4.setEnabled(false);
                currPic = "4";
            }
        });
    }

    public void addListenerOnChooseButton() {
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                enableAll();
                disableChooseButton();
                allChosenPics[currRound] = currPic;
                currRound++;
                if (currRound == 8) {
                    showResult(getScore());
                    return;
                }
                refreshPics(currRound);
            }
        });
    }

    public void enableAll() {
        runButton.setEnabled(true);
        imageButton1.setEnabled(true);
        imageButton2.setEnabled(true);
        imageButton3.setEnabled(true);
        imageButton4.setEnabled(true);
        enableChooseButton();
        imageButtonFrame1.setVisibility(View.GONE);
        imageButtonFrame2.setVisibility(View.GONE);
        imageButtonFrame3.setVisibility(View.GONE);
        imageButtonFrame4.setVisibility(View.GONE);
    }

    public int getScore() {
        int score = 0;
        if (allChosenPics[1] == "4")
            score++;
        if (allChosenPics[2] == "2")
            score++;
        if (allChosenPics[3] == "4")
            score++;
        if (allChosenPics[4] == "3")
            score++;
        if (allChosenPics[5] == "3")
            score++;
        if (allChosenPics[6] == "1")
            score++;
        if (allChosenPics[7] == "4")
            score++;
        return score;
    }

    public void showResult(int score) {
        double percentage = Math.round(score * 100 / numberOfRounds);
        if (percentage > 55)
            bigImage.setBackground(getImage("big_good"));
        else if (percentage > 27)
            bigImage.setBackground(getImage("big_average"));
        else
            bigImage.setBackground(getImage("big_bad"));
        runLayout.setVisibility(View.VISIBLE);
        testLayout.setVisibility(View.GONE);
        runButton.setText("Результат - " + (int)percentage + "%.\nПройти еще раз?");
    }

    public void refreshPics(int _currRound) {
        imageButton1.setBackground(getImage("img00" + _currRound + "1"));
        imageButton2.setBackground(getImage("img00" + _currRound + "2"));
        imageButton3.setBackground(getImage("img00" + _currRound + "3"));
        imageButton4.setBackground(getImage("img00" + _currRound + "4"));
    }

    public void enableChooseButton() {
        chooseButton.setEnabled(true);
        chooseButton.setBackgroundColor(Color.parseColor("#6200EE"));
        chooseButton.setTextColor(Color.parseColor("#FFFFFF"));
    }

    public void disableChooseButton() {
        chooseButton.setEnabled(false);
        chooseButton.setBackgroundColor(Color.parseColor("#AAAAFF"));
        chooseButton.setTextColor(Color.parseColor("#FFFFFF"));
    }

    public Drawable getImage(String imageName) {
        return this.getResources().getDrawable(this.getResources().getIdentifier(imageName, "drawable", this.getPackageName()));
    }

}


