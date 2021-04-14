package com.example.molsapplication;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;

/**
 * TextPage is a class that  is a simple text page that will have detailed paragraphs explaining
 * the rules and some of the history behind MOLS.
 * @author Leon Mills 979610
 */

public class TextPage extends Activity {
    private String text = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textpage);
        TextView tv = findViewById(R.id.textpage_title);
        TextView paragraph = findViewById(R.id.text_paragraph);

        text = getIntent().getStringExtra("Text");
        if (text .equals("Rules")){
            tv.setText(R.string.rules);
            paragraph.setText(R.string.rule_script);
        }
        if (text .equals("Euler")){
            tv.setText(R.string.eulerConjecture);
            paragraph.setText(R.string.euler_story);
        }
    }
}
