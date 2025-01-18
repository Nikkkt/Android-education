package com.example.interviewquestionnaire;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etFullName, etAge;
    private SeekBar seekBarSalary;
    private RadioGroup rgQuestion1, rgQuestion2, rgQuestion3, rgQuestion4, rgQuestion5;
    private CheckBox cbExperience, cbTeamwork, cbTravel;
    private Button btnSubmit;
    private TextView tvSalary;
    private TextView tvResult;

    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 40;
    private static final int MIN_SALARY = 500;
    private static final int MAX_SALARY = 5000;
    private static final int PASSING_SCORE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFullName = findViewById(R.id.etFullName);
        etAge = findViewById(R.id.etAge);
        seekBarSalary = findViewById(R.id.seekBarSalary);
        tvSalary = findViewById(R.id.tvSalary);
        rgQuestion1 = findViewById(R.id.rgQuestion1);
        rgQuestion2 = findViewById(R.id.rgQuestion2);
        rgQuestion3 = findViewById(R.id.rgQuestion3);
        rgQuestion4 = findViewById(R.id.rgQuestion4);
        rgQuestion5 = findViewById(R.id.rgQuestion5);
        cbExperience = findViewById(R.id.cbExperience);
        cbTeamwork = findViewById(R.id.cbTeamwork);
        cbTravel = findViewById(R.id.cbTravel);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setEnabled(false);

        etFullName.addTextChangedListener(textWatcher);
        etAge.addTextChangedListener(textWatcher);
        seekBarSalary.setOnSeekBarChangeListener(seekBarListener);
        rgQuestion1.setOnCheckedChangeListener(radioGroupListener);
        rgQuestion2.setOnCheckedChangeListener(radioGroupListener);
        rgQuestion3.setOnCheckedChangeListener(radioGroupListener);
        rgQuestion4.setOnCheckedChangeListener(radioGroupListener);
        rgQuestion5.setOnCheckedChangeListener(radioGroupListener);

        btnSubmit.setOnClickListener(view -> calculateResult());
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            validateForm();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tvSalary.setText("Зарплата: " + progress + " USD");
            validateForm();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    private final RadioGroup.OnCheckedChangeListener radioGroupListener = (group, checkedId) -> validateForm();

    private void validateForm() {
        String fullName = etFullName.getText().toString().trim();
        boolean isFullNameValid = !fullName.isEmpty();

        String ageText = etAge.getText().toString().trim();
        boolean isAgeValid = false;
        if (!ageText.isEmpty()) {
            try {
                int age = Integer.parseInt(ageText);
                isAgeValid = age >= MIN_AGE && age <= MAX_AGE;
            } catch (NumberFormatException e) {
                isAgeValid = false;
            }
        }

        int salary = seekBarSalary.getProgress();
        boolean isSalaryValid = salary >= MIN_SALARY && salary <= MAX_SALARY;

        boolean areQuestionsAnswered =
                rgQuestion1.getCheckedRadioButtonId() != -1 &&
                rgQuestion2.getCheckedRadioButtonId() != -1 &&
                rgQuestion3.getCheckedRadioButtonId() != -1 &&
                rgQuestion4.getCheckedRadioButtonId() != -1 &&
                rgQuestion5.getCheckedRadioButtonId() != -1;

        btnSubmit.setEnabled(isFullNameValid && isAgeValid && isSalaryValid && areQuestionsAnswered);
    }

    private void calculateResult() {
        int score = 0;

        if (rgQuestion1.getCheckedRadioButtonId() == R.id.rbQ1Option3) score += 2;
        if (rgQuestion2.getCheckedRadioButtonId() == R.id.rbQ2Option3) score += 2;
        if (rgQuestion3.getCheckedRadioButtonId() == R.id.rbQ3Option3) score += 2;
        if (rgQuestion4.getCheckedRadioButtonId() == R.id.rbQ4Option1) score += 2;
        if (rgQuestion5.getCheckedRadioButtonId() == R.id.rbQ5Option3) score += 2;
        if (cbExperience.isChecked()) score += 2;
        if (cbTeamwork.isChecked()) score += 1;
        if (cbTravel.isChecked()) score += 1;

        if (score >= PASSING_SCORE) tvResult.setText("Ви пройшли тест. Загальна кількість балів: " + score);
        else tvResult.setText("Ви не пройшли тест. Загальна кількість балів: " + score);

        tvResult.setVisibility(TextView.VISIBLE);
    }
}
