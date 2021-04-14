package com.example.molsapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Main Activity acts as a main menu for the application. Simple Button/Intent combinations.
 * @author Leon Mills 979610
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button oneSolution = findViewById(R.id.one_solution);
        Button allSolution = findViewById(R.id.all_solutions);
        Button euler = findViewById(R.id.euler);
        Button rules = findViewById(R.id.rule_page);



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

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TextPage.class);
                intent.putExtra("Text", "Rules");
                startActivity(intent);
            }
        });
    }
}