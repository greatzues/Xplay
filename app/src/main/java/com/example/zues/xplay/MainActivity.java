package com.example.zues.xplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.zues.xplay.Activity.BaseActivity;
import com.example.zues.xplay.Activity.LocationActivity;
import com.example.zues.xplay.Activity.SearchActivity;
import com.example.zues.xplay.fragment.HomeFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

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
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setLogo(R.drawable.location);
        toolbar.setTitle("Xplay");
        toolbar.setTitleTextColor(R.color.colorAppName);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LocationActivity.class);
                startActivity(intent);
            }
        });

        if (homeFragment == null){
            homeFragment = new HomeFragment();
            addFragment(R.id.home_content,homeFragment);
        }

        imgHome.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home:
                if (homeFragment == null) homeFragment = new HomeFragment();
                addFragment(R.id.home_content, homeFragment);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }
}
