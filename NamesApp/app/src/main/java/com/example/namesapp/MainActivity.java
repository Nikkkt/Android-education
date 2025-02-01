package com.example.namesapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<NameModel> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNameList();

        if (savedInstanceState == null) {
            GridFragment gridFragment = new GridFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, gridFragment).commit();
        }
    }

    private void initNameList() {
        nameList = new ArrayList<>();
        nameList.add(new NameModel("Нікіта", "29.01.2006", "перемагаю, переможець"));
        nameList.add(new NameModel("Дмитро", "20.10.2000", "стосовний до Деметри, належний їй; у грецькій міфології Деметра — богиня родючості та хліборобства"));
        nameList.add(new NameModel("Євген", "15.06.2004", "благородний, шляхетний"));
    }

    public ArrayList<NameModel> getNameList() {
        return nameList;
    }

    public NameModel getNameModelByName(String name) {
        for (NameModel nm : nameList) if (nm.getName().equals(name)) return nm;
        return null;
    }
}