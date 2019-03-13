package com.example.iisisuomi;

import android.support.v7.app.AppCompatActivity;

import java.util.*;
import java.io.*;

public class App {
    private List<Chapter> chapterList;

    public Chapter getRandomChapter () {
        return chapterList.get((int)(Math.random() * chapterList.size()));
    }

    public Word getRandomWordBaseOnChapter(List<String> chapter) {
        while(true) {
            Chapter tmp = getRandomChapter();
            if (chapter.contains(tmp.getName())) {
                return tmp.getRandomWord();
            }
        }
    }

    public Word getRandomWord() {
        return chapterList.get(0).getWordList().get((int)(Math.random() * (chapterList.get(0).getWordList().size())));
        //return chapterList.get(0).getWordList().get(0);
    }

    public Word getWord(String s) {
        for(int i = 0; i < chapterList.size(); i++) {
            if (chapterList.get(i).isInChapter(s)) {
                return chapterList.get(i).getWord(s);
            }
        }
        return null;
    }

    public Chapter getChapter(String name) {
        for(int i = 0; i < chapterList.size(); i++) {
            if (chapterList.get(i).getName().equals(name)) {
                return chapterList.get(i);
            }
        }
        return null;
    }

    public App(AppCompatActivity mainActivity) {

        chapterList = new ArrayList<>();
        chapterList.add(new Chapter("1"));
        chapterList.add(new Chapter("2"));
        chapterList.add(new Chapter("3"));
        chapterList.add(new Chapter("4"));
        chapterList.add(new Chapter("5"));
        try {
            InputStream iS = mainActivity.getAssets().open("chapter1.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            while(true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] components = line.split(";");
                if (components.length == 2) {
                    chapterList.get(0).addWord(new Word(components[0], components[1], chapterList.get(0)));
                }
            }
        } catch (IOException e) {
            System.out.println("no file 1");
        }
        try {
            InputStream iS = mainActivity.getAssets().open("chapter2.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            while(true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] components = line.split(";");
                if (components.length == 2) {
                    chapterList.get(1).addWord(new Word(components[0], components[1], chapterList.get(1)));
                }
            }
        } catch (IOException e) {
            System.out.println("no file 2");
        }

        try {
            InputStream iS = mainActivity.getAssets().open("chapter3.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            while(true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] components = line.split(";");
                if (components.length == 2) {
                    chapterList.get(2).addWord(new Word(components[0], components[1], chapterList.get(2)));
                }
            }
        } catch (IOException e) {
            System.out.println("no file 3");
        }

        try {
            InputStream iS = mainActivity.getAssets().open("chapter4.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            while(true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] components = line.split(";");
                if (components.length == 2) {
                    chapterList.get(3).addWord(new Word(components[0], components[1], chapterList.get(3)));
                }
            }
        } catch (IOException e) {
            System.out.println("no file 4");
        }

        try {
            InputStream iS = mainActivity.getAssets().open("chapter5.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            while(true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] components = line.split(";");
                if (components.length == 2) {
                    chapterList.get(4).addWord(new Word(components[0], components[1], chapterList.get(4)));
                }
            }
        } catch (IOException e) {
            System.out.println("no file 5");
        }
    }



}
