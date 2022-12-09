package com.example.keshe.bean;

import java.io.Serializable;

public class ActicleBean implements Serializable {
    private String acticleID;
    private String title;
    private String content;
    private int acticlePic;
    private String author;

    public ActicleBean(String acticleID, String title, String content, int acticlePic, String author) {
        this.acticleID = acticleID;
        this.title = title;
        this.content = content;
        this.acticlePic = acticlePic;
        this.author = author;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public int getActiclePic() {
        return acticlePic;
    }



    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getActicleID() {
        return acticleID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setActicleID(String acticleID) {
        this.acticleID = acticleID;
    }

    public void setActiclePic(int acticlePic) {
        this.acticlePic = acticlePic;
    }
}
