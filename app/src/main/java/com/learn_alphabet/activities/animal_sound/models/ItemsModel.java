package com.learn_alphabet.activities.animal_sound.models;


public class ItemsModel {
    public String category_id;
    public String desc;
    public String desc2;
    public String id;
    public String img;
    public String name;
    public String sound_raw;

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
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

    public String getDesc2() {
        return this.desc2;
    }

    public void setDesc2(String str) {
        this.desc2 = str;
    }
}
