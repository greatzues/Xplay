package com.example.zues.xplay;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zues.xplay.Activity.BaseActivity;
import com.example.zues.xplay.fragment.HomeFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.title_xplay)
    TextView titleXplay;
    @Bind(R.id.more_choose)
    ImageView moreChoose;
    @Bind(R.id.img_home)
    ImageView imgHome;
    @Bind(R.id.tab_home)
    RelativeLayout tabHome;
    @Bind(R.id.img_search)
    ImageView imgSearch;
    @Bind(R.id.tab_search)
    RelativeLayout tabSearch;
    @Bind(R.id.img_friend)
    ImageView imgFriend;
    @Bind(R.id.tab_friend)
    RelativeLayout tabFriend;
    @Bind(R.id.img_mine)
    ImageView imgMine;
    @Bind(R.id.tab_mine)
    RelativeLayout tabMine;
    @Bind(R.id.home_content)
    LinearLayout homeContent;

    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (homeFragment == null)homeFragment = new HomeFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.home_content, homeFragment);
        transaction.commit();
        imgHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:
                if (homeFragment == null)homeFragment = new HomeFragment();
                addFragment(R.id.home_content,homeFragment);
                break;
        }

    }
}
