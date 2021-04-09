package com.example.molsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button oneSolution = findViewById(R.id.one_solution);
        Button allSolution = findViewById(R.id.all_solutions);
        Button euler = findViewById(R.id.euler);


        oneSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameOptions.class);
                intent.putExtra("Mode", "OneSolution");
                intent.putExtra("eCondition", false);
                startActivity(intent);
            }
        });

        allSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameOptions.class);
                intent.putExtra("Mode", "AllSolution");
                intent.putExtra("eCondition", false);
                startActivity(intent);
            }
        });

        euler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameOptions.class);
                intent.putExtra("Mode", "AllSolution");
                intent.putExtra("eCondition", true);
                startActivity(intent);
            }
        });
    }
}