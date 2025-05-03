package ru.samsung.mysteryforest;

public class Insert {
    public float x = 970f;
    public float y = 300f;
    public float width;
    public float height;
    public boolean down = false;

    public Insert(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void moveUp(){
        if (down && width >= 0.0001f){
            if (width > 1) {
                width /= 1.1f;
                height /= 1.1f;
            }
        }
        if (!down && width <= 400) {
            width *= 1.1f;
            height *= 1.1f;
        }

    }


}
