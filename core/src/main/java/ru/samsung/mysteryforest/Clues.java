package ru.samsung.mysteryforest;

import java.util.Objects;

public class Clues {
    public float x, y, width, height; // параметры улики
    boolean takeIt = false; // взята ли улика
    String path; // путь до изображения улики
    boolean stopShow = false; // прекратить показывать улику

    // конструктор улики
    public Clues(float x, float y, float width, float height, String path) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.path = path;

    }
    // перемещение улики
    public void move() {
        if (!takeIt) {
            {
                if (width < 400) {
                    width *= 1.5f;
                    height *= 1.5f;

                }
                if (x > 626 && y > 272) {
                    x /= 1.5f;
                    y /= 1.5f;
                }
            }
        }
        else{
            if(width > 0.01f){
                width /= 1.5f;
                height /= 1.5f;
            }
            else {
                x = 234;
                y = 15;

                if (Objects.equals(path, "bg/card.png")) {
                    stopShow = true;

                }
            }

        }

    }
}
