package com.suhu.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.suhu.android.R;
import com.suhu.android.base.BaseSlidingActivity;
import com.suhu.android.db.SportModel;
import com.suhu.android.db.utils.TabConfig;
import com.suhu.android.db.utils.TableManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author suhu
 * @data 2017/9/13.
 * @description
 */

public class SQLActivity extends BaseSlidingActivity {
    @BindView(R.id.show)
    TextView show;

    private TableManager manager;

    @Override
    public int showContView() {
        return R.layout.activity_sql;
    }

    @Override
    public void setActionBar() {
        title.setText("数据库基本操作");
    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {
        manager = new TableManager();
    }


    @OnClick({R.id.insert, R.id.delete, R.id.update, R.id.query,R.id.query_if})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.insert:
                insert();
                break;
            case R.id.delete:
                //delete();
                deleteAll();
                break;
            case R.id.update:
                update();
                break;
            case R.id.query:
                queryAll();
                break;
            case R.id.query_if:
                query();
                break;
        }
    }

    private void delete() {
        manager.delete(TabConfig.Sport.TAB_NAME,TabConfig.Sport.LONGITUDE_LATITUDE,"suhu");
    }
    private void deleteAll(){
        manager.deleteAll(TabConfig.Sport.TAB_NAME);
    }

    private void update() {
        SportModel model = new SportModel();
        model.setTime(getTime());
        model.setLongitude_latitude("suhu");
        manager.update(TabConfig.Sport.TAB_NAME,TabConfig.Sport.LONGITUDE_LATITUDE,"119.00,35.00",model);
    }




    private void insert() {
        SportModel model = new SportModel();
        model.setTime(getTime());
        model.setLongitude_latitude("119.00,35.00");
        int a = manager.insert(TabConfig.Sport.TAB_NAME, model);
        if (a > 0) {
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void queryAll() {
        ArrayList<SportModel> list = manager.queryAll(TabConfig.Sport.TAB_NAME, SportModel.class);
        show.setText(list.toString());
    }

    private void query(){
        ArrayList<SportModel> list = manager.query(TabConfig.Sport.TAB_NAME, SportModel.class,TabConfig.Sport.LONGITUDE_LATITUDE,"suhu");
        show.setText(list.toString());
    }

    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
