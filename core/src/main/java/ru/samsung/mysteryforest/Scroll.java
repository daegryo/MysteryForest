package ru.samsung.mysteryforest;

public class Scroll {
    public float width = 1000;
    public float height = 1000;
    public float x = 300;
    public float y = 70;

    public void move(){
        if (width >= 200) {
            width = width / 1.1f;
            height = height / 1.1f;
        }
        if (y >= 10) {
            x -= 40;
            y -= 10;
        }
    }
}
