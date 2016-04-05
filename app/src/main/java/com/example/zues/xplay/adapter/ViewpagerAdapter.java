package com.example.zues.xplay.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zues on 2016/4/5.
 */
public class ViewpagerAdapter extends PagerAdapter {
    private List<View> mList;

    public ViewpagerAdapter(Context context ,List<Integer> list) {
        mList = new ArrayList<View>();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        for (int i =0;i<list.size();i++){
            ImageView mImageView = new ImageView(context);
            mImageView.setLayoutParams(params);
            mImageView.setBackgroundResource(list.get(i));
            mList.add(mImageView);
        }
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    /**添加下一页*/
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position %mList.size();
        if (mList.get(position).getParent() != null){
            container.removeView(mList.get(position));
        }
        container.addView(mList.get(position));
        return mList.get(position);
    }

    /**移除上一张图片*/
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position = position%mList.size();
        container.removeView(mList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
