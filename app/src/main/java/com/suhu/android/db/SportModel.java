package com.suhu.android.db;

/**
 * @author suhu
 * @data 2017/9/12.
 * @description
 */

public class SportModel {
    private String time;
    private String longitude_latitude;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLongitude_latitude() {
        return longitude_latitude;
    }

    public void setLongitude_latitude(String longitude_latitude) {
        this.longitude_latitude = longitude_latitude;
    }

    @Override
    public String toString() {
        return "SportModel{" +
                "time='" + time + '\'' +
                ", longitude_latitude='" + longitude_latitude + '\'' +
                '}';
    }
}
