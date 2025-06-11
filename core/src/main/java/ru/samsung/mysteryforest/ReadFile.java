package ru.samsung.mysteryforest;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadFile {
    private String filePath;
    private int num;
    private List<String> array = new ArrayList<>();

    public ReadFile(String filePath, int num) {
        this.filePath = filePath;
        this.num = num;
    }

    public List<String> reader() {
        FileHandle file = Gdx.files.internal(filePath);  // Используем FileHandle
        if (!file.exists()) {
            Gdx.app.error("ReadFile", "Файл не найден: " + filePath);
            return array;
        }

        try (BufferedReader reader = new BufferedReader(file.reader())) {
            String line;
            StringBuilder str = new StringBuilder();
            int n = 0;

            while ((line = reader.readLine()) != null) {
                if (n <= num) {
                    str.append("\n").append(line);
                    n++;
                }
                if (n == num) {
                    array.add(str.toString());
                    n = 0;
                    str = new StringBuilder();
                }
            }
        } catch (IOException e) {
            Gdx.app.error("ReadFile", "Ошибка при чтении файла: " + filePath, e);
        }
        return array;
    }
}
