package ru.samsung.mysteryforest;

import java.util.ArrayList;
import java.util.List;

public class BackPack {
    // списки для улик и устройств
    List<Clues> content = new ArrayList<>();
    List<Clues> helpers = new ArrayList<>();

    public void add(Clues clues){
        content.add(clues);
    } // добавление улики в список
    public void addHelpers(Clues clues){
        helpers.add(clues);
    } // добавление устройства в список
}
