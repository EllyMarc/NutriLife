package com.unir.av1_pdm.models;

import java.io.Serializable;

public class Quote implements Serializable {

    public Quote(String id, String Author, String text){
        this.id = id;
        this.author = author;
        this.en=text;
    }

    private String id;
    private String author;
    private String en;

    public String getId(){
        return this.id;
    }
    public String getAuthor(){
        return this.author;
    }
    public String getText(){
        return this.en;
    }


}
