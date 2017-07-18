package com.liumapp.DNSBee.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public class ZonesFile {

    private String text;

    private String name;

    private String user;

    /**
     * 1: public 2:personal
     */
    private int type;

    private int id;

    public String getName() {
        return name;
    }

    public ZonesFile setName(String name) {
        this.name = name;
        return this;
    }

    public String getText() {
        return text;
    }

    public ZonesFile setText(String text) {
        this.text = text;
        return this;
    }

    public String getUser() {
        return user;
    }

    public ZonesFile setUser(String user) {
        this.user = user;
        return this;
    }

    public int getId() {
        return id;
    }

    public ZonesFile setId(int id) {
        this.id = id;
        return this;
    }

    public int getType() {
        return type;
    }

    public ZonesFile setType(int type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
