package com.suhu.android.bean;

/**
 * @author suhu
 * @data 2017/12/7.
 * @description
 */

public class One implements Visitable{

    private int age;
    private String name;

    public One(int age, String name) {
        this.age = age;
        this.name = name;
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

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    @Override
    public String toString() {
        return "One{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
