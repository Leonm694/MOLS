package com.example.molsapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;

/**
 * Class for pop up menu that allows the user to navigate back to the menus from the game and
 * restart any given level the user is playing.
 * @author Leon Mills 979610
 */

public class PopUp extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up);
        final String title = getIntent().getStringExtra("Title");
        final int score = getIntent().getIntExtra("Score", 0);
        final int value = getIntent().getIntExtra("Value", 0);
        final String mode = getIntent().getStringExtra("Mode");
        final String score_to_text = ("Moves: " + String.valueOf(score));

        TextView title_text = findViewById(R.id.title_text);
        TextView score_text = findViewById(R.id.score_text);
        Button restart = findViewById(R.id.restart_button);
        Button home = findViewById(R.id.home_button);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PopUp.this, Board.class);
                intent.putExtra("Value", value);
                intent.putExtra("Mode", mode);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PopUp.this, MainActivity.class);
                startActivity(intent);
            }
        });

        title_text.setText(title);
        score_text.setText(score_to_text);

        /**
         * These lines of code allows the activity to be viewed smaller than the screen to act as
         * a pop up activity.
         * This code was found on stack overflow at
         * https://stackoverflow.com/questions/2306503/how-to-make-an-alert-dialog-fill-90-of-screen-size
         * Co-answered by "Tunaki" and "Guilherme Pereira" in April 2016.
         */

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.7));

    }
}
