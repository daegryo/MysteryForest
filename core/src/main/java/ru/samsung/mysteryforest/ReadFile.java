package ru.samsung.mysteryforest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public String filePath;
    List<String> array = new ArrayList<>();
    int n = 0;
    String str = "";
//    public String[] array = {"111"};

    public ReadFile(String filePath) {
        this.filePath = filePath;
    }

    public List<String> reader() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (n <= 7){
                    str = str + "\n " + line;
                    n++;
                }
                if (n == 7) {
                    array.add(str);
                    System.out.println(str);
                    n = 0;
                    str = "";
                }

            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        System.out.println(array.size());
        return array;
    }
}
