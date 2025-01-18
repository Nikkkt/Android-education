package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private String previousInput = "";
    private String operator = "";
    private boolean isNewOperation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        initializeButtons();
    }

    private void initializeButtons() {
        int[] numberButtonIds = {
                R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
                R.id.button_8, R.id.button_9
        };

        for (int id : numberButtonIds) findViewById(id).setOnClickListener(this::onNumberClick);

        findViewById(R.id.button_plus).setOnClickListener(v -> onOperatorClick("+"));
        findViewById(R.id.button_minus).setOnClickListener(v -> onOperatorClick("-"));
        findViewById(R.id.button_multiply).setOnClickListener(v -> onOperatorClick("×"));
        findViewById(R.id.button_divide).setOnClickListener(v -> onOperatorClick("÷"));
        findViewById(R.id.button_equal).setOnClickListener(v -> calculateResult());
        findViewById(R.id.button_ac).setOnClickListener(v -> clearAll());
        findViewById(R.id.button_backspace).setOnClickListener(v -> backspace());
        findViewById(R.id.button_percent).setOnClickListener(v -> applyPercentage());
    }

    private void onNumberClick(View view) {
        if (isNewOperation) {
            currentInput = "";
            isNewOperation = false;
        }

        Button button = (Button) view;
        currentInput += button.getText().toString();
        updateDisplay(currentInput);
    }

    private void onOperatorClick(String op) {
        if (!currentInput.isEmpty()) {
            if (!previousInput.isEmpty()) calculateResult();
            operator = op;
            previousInput = currentInput;
            currentInput = "";
        }
    }

    private void calculateResult() {
        if (!previousInput.isEmpty() && !currentInput.isEmpty() && !operator.isEmpty()) {
            double num1 = Double.parseDouble(previousInput);
            double num2 = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "×":
                    result = num1 * num2;
                    break;
                case "÷":
                    if (num2 != 0) result = num1 / num2;
                    else {
                        updateDisplay("Error");
                        clearAll();
                        return;
                    }
                    break;
            }

            currentInput = String.valueOf(result);
            previousInput = "";
            operator = "";
            isNewOperation = true;
            updateDisplay(currentInput);
        }
    }

    private void applyPercentage() {
        if (!currentInput.isEmpty()) {
            double num = Double.parseDouble(currentInput) / 100;
            currentInput = String.valueOf(num);
            updateDisplay(currentInput);
        }
    }

    private void clearAll() {
        currentInput = "";
        previousInput = "";
        operator = "";
        updateDisplay("0");
    }

    private void backspace() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            updateDisplay(currentInput.isEmpty() ? "0" : currentInput);
        }
    }

    private void updateDisplay(String text) {
        display.setText(text);
    }
}
