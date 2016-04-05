package com.example.zues.xplay.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.zues.xplay.R;
import com.example.zues.xplay.adapter.ViewpagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zues on 2016/4/5.
 */
public class HomeFragment extends Fragment implements View.OnTouchListener{
    private ViewPager viewpager;
    private ListView listItem;

    /**当前位置*/
    int curPosition = 5000;
    /**是否手动换图*/
    private boolean isTouch = false;
    /**每一条list的转载容器*/
    private List<Map<String,Object>> list;
    private Map<String, Object> map;
    /**每一条list中的map对应的key,也就是from*/
    String[] keyFrom = {"img","title","type"};
    /**每一个key对应在布局xml中的位置*/
    int[] toWhere = {R.id.item_image,R.id.item_title,R.id.item_type};

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewpager.setCurrentItem(curPosition);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        listItem = (ListView) view.findViewById(R.id.gamelist);
        List<Integer> list = new ArrayList<Integer>();
        list.add(R.drawable.banner1);
        list.add(R.drawable.banner2);
        list.add(R.drawable.banner3);
        ViewpagerAdapter adapter = new ViewpagerAdapter(getActivity(), list);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(curPosition);
        //设置当手触摸到屏幕的时候banner停止滚动
        viewpager.setOnTouchListener(this);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        whenTouch();

        listItem.setAdapter(new SimpleAdapter(getActivity(),getdata(),R.layout.home_list_item,keyFrom,toWhere));
        return view;
    }


    private void whenTouch(){
        Thread change = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!isTouch) {
                        curPosition++;
                        handler.sendEmptyMessage(0);
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        change.start();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                isTouch = true;
                break;
            case MotionEvent.ACTION_UP:
                isTouch = false;
                break;
            default:
                break;
        }

        return false;
    }

    private List<Map<String,Object>> getdata(){
        list = new ArrayList<Map<String,Object>>();
        map = new HashMap<String ,Object>();
        for (int i=0;i<20;i++){
            map.put("img",R.drawable.banner1);
            map.put("title","翻滚吧！海贼王");
            map.put("type","冒险");
            list.add(map);
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
