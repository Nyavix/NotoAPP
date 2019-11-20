package com.example.notoapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Dialog mydialog;

    Button[] btnWord = new Button[0];
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test();

        mydialog = new Dialog(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    private void test() {
        linear = (LinearLayout) findViewById(R.id.chapterLayout);

        for (int i = 0; i < btnWord.length; i++) {
            btnWord[i] = new Button(this);
            btnWord[i].setHeight(60);
            btnWord[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200));
            btnWord[i].setWidth(400);
            btnWord[i].setTag(i);
            btnWord[i].setText("Chapter " + (i + 1));
            btnWord[i].setOnClickListener(btnClicked);
            linear.addView(btnWord[i]);
        }
    }

    public void addChapter(String name) {
        linear = (LinearLayout) findViewById(R.id.chapterLayout);

        Button chButton = new Button(this);
        chButton.setHeight(60);
        chButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200));
        chButton.setWidth(400);
        chButton.setText(name);
        chButton.setOnClickListener(btnClicked);
        linear.addView(chButton);
    }

    public void showAddCH(View view) {
        Button addButton;
        Button cancleButton;
        final EditText nameText;
        mydialog.setContentView(R.layout.chapterpopup);
        mydialog.show();

        addButton = (Button)mydialog.findViewById(R.id.AddDutton);
        cancleButton = (Button)mydialog.findViewById(R.id.CancelButton);
        nameText = (EditText) mydialog.findViewById(R.id.chapterTextView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChapter(nameText.getText().toString());
                mydialog.dismiss();
            }
        });

        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.dismiss();
            }
        });
    }

    View.OnClickListener btnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Object tag = v.getTag();
            Intent intent = new Intent(MainActivity.this, ChapterActivity.class);
            intent.putExtra("CUR_CHAPTER", ((Button)v).getText().toString());
            startActivity(intent);
        }
    };
}
