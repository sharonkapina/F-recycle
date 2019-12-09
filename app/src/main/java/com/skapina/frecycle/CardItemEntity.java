package com.skapina.frecycle;

import android.graphics.drawable.Drawable;

public class CardItemEntity {
    private Drawable Image;
    private String Name;

    public CardItemEntity(Drawable image, String name) {
        this.Image = image;
        this.Name = name;
    }

    public Drawable getImage() {
        return Image;
    }

    public void setImage(Drawable image) {
        this.Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}

