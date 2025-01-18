package com.example.customlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator {
    private static final int[] avatars = {R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar4, R.drawable.avatar5};
    private static final String[] firstNames = {"Ivan", "Oleh", "Anna", "Maria", "Dmytro", "Olena", "Kateryna", "Mykola", "Sofia", "Andriy"};
    private static final String[] lastNames = {"Petrov", "Ivanov", "Shevchenko", "Kovalenko", "Melnyk", "Tkachenko", "Bondarenko", "Kravchuk", "Polishchuk", "Vasylchenko"};
    private static final String[] countries = {"Ukraine", "USA", "Canada"};
    private static final String[][] cities = {
            {"Kyiv", "Lviv", "Dnipro", "Odessa", "Kharkiv"},
            {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"},
            {"Toronto", "Vancouver", "Montreal", "Calgary", "Ottawa"}
    };

    public static List<UserModel> generateUsers(int count) {
        List<UserModel> users = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int avatarId = avatars[random.nextInt(avatars.length)];
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            int age = 14 + random.nextInt(86);
            int countryIndex = random.nextInt(countries.length);
            String country = countries[countryIndex];
            String city = cities[countryIndex][random.nextInt(cities[countryIndex].length)];

            users.add(new UserModel(avatarId, firstName, lastName, age, country, city));
        }
        return users;
    }
}
