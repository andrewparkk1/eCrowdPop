package com.example.ecrowdpop.Model;

public class Category {
    private String category;
    private String postid;

    public Category() {

    }

    public Category(String category, String postid) {
        this.category = category;
        this.postid = postid;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
