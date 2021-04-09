package com.example.molsapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOptions extends Activity {
    String mode = "";
    Boolean euler = false;
    int tbt = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_options_window);

        Button threeBythree = findViewById(R.id.three_by_three);
        Button fourByfour = findViewById(R.id.four_by_four);
        Button fiveByfive = findViewById(R.id.five_by_five);
        Button sevenBySeven = findViewById(R.id.seven_by_seven);
        mode = getIntent().getStringExtra("Mode");
        euler = getIntent().getBooleanExtra("eCondition", false);

        if (euler) {
            threeBythree.setText("Attempt order 6");
            tbt = 6;
            fourByfour.setVisibility(View.GONE);
            fiveByfive.setVisibility(View.GONE);
            sevenBySeven.setVisibility(View.GONE);
        }


        threeBythree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(GameOptions.this, Board.class);
               intent.putExtra("Value", tbt);
               intent.putExtra("Mode", mode);
               startActivity(intent);
            }
        });

        fourByfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOptions.this, Board.class);
                intent.putExtra("Value", 4);
                intent.putExtra("Mode", mode);
                startActivity(intent);
            }
        });

        fiveByfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOptions.this, Board.class);
                intent.putExtra("Value", 5);
                intent.putExtra("Mode", mode);
                startActivity(intent);
            }
        });


        sevenBySeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOptions.this, Board.class);
                intent.putExtra("Value", 7);
                intent.putExtra("Mode", mode);
                startActivity(intent);
            }
        });

    }
}
