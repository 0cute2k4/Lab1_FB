package com.thanhnvph33381.myapp123;

import java.util.HashMap;

public class Todo {
    private String id,title,content;

    public Todo(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Todo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public HashMap<String,Object> convertHashMapTo(){
        HashMap<String,Object> h = new HashMap<>();
        h.put("id",id);
        h.put("title",title);
        h.put("content",content);
        return h;

    }
}
