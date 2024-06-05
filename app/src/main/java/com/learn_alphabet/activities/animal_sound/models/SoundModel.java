package com.learn_alphabet.activities.animal_sound.models;


public class SoundModel {
    public String category_id;
    public int id;
    public String img;
    public String name;
    public String name_ar;
    public String name_eng;
    public String sound_raw;

    public SoundModel(int i, String str, String str2, String str3, String str4, String str5, String str6) {
        this.id = i;
        this.category_id = str;
        this.name = str2;
        this.sound_raw = str3;
        this.img = str4;
        this.name_eng = str5;
        this.name_ar = str6;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getCategory_id() {
        return this.category_id;
    }

    public void setCategory_id(String str) {
        this.category_id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSound_raw() {
        return this.sound_raw;
    }

    public void setSound_raw(String str) {
        this.sound_raw = str;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
    }

    public String getName_eng() {
        return this.name_eng;
    }

    public void setName_eng(String str) {
        this.name_eng = str;
    }

    public String getName_ar() {
        return this.name_ar;
    }

    public void setName_ar(String str) {
        this.name_ar = str;
    }
}
