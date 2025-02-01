package com.example.namesapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {
    private TextView tvName, tvBirthday, tvMeaning;

    public DetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        tvName = view.findViewById(R.id.tvName);
        tvBirthday = view.findViewById(R.id.tvBirthday);
        tvMeaning = view.findViewById(R.id.tvMeaning);

        Bundle args = getArguments();
        if (args != null) {
            String name = args.getString("name");
            tvName.setText(name);
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                NameModel nm = activity.getNameModelByName(name);
                if (nm != null) {
                    tvBirthday.setText("Дата іменин: " + nm.getBirthday());
                    tvMeaning.setText("Значення: " + nm.getMeaning());
                }
            }
        }
        return view;
    }
}
