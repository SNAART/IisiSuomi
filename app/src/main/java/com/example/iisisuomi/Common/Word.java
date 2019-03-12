package com.example.iisisuomi.Common;

public class Word {
    private String fin;
    private String eng;

    public Word(String fin, String eng) {
        this.fin = fin;
        this.eng = eng;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    @Override
    public String toString(){
        return fin;
    }
}
