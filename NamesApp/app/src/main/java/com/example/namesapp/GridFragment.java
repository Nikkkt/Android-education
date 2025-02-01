package com.example.namesapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import java.util.ArrayList;

public class GridFragment extends Fragment {
    private GridView gridView;
    private ArrayList<String> nameStrings;
    private ArrayAdapter<String> adapter;

    public GridFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        gridView = view.findViewById(R.id.gridView);

        nameStrings = new ArrayList<>();
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) for (NameModel nm : activity.getNameList()) nameStrings.add(nm.getName());

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, nameStrings);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = nameStrings.get(position);
                DetailFragment detailFragment = new DetailFragment();
                Bundle args = new Bundle();
                args.putString("name", selectedName);
                detailFragment.setArguments(args);

                assert getFragmentManager() != null;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, detailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
