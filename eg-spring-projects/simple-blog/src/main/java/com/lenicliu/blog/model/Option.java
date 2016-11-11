package com.lenicliu.blog.model;

/**
 * Created by lenicliu on 11/10/16.
 */
public class Option {
    private String name;
    private String value;

    public Option() {
    }

    public Option(String name, String value) {
        setName(name);
        setValue(value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getInt() {
        try {
            return Integer.parseInt(getValue());
        } catch (NumberFormatException ignore) {
            return 0;
        }
    }
}