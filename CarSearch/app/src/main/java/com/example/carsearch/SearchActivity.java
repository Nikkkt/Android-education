package com.example.carsearch;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchActivity extends AppCompatActivity {
    private AutoCompleteTextView brandAutoComplete;
    private AutoCompleteTextView modelAutoComplete;
    private Spinner yearSpinner;
    private Button searchButton;
    private List<Car> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        brandAutoComplete = findViewById(R.id.brandAutoComplete);
        modelAutoComplete = findViewById(R.id.modelAutoComplete);
        yearSpinner = findViewById(R.id.yearSpinner);
        searchButton = findViewById(R.id.searchButton);

        carList = ((CarApplication) getApplication()).getCarList();

        Set<String> brands = new HashSet<>();
        Set<String> models = new HashSet<>();
        Set<Integer> years = new HashSet<>();

        for (Car car : carList) {
            brands.add(car.getBrand());
            models.add(car.getModel());
            years.add(car.getYear());
        }

        ArrayAdapter<String> brandAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, brands.toArray(new String[0]));
        brandAutoComplete.setAdapter(brandAdapter);

        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, models.toArray(new String[0]));
        modelAutoComplete.setAdapter(modelAdapter);

        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years.stream().sorted().collect(Collectors.toList()));
        yearSpinner.setAdapter(yearAdapter);

        searchButton.setOnClickListener(v -> {
            String brand = brandAutoComplete.getText().toString();
            String model = modelAutoComplete.getText().toString();
            int year = (int) yearSpinner.getSelectedItem();

            List<Car> filteredCars = carList.stream()
                    .filter(car -> (brand.isEmpty() || car.getBrand().equals(brand)) &&
                            (model.isEmpty() || car.getModel().equals(model)) &&
                            (year == 0 || car.getYear() == year))
                    .collect(Collectors.toList());

            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
            intent.putExtra("filteredCars", new ArrayList<>(filteredCars));
            startActivity(intent);
        });
    }
}