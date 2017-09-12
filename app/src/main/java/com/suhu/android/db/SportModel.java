package com.suhu.android.db;

/**
 * @author suhu
 * @data 2017/9/12.
 * @description
 */

public class SportModel {
    private String SportTime;
    private String latLngString;

    public String getSportTime() {
        return SportTime;
    }

    public void setSportTime(String sportTime) {
        SportTime = sportTime;
    }

    public String getLatLngString() {
        return latLngString;
    }

    public void setLatLngString(String latLngString) {
        this.latLngString = latLngString;
    }
}
