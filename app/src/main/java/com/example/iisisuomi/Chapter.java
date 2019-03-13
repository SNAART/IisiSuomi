package com.example.iisisuomi;

import java.util.*;

public class Chapter {

    private String name;
    private List<Word> wordList;

    public Word getRandomWord() {
        return wordList.get((int)(Math.random() * wordList.size()));
    }

    public Chapter(String name) {
        this.name = name;
        wordList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean addWord(Word word) {
        return wordList.add(word);
    }

    public boolean isInChapter(String word) {
        for(int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getWord().equals(word)) {
                return true;
            }
        }
        return false;
    }

    public Word getWord(String word) {
        for(int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getWord().equals(word)) {
                return wordList.get(i);
            }
        }
        return null;
    }

    public List<Word> getWordList() {
        return wordList;
    }
}