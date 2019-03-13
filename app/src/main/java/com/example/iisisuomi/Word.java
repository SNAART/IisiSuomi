package com.example.iisisuomi;

public class Word {

    private String word;
    private Chapter chapter;
    private String description;

    public Word(String wordInFin,String description, Chapter chapter) {
        this.word = wordInFin;
        this.chapter = chapter;
        this.description = description;
    }

    public String getWord() {
        return word;
    }

    public String getDescription() {
        return description;
    }

    public Chapter getChapter() {
        return chapter;
    }

}
