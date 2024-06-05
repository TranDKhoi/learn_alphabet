package com.learn_alphabet.activities.animal_sound;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.animal_sound.db.DatabaseHelper;
import com.learn_alphabet.activities.animal_sound.models.MainCategoryModel;
import com.learn_alphabet.adapter.CategoryAdapter;

import java.util.List;

public class AnimalSoundActivity extends AppCompatActivity {

    private List<MainCategoryModel> data;
    RelativeLayout gdprLayout;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_animal_sound);

        this.gdprLayout = findViewById(R.id.gdprLayout);

        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            databaseHelper.openDataBase();
            this.data = databaseHelper.getCategorieList();
        } catch (Exception ignore) {
        }

        CategoryAdapter categoryAdapter = new CategoryAdapter(this, R.layout.layout_category_list, this.data);
        categoryAdapter.setOnItemClickListener((view, mainCategoryModel, i) -> {
            MainCategoryModel mainCategoryModel2 = AnimalSoundActivity.this.data.get(i);
            Intent intent = new Intent(AnimalSoundActivity.this.getApplicationContext(), AnimalSoundDetailsActivity.class);
            intent.putExtra("Category", mainCategoryModel2.getCategory_name());
            intent.putExtra("id", mainCategoryModel2.getId());
            intent.putExtra("Category_Desc", mainCategoryModel2.getCategory_eng());
            AnimalSoundActivity.this.startActivity(intent);
        });


        findViewById(R.id.btn1).setOnClickListener(view -> {
            Intent intent = new Intent(AnimalSoundActivity.this.getApplicationContext(), AnimalSoundDetailsActivity.class);
            intent.putExtra("id", 1);
            intent.putExtra("category", "Wild animals");
            intent.putExtra("desc_eng", "Wild animals");
            AnimalSoundActivity.this.startActivity(intent);
        });
        findViewById(R.id.btn2).setOnClickListener(view -> {
            Intent intent = new Intent(AnimalSoundActivity.this.getApplicationContext(), AnimalSoundDetailsActivity.class);
            intent.putExtra("id", 2);
            intent.putExtra("category", "Pets");
            intent.putExtra("desc_eng", "Pets");
            AnimalSoundActivity.this.startActivity(intent);
        });
        findViewById(R.id.btn3).setOnClickListener(view -> {
            Intent intent = new Intent(AnimalSoundActivity.this.getApplicationContext(), AnimalSoundDetailsActivity.class);
            intent.putExtra("id", 3);
            intent.putExtra("category", "Farm Animals");
            intent.putExtra("desc_eng", "Farm Animals");
            AnimalSoundActivity.this.startActivity(intent);
        });
        findViewById(R.id.btn4).setOnClickListener(view -> {
            Intent intent = new Intent(AnimalSoundActivity.this.getApplicationContext(), AnimalSoundDetailsActivity.class);
            intent.putExtra("id", 4);
            intent.putExtra("category", "Birds");
            intent.putExtra("desc_eng", "Birds");
            AnimalSoundActivity.this.startActivity(intent);
        });

        findViewById(R.id.gdprLayout).setOnClickListener(v -> {
            finish();
        });
    }
}
