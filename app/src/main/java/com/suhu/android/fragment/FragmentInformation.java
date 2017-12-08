package com.suhu.android.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.suhu.android.R;
import com.suhu.android.adapter.MixAdapter;
import com.suhu.android.bean.Head;
import com.suhu.android.bean.One;
import com.suhu.android.bean.Three;
import com.suhu.android.bean.Two;
import com.suhu.android.bean.Visitable;
import com.suhu.android.listener.OnMixClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *@author suhu
 *@time 2017/12/7 11:15
 *http://blog.csdn.net/footballclub/article/details/46982115
*/

public class FragmentInformation extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    XRefreshView refresh;


    private Unbinder unbinder;

    private MixAdapter adapter;
    private List<Visitable> list;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, null);
        unbinder = ButterKnife.bind(this, view);
        recyclerView();
        listener();
        setData();
        return view;
    }

    private void setData() {
        Head.Bean bean1 = new Head.Bean(18,"山鸡");
        Head.Bean bean2 = new Head.Bean(26,"李刚");
        Head.Bean bean3 = new Head.Bean(17,"张三");
        Head.Bean bean4 = new Head.Bean(99,"李四");
        Head.Bean bean5 = new Head.Bean(66,"王五");
        Head.Bean bean6 = new Head.Bean(35,"赵六");
        Head.Bean bean7 = new Head.Bean(29,"码子");

        List<Head.Bean> beans = new ArrayList<>();
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        beans.add(bean6);
        beans.add(bean7);
        Head head = new Head();
        head.setListBean(beans);


        list.add(head);

        list.add(new One(18,"你好啊"));
        list.add(new Two("http://img.zcool.cn/community/017274582000cea84a0e282b576a32.jpg","http://img5.imgtn.bdimg.com/it/u=807605679,2027849210&fm=27&gp=0.jpg"));
        list.add(new Two("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=263610101,1781874146&fm=173&s=FF60A353C8AE669E819990820300B0E3&w=600&h=450&img.JPEG","https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1630708762,497755454&fm=173&s=9027D914C3A642B63D9C1CD50300C0A0&w=600&h=450&img.JPEG"));
        list.add(new Two("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2135045890,1049314841&fm=173&s=5C145F9CDABA109ECF4158070300F0C2&w=640&h=480&img.JPEG","https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1805875545,1650451374&fm=173&s=EA50438F5A323E9E41018C990300E091&w=600&h=450&img.JPEG"));
        list.add(new Three(49,"李四","http://img05.tooopen.com/images/20140326/sy_57640132134.jpg"));


        list.add(new One(18,"你好啊"));
        list.add(new Two("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=975996403,536698909&fm=173&s=83925785609753C6179D5C870300D021&w=640&h=480&img.JPEG","https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1567177510,1037025785&fm=173&s=849751DAC032BE7648DB4FBE0300D00F&w=640&h=480&img.JPEG"));
        list.add(new Three(49,"李四","http://img05.tooopen.com/images/20140326/sy_57640132134.jpg"));
        list.add(new One(18,"你好啊"));
        list.add(new Two("http://img.zcool.cn/community/017274582000cea84a0e282b576a32.jpg","https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2674632678,2684755585&fm=173&s=AA088E4F68EEED1FD80C1F7803009078&w=300&h=195&img.JPEG"));
        list.add(new Three(49,"李四","http://img05.tooopen.com/images/20140326/sy_57640132134.jpg"));
        list.add(new One(18,"你好啊"));
        list.add(new Two("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1947789812,488987527&fm=173&s=BB32718738F321869080428C03003081&w=640&h=288&img.JPEG","http://img5.imgtn.bdimg.com/it/u=807605679,2027849210&fm=27&gp=0.jpg"));
        list.add(new Two("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2674632678,2684755585&fm=173&s=AA088E4F68EEED1FD80C1F7803009078&w=300&h=195&img.JPEG","http://img5.imgtn.bdimg.com/it/u=807605679,2027849210&fm=27&gp=0.jpg"));
        list.add(new Three(49,"李四","http://img05.tooopen.com/images/20140326/sy_57640132134.jpg"));
        adapter.setList(list);
    }

    private void recyclerView() {
        list = new ArrayList<>();
        adapter = new MixAdapter(list,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(new OnMixClickListener() {
            @Override
            public void onClick(Object visitable, int position, View view) {
                if (visitable instanceof One){
                    One one = new One(999,"苏虎");
                    adapter.portionNotify(position,one);

                    Toast.makeText(getActivity(),position+""+visitable.toString(),Toast.LENGTH_SHORT).show();
                }

                if (visitable instanceof Two){
                    Two two = new Two("http://img1.imgtn.bdimg.com/it/u=1283738000,2026766525&fm=200&gp=0.jpg","http://img2.imgtn.bdimg.com/it/u=3727774930,3759071354&fm=11&gp=0.jpg");
                    adapter.portionNotify(position,two);

                    Toast.makeText(getActivity(),position+""+visitable.toString(),Toast.LENGTH_SHORT).show();
                }




            }
        });
    }

    private void listener() {
        refresh.setPullLoadEnable(true);
        refresh.setMoveForHorizontal(true);
        refresh.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.stopRefresh();
                        //refresh.setLoadComplete(false);
                    }
                },500);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.stopLoadMore();
                        //refresh.setLoadComplete(true);
                    }
                },1000);
            }
        });
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
