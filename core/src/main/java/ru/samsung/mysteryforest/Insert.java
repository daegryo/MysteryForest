package ru.samsung.mysteryforest;

public class Insert {
    // параметры тектового поля
    public float x;
    public float y;
    public float width;
    public float height;
    public boolean down = false; // boolean для увеличения или уменьшения  текстового поля

    // конструктор класса
    public Insert(float x, float y,float width, float height) {
        this.x  = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    //  изменение текстового поля
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
