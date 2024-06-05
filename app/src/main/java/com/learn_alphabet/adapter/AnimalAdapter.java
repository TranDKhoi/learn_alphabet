package com.learn_alphabet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.animal_sound.models.SoundModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AnimalAdapter extends PagerAdapter {
    private Context context;
    private List<SoundModel> data;
    private LayoutInflater layoutInflater;

    public AnimalAdapter(Context context, List<SoundModel> list) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = list;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == ((View) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = this.layoutInflater.inflate(R.layout.layout_item_detail, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.Text2);
        ((TextView) inflate.findViewById(R.id.Text)).setText(this.data.get(i).getName());
        Picasso picasso = Picasso.get();
        picasso.load("file:///android_asset/" + this.data.get(i).getImg()).into((ImageView) inflate.findViewById(R.id.MainImage));
        textView.setText(this.data.get(i).getName_eng());
        viewGroup.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
