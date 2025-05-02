package ru.samsung.mysteryforest;

public class Insert {
    public float x = 970f;
    public float y = 300f;
    public float width = 1f;
    public float height = 1f;
    public boolean down = false;

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
