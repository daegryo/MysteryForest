package ru.samsung.mysteryforest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public String filePath;
    public int num;
    List<String> array = new ArrayList<>();
    int n = 0;
    String str = "";
//    public String[] array = {"111"};

    public ReadFile(String filePath, int num) {
        this.filePath = filePath;
        this.num = num;
    }

    public List<String> reader() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (n <= num){
                    str = str + "\n " + line;
                    n++;
                }
                if (n == num) {
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
