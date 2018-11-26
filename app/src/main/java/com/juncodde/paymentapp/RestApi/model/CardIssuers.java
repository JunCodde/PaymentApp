package com.juncodde.paymentapp.RestApi.model;

public class CardIssuers {

    private String id, name, secure_thumbnail;

    public CardIssuers(String id, String name, String secure_thumbnail) {
        this.id = id;
        this.name = name;
        this.secure_thumbnail = secure_thumbnail;
    }

    public CardIssuers() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecure_thumbnail() {
        return secure_thumbnail;
    }

    public void setSecure_thumbnail(String secure_thumbnail) {
        this.secure_thumbnail = secure_thumbnail;
    }
}
