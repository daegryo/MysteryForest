package ru.samsung.mysteryforest;

import java.util.ArrayList;
import java.util.List;

public class BackPack {
    List<Clues> content = new ArrayList<>();
    List<Clues> helpers = new ArrayList<>();

    public void add(Clues clues){
        content.add(clues);
    }
    public void addHelpers(Clues clues){
        helpers.add(clues);
    }
}
