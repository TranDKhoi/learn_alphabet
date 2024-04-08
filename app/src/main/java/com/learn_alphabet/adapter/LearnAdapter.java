package com.learn_alphabet.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.learn_speak.LearnActivity;
import com.learn_alphabet.activities.learn_speak.LearnResourceSet;
import com.learn_alphabet.models.LearnModel;

import java.util.ArrayList;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.MyViewHolder> {
    private final ArrayList<LearnModel> mData;
    private final Context context;
    private final LayoutInflater mInflater;

    public LearnAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = initData();
    }

    private ArrayList<LearnModel> initData() {

        ArrayList<LearnModel> data = new ArrayList<>();
        data.add(new LearnModel(0, R.drawable.abc, R.drawable.card1, "Alphabets"));
        data.add(new LearnModel(1, R.drawable.numbers, R.drawable.card2, "Numbers"));
        data.add(new LearnModel(2, R.drawable.days, R.drawable.card3, "Days"));
        data.add(new LearnModel(3, R.drawable.months, R.drawable.card4, "Months"));
        data.add(new LearnModel(4, R.drawable.fruits2, R.drawable.card5, "Fruits"));
        data.add(new LearnModel(5, R.drawable.animals3, R.drawable.card6, "Animals"));
        data.add(new LearnModel(6, R.drawable.colors, R.drawable.card1, "Colors"));
        data.add(new LearnModel(7, R.drawable.bodyparts_icon, R.drawable.card4, "Body Parts"));
        data.add(new LearnModel(8, R.drawable.professions_icon, R.drawable.card5, "Professions"));
        data.add(new LearnModel(9, R.drawable.shapes_icon, R.drawable.card3, "Shapes"));
        data.add(new LearnModel(10, R.drawable.vehicles_icon, R.drawable.card2, "Vehicles"));

        return data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_learn, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LearnModel item = mData.get(position);

        holder.title.setText(item.title);
        holder.img.setImageResource(item.img);
        holder.bg.setBackgroundResource(item.bg);
        holder.img.startAnimation(AnimationUtils.loadAnimation(
                context, R.anim.rotation
        ));
        holder.img.setOnClickListener(v -> onClick(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void onClick(int id) {
        Intent intent = new Intent(context, LearnActivity.class);
        switch (id) {
            case 0:
                intent.putExtra("type", LearnResourceSet.ICON1);
                break;
            case 1:
                intent.putExtra("type", LearnResourceSet.ICON2);
                break;
            case 2:
                intent.putExtra("type", LearnResourceSet.ICON3);
                break;
            case 3:
                intent.putExtra("type", LearnResourceSet.ICON4);
                break;
            case 4:
                intent.putExtra("type", LearnResourceSet.ICON5);
                break;
            case 5:
                intent.putExtra("type", LearnResourceSet.ICON6);
                break;
            case 6:
                intent.putExtra("type", LearnResourceSet.ICON7);
                break;
            case 7:
                intent.putExtra("type", LearnResourceSet.ICON8);
                break;
            case 8:
                intent.putExtra("type", LearnResourceSet.ICON9);
                break;
            case 9:
                intent.putExtra("type", LearnResourceSet.ICON10);
                break;
            case 10:
                intent.putExtra("type", LearnResourceSet.ICON11);
                break;
        }
        context.startActivity(intent);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;
        LinearLayout bg;

        MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.image);
            bg = itemView.findViewById(R.id.bg);
        }
    }
}