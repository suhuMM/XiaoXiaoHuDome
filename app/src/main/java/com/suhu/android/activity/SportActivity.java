package com.suhu.android.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.suhu.android.R;
import com.suhu.android.base.activity.BaseTitleActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/9 0009.
 */

public class SportActivity extends BaseTitleActivity implements LocationSource, AMapLocationListener {
    @BindView(R.id.map)
    MapView mapView;
    private AMap aMap;
    private LinkedList<LatLng> latLngList = new LinkedList<>();


    private int WRITE_COARSE_LOCATION_REQUEST_CODE = 0;
    private MyLocationStyle myLocationStyle;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private LocationSource.OnLocationChangedListener mListener;
    private PolylineOptions polylineOptions;
    private Polyline polyline;

    @Override
    public int showContView() {
        return R.layout.activity_sport;
    }

    @Override
    public void setActionBar() {
        title.setText("运动");
        TextView tv = new TextView(this);
        tv.setText("保存并退出");
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(14);
        right.addView(tv);
    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    WRITE_COARSE_LOCATION_REQUEST_CODE);

        } else {
            setLocationStyle();
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null) {
            mapView.onDestroy();
        }
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_COARSE_LOCATION_REQUEST_CODE) {
            setLocationStyle();
        }
    }


    /**
     * @param
     * @method 设置定位信息
     * @author suhu
     * @time 2017/9/10 0010 16:14
     */
    private void setLocationStyle() {
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(2000);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        myLocationStyle.showMyLocation(true);

        polylineOptions = new PolylineOptions();
        polylineOptions.width(10);
        polylineOptions.color(Color.RED);
        polylineOptions.zIndex(17);
        //设置折线边框样式为虚线
        //polylineOptions.setDottedLine(true);

        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);




    }


    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mLocationClient == null) {
            //初始化定位
            mLocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mLocationClient.setLocationListener(this);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.startLocation();//启动定位
        }
    }

    @Override
    public void deactivate() {

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);
                LatLng latLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                if (latLngList.size() > 0) {
                    polylineOptions.add(latLngList.getLast(), latLng);
                    if (polyline ==null){
                        polyline = aMap.addPolyline(polylineOptions);
                    }else {
                        polyline.setPoints(polylineOptions.getPoints());
                    }

                }else {
                    MarkerOptions markerOption = new MarkerOptions();
                    markerOption.position(latLng);
                    markerOption.draggable(false);
                    markerOption.zIndex(17);
                    markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(),R.drawable.location)));
                    aMap.addMarker(markerOption);

                }
                latLngList.addLast(latLng);


            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.i("map", errText);
                Toast.makeText(this, errText, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    @OnClick(R.id.right)
    public void onViewClicked(View view){
        super.onViewClicked(view);
        switch (view.getId()){
            case R.id.right:
                saveData();
                break;
            default:
        }

    }

    private void saveData(){
        getTime();
    }

    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
