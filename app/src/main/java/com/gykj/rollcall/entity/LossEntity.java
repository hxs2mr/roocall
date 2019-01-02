package com.gykj.rollcall.entity;

/**
 * desc   : 报损实体类
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2019/1/210:02
 * version: 1.0
 */
public class LossEntity {

    private String avater;
    private String people;
    private String items;
    private String date;
    private int type;


    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
