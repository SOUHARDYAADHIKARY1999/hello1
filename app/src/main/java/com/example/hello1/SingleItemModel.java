package com.example.hello1;

public class SingleItemModel {
    private String background_url;
    private boolean free;
    private String name;
    private String time;
    private String url;

    public SingleItemModel() {
    }

    public SingleItemModel(String string2, String string3, String string4, String string5, boolean bl) {
        this.name = string2;
        this.url = string3;
        this.background_url = string4;
        this.time = string5;
        this.free = bl;
    }

    public String getBackground_url() {
        return this.background_url;
    }

    public String getName() {
        return this.name;
    }

    public String getTime() {
        return this.time;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isFree() {
        return this.free;
    }

    public void setBackground_url(String string2) {
        this.background_url = string2;
    }

    public void setFree(boolean bl) {
        this.free = bl;
    }

    public void setName(String string2) {
        this.name = string2;
    }

    public void setTime(String string2) {
        this.time = string2;
    }

    public void setUrl(String string2) {
        this.url = string2;
    }
}
