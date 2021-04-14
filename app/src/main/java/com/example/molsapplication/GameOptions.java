package com.example.molsapplication;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Game options is a collection of buttons that are used for navigation. Usually used to decide
 * on the size of the game board. However if the euler condition is true, the page will navigate
 * to pages representing information based on the euler conjecture.
 * @author Leon Mills
 */

public class GameOptions extends Activity {
    String mode = "";
    Boolean euler = false;
    int tbt = 4;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_options_window);

        Button threeBythree = findViewById(R.id.three_by_three);
        final Button fourByfour = findViewById(R.id.four_by_four);
        Button fiveByfive = findViewById(R.id.five_by_five);
        Button sevenBySeven = findViewById(R.id.seven_by_seven);
        mode = getIntent().getStringExtra("Mode");
        euler = getIntent().getBooleanExtra("eCondition", false);

        if (euler) {
            threeBythree.setText("Euler Conjecture");
            threeBythree.setTextSize(18);
            fourByfour.setText("Attempt order 6");
            tbt = 6;
            fiveByfive.setVisibility(View.GONE);
            sevenBySeven.setVisibility(View.GONE);
        }


        threeBythree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(euler){
                    Intent intent = new Intent(GameOptions.this, TextPage.class);
                    intent.putExtra("Text", "Euler");
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(GameOptions.this, Board.class);
                    intent.putExtra("Value", 3);
                    intent.putExtra("Mode", mode);
                    startActivity(intent);
                }

            }
        });

        fourByfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOptions.this, Board.class);
                intent.putExtra("Value", tbt);
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
