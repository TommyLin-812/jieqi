package com.example.keshe.bean;

import java.io.Serializable;

public class ArticleBean implements Serializable {
    private String articleID;
    private String title;
    private String content;
    private int articlePic;
    private String author;

    public ArticleBean(String articleID, String title, String content, int articlePic, String author) {
        this.articleID = articleID;
        this.title = title;
        this.content = content;
        this.articlePic = articlePic;
        this.author = author;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public int getArticlePic() {
        return articlePic;
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public void setArticlePic(int articlePic) {
        this.articlePic = articlePic;
    }
}
