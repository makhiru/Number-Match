package com.example.picturematch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Start_Activity extends AppCompatActivity {

    Button btnstart;
    Spinner spinner;

    ArrayList<String> arrchoice = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnstart = findViewById(R.id.btnstart);
        spinner = findViewById(R.id.spinner);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = spinner.getSelectedItem().toString();
                Intent intent = new Intent(Start_Activity.this, MainActivity.class);
                if (s.equals("6 BOX")) {
                    intent.putExtra("key", 1);
                } else if (s.equals("8 BOX")) {
                    intent.putExtra("key", 2);
                } else if (s.equals("10 BOX")) {
                    intent.putExtra("key", 3);
                }
                startActivity(intent);
            }
        });

        arrchoice.add("6 BOX");
        arrchoice.add("8 BOX");
        arrchoice.add("10 BOX");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrchoice);
        spinner.setAdapter(arrayAdapter);

    }
}