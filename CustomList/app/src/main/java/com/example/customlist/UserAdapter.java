package com.example.customlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserAdapter extends ArrayAdapter<UserModel> {
    private Context context;
    private int resource;

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<UserModel> users) {
        super(context, resource, users);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
        }

        UserModel user = getItem(position);

        ImageView avatar = convertView.findViewById(R.id.avatarImageView);
        TextView name = convertView.findViewById(R.id.nameTextView);
        TextView location = convertView.findViewById(R.id.locationTextView);
        TextView age = convertView.findViewById(R.id.ageTextView);

        avatar.setImageResource(user.getAvatarId());
        name.setText(user.getFirstName() + " " + user.getLastName());
        location.setText(user.getCountry() + ", " + user.getCity());
        age.setText(user.getAge() + " years old");

        return convertView;
    }
}
