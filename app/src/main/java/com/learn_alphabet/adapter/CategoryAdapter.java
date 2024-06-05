package com.learn_alphabet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.animal_sound.models.MainCategoryModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ReyclerViewHolder> {
    private List<MainCategoryModel> data;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private int rowLayout;


    public interface OnItemClickListener {
        void onItemClick(View view, MainCategoryModel mainCategoryModel, int i);
    }

    public CategoryAdapter(Context context, int i, List<MainCategoryModel> list) {
        this.mContext = context;
        this.rowLayout = i;
        this.data = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ReyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ReyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.rowLayout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ReyclerViewHolder reyclerViewHolder, final int i) {
        MainCategoryModel mainCategoryModel = this.data.get(i);
        reyclerViewHolder.mCategory.setText(mainCategoryModel.getCategory_name());
        Picasso picasso = Picasso.get();
        picasso.load("file:///android_asset/" + this.data.get(i).getImg()).into(reyclerViewHolder.mBackgroung);
        reyclerViewHolder.mDescription.setText(mainCategoryModel.getCategory_eng());
        reyclerViewHolder.mMainCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CategoryAdapter.this.mOnItemClickListener != null) {
                    CategoryAdapter.this.mOnItemClickListener.onItemClick(view, (MainCategoryModel) CategoryAdapter.this.data.get(i), i);
                }
            }
        });
//        if (i == 0) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_1_bg);
//        } else if (i == 1) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_2_bg);
//        } else if (i == 2) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_3_bg);
//        } else if (i == 3) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_4_bg);
//        } else if (i == 4) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_5_bg);
//        } else if (i == 5) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_6_bg);
//        } else if (i == 6) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_7_bg);
//        } else if (i == 7) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_8_bg);
//        } else if (i == 8) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_9_bg);
//        } else if (i == 9) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_10_bg);
//        } else if (i == 10) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_11_bg);
//        } else if (i == 11) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_12_bg);
//        } else if (i == 12) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_13_bg);
//        } else if (i == 13) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_8_bg);
//        } else if (i == 14) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_15_bg);
//        } else if (i == 15) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_1_bg);
//        } else if (i == 16) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_2_bg);
//        } else if (i == 17) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_3_bg);
//        } else if (i == 18) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_5_bg);
//        } else if (i == 19) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_2_bg);
//        } else if (i == 20) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_6_bg);
//        } else if (i == 21) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_6_bg);
//        } else if (i == 22) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_6_bg);
//        } else if (i == 23) {
//            reyclerViewHolder.mMainCont.setBackgroundResource(R.drawable.card_6_bg);
//        }
    }


    public class ReyclerViewHolder extends RecyclerView.ViewHolder {
        public ImageView mBackgroung;
        public TextView mCategory;
        public TextView mDescription;
        public LinearLayout mMainCont;

        private ReyclerViewHolder(View view) {
            super(view);
            this.mCategory = (TextView) view.findViewById(R.id.category_list_Category);
            this.mDescription = (TextView) view.findViewById(R.id.tvTitleDesc);
            this.mBackgroung = (ImageView) view.findViewById(R.id.category_list_background);
            this.mMainCont = (LinearLayout) view.findViewById(R.id.category_list);
        }
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
