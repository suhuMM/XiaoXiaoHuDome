package com.suhu.android.bean;

/**
 * @author suhu
 * @data 2017/12/7.
 * @description
 */

public class Two implements Visitable{

    private String url;
    private String icon;

    public Two(String url, String icon) {
        this.url = url;
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    @Override
    public String toString() {
        return "Two{" +
                "url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
