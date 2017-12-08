package com.suhu.android.bean;

import java.util.List;

/**
 * @author suhu
 * @data 2017/12/8.
 * @description
 */

public class Head implements Visitable{
    private List<Bean> listBean;

    public Head() {
    }

    public Head(List<Bean> listBean) {
        this.listBean = listBean;
    }

    public List<Bean> getListBean() {
        return listBean;
    }

    public void setListBean(List<Bean> listBean) {
        this.listBean = listBean;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }


    public static class Bean{
        private int age;
        private String name;

        public Bean() {
        }

        public Bean(int age, String name) {
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
    }





}
