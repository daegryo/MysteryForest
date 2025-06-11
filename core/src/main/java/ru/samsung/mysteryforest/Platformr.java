package ru.samsung.mysteryforest;

public class Platformr {
    public float x;
    public float y;
    public float width;
    public float height;
    public float stepX;
    public float stepY;
    boolean show = true;

    public Platformr(float x, float y, float width, float height, float stepX, float stepY) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.stepX = stepX;
        this.stepY = stepY;
    }

    public void move() {
        x += stepX;

    }

    public void jump() {

        y += stepY;
    }


    public void down() {
        y -= stepY;
    }
}
