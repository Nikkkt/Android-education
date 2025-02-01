package com.example.carsearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private List<Car> carList;

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.brandTextView.setText(car.getBrand());
        holder.modelTextView.setText(car.getModel());
        holder.yearTextView.setText(String.valueOf(car.getYear()));
        holder.costTextView.setText(String.valueOf(car.getCost()));
        holder.carImageView.setImageResource(car.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        public TextView brandTextView;
        public TextView modelTextView;
        public TextView yearTextView;
        public TextView costTextView;
        public ImageView carImageView;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            brandTextView = itemView.findViewById(R.id.brandTextView);
            modelTextView = itemView.findViewById(R.id.modelTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            costTextView = itemView.findViewById(R.id.costTextView);
            carImageView = itemView.findViewById(R.id.carImageView);
        }
    }
}