package com.example.menupractice;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = findViewById(R.id.edit_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_edit_text) {
            showEditTextDialog();
            return true;
        } else if (id == R.id.menu_select_text) {
            showSelectTextDialog();
            return true;
        } else if (id == R.id.menu_get_time) {
            showCurrentTime();
            return true;
        } else if (id == R.id.menu_change_date) {
            showDatePicker();
            return true;
        } else if (id == R.id.menu_trigger_notification) {
            triggerNotification();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showEditTextDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Редагувати текст");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("ОК", (dialog, which) -> {
            String text = input.getText().toString();
            if (!text.isEmpty()) {
                editText.setText(text + "\n" + getCurrentDateTime());
            }
        });

        builder.setNegativeButton("Відміна", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showSelectTextDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Обрати текст");
        builder.setItems(new String[]{"Варіант 1", "Варіант 2", "Варіант 3"}, (dialog, which) -> {
            String[] options = {"Варіант 1", "Варіант 2", "Варіант 3"};
            editText.setText(options[which] + "\n" + getCurrentDateTime());
        });
        builder.show();
    }

    private void showCurrentTime() {
        String currentTime = getCurrentDateTime();
        new AlertDialog.Builder(this).setTitle("Поточний час").setMessage(currentTime).setPositiveButton("ОК", null).show();
    }

    private void showDatePicker() {
        DatePickerDialog datePicker = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                    editText.setText("Дата: " + date);
                    showNotification("Дата змінена", date);
                }, 2023, 0, 1);
        datePicker.show();
    }

    private void triggerNotification() {
        String text = editText.getText().toString();
        if (!text.isEmpty()) showNotification("Сповіщення", text);
        else Toast.makeText(this, "Текстове поле порожнє", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private String getCurrentDateTime() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    private void showNotification(String title, String message) {
        String text = editText.getText().toString();
        if (!text.isEmpty()) {
            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime());
            Toast.makeText(this, "Повідомлення: " + text + " о " + currentTime, Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Текстове поле порожнє!", Toast.LENGTH_SHORT).show();
    }
}