package eu.szwiec.pixelperfect;

import android.graphics.drawable.Drawable;

/**
 * Created by szwiec on 28/03/2017.
 */

public class Item {
    private String title, description, score;
    private boolean isOrderFree;
    private Drawable image;

    public Item() {
    }

    public Item(String title, String description, String score, boolean isOrderFree, Drawable image) {
        this.title = title;
        this.description = description;
        this.score = score;
        this.isOrderFree = isOrderFree;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOrderFree() {
        return isOrderFree;
    }

    public void setOrderFree(boolean orderFree) {
        isOrderFree = orderFree;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}