package com.learn_alphabet.activities.animal_sound.models;


public class MainCategoryModel {
    public String category_ar;
    public String category_eng;
    public String category_name;
    public int id;
    public String img;

    public MainCategoryModel(int i, String str, String str2, String str3, String str4) {
        this.id = i;
        this.category_name = str;
        this.category_ar = str2;
        this.img = str3;
        this.category_eng = str4;
    }

    public String getCategory_name() {
        return this.category_name;
    }

    public void setCategory_name(String str) {
        this.category_name = str;
    }

    public String getCategory_ar() {
        return this.category_ar;
    }

    public void setCategory_ar(String str) {
        this.category_ar = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
    }

    public String getCategory_eng() {
        return this.category_eng;
    }

    public void setCategory_eng(String str) {
        this.category_eng = str;
    }
}
