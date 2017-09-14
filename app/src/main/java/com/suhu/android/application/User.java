package com.suhu.android.application;

/**
 * @author suhu
 * @data 2017/9/14.
 * @description
 */

public class User {

    /**
     * 10086
     * test
     * YeBfudEd4kx52Qv/vSR1F4Nw32R3eixTPXxyUXopBsUzPNc5LVg5qbHf6jzGd+3q7gveb6sBTvQrM02kBsHLJA==
     *
     * 3argexb630q4e
     * suhu
     * 6JvpriAko02wYBlh3yTIjD8S/nVdu+xyK4vZkxev90Z4gc7fI1Qv89w6dWL6wb/XDXEiBS2o8gZmGFWW7ddH9TaHBQHGCCPDEnOjvcD3aCk=
     * */
//    private String userId = "10086";
//    private String token = "YeBfudEd4kx52Qv/vSR1F4Nw32R3eixTPXxyUXopBsUzPNc5LVg5qbHf6jzGd+3q7gveb6sBTvQrM02kBsHLJA==";
//    private String url = "http://img1.imgtn.bdimg.com/it/u=806806176,4079081363&fm=27&gp=0.jpg";

    private String userId = "3argexb630q4e";
    private String token = "6JvpriAko02wYBlh3yTIjD8S/nVdu+xyK4vZkxev90Z4gc7fI1Qv89w6dWL6wb/XDXEiBS2o8gZmGFWW7ddH9TaHBQHGCCPDEnOjvcD3aCk=";
    private String url = "http://img1.imgtn.bdimg.com/it/u=806806176,4079081363&fm=27&gp=0.jpg";

//    private String userId = "3argexb630q4e";
//    private String token = "0L3LhZvmEhmeY/O6z+jqkDdw425PLr6tFkyLhkSfXOmlDCw9tNfLfUcyTHdC9vWaTz2z6Hb0nwEYYnzkBXdBuw==";

    private static User userInfo = new User();

    public static User getInstance(){
        return userInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
