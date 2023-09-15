package com.fattouh.cars;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CarViewHolder> {
    ArrayList<Car>cars=new ArrayList<>();
    public RecyclerViewAdapter(ArrayList<Car> cars){
        this.cars=cars;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_car_item,null,false);
        CarViewHolder carViewHolder=new CarViewHolder(v);
        return carViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car c=cars.get(position);
        if (c.getImage()!=null && !c.getImage().isEmpty())
            holder.iv_car_item.setImageURI(Uri.parse(c.getImage()));
        holder.tv_car_item_model.setText(c.getModel());
        holder.tv_car_item_color.setText(c.getColor());
        holder.tv_car_item_dpl.setText(String.valueOf(c.getDpl()));
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    class CarViewHolder extends RecyclerView.ViewHolder{
    ImageView iv_car_item;
    TextView tv_car_item_model,tv_car_item_color,tv_car_item_dpl;
        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_car_item=itemView.findViewById(R.id.car_iv_item);
            tv_car_item_model=itemView.findViewById(R.id.car_tv_item_mode);
            tv_car_item_color=itemView.findViewById(R.id.car_tv_item_color);
            tv_car_item_dpl=itemView.findViewById(R.id.car_tv_item_depl);
        }
    }
}
