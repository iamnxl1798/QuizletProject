package com.example.quizlet.Model;

public class ThuMucHoc {
    private String title, author;
    private int avatar;

    public ThuMucHoc() {
    }

    public ThuMucHoc(String title, String author, int avatar) {
        this.title = title;
        this.author = author;
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
