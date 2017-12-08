package com.suhu.android.bean;

/**
 * @author suhu
 * @data 2017/12/7.
 * @description
 */

public class Three implements Visitable{

    private int age;
    private String name;
    private String url;

    public Three(int age, String name, String url) {
        this.age = age;
        this.name = name;
        this.url = url;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    @Override
    public String toString() {
        return "Three{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
