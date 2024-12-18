package com.example.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private int low = 1;
    private int high = 100;
    private int guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView guessText = findViewById(R.id.guessText);
        Button higherButton = findViewById(R.id.higherButton);
        Button lowerButton = findViewById(R.id.lowerButton);
        Button correctButton = findViewById(R.id.correctButton);

        updateGuess(guessText);

        higherButton.setOnClickListener(v -> {
            low = guess + 1;
            updateGuess(guessText);
        });

        lowerButton.setOnClickListener(v -> {
            high = guess - 1;
            updateGuess(guessText);
        });

        correctButton.setOnClickListener(v -> {
            Intent intent = new Intent(GameActivity.this, ResultActivity.class);
            intent.putExtra("GUESS", guess);
            startActivity(intent);
        });
    }

    private void updateGuess(TextView guessText) {
        guess = (low + high) / 2;
        guessText.setText("Ваше число: " + guess + "?");
    }
}
