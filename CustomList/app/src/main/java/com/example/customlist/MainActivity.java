package com.example.customlist;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.userListView);
        List<UserModel> users = UserGenerator.generateUsers(100);

        UserAdapter adapter = new UserAdapter(this, R.layout.list_item, users);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            UserModel user = users.get(position);
            String message = "Selected: " + user.getFirstName() + ", " + user.getAge() + " years old";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });
    }
}