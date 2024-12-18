package com.example.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultText = findViewById(R.id.resultText);
        Button playAgainButton = findViewById(R.id.playAgainButton);

        int guessedNumber = getIntent().getIntExtra("GUESS", -1);
        resultText.setText("Я вгадав! Ваше число: " + guessedNumber);

        playAgainButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
