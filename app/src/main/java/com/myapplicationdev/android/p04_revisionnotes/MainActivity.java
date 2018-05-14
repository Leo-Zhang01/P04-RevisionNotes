package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText noteContent;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button insert, show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteContent = findViewById(R.id.editTextNote);
        radioGroup = findViewById(R.id.radioGroupStars);
        insert = findViewById(R.id.buttonInsertNote);
        show = findViewById(R.id.buttonShowList);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                int stars = Integer.valueOf(radioButton.getText().toString());
                String note = noteContent.getText().toString();
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertNote(note,stars);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
