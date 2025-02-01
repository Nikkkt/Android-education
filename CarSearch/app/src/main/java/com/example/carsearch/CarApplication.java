package com.example.carsearch;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class CarApplication extends Application {
        private List<Car> carList;

        @Override
        public void onCreate() {
            super.onCreate();
            carList = new ArrayList<>();
            carList.add(new Car("Toyota", "Corolla", 2020, "Good condition", 20000, R.drawable.car1));
            carList.add(new Car("Honda", "Civic", 2019, "Excellent condition", 18000, R.drawable.car2));
        }

        public List<Car> getCarList() {
            return carList;
        }
}
