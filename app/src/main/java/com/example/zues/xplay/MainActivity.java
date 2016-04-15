package com.example.zues.xplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.zues.xplay.Activity.BaseActivity;
import com.example.zues.xplay.Activity.LocationActivity;
import com.example.zues.xplay.Activity.SearchActivity;
import com.example.zues.xplay.fragment.HomeFragment;
import com.example.zues.xplay.fragment.SearchFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener,Toolbar.OnMenuItemClickListener,NavigationView.OnNavigationItemSelectedListener{

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
    private SearchFragment searchFragment;
    private static final String TAG = "Mainactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();

        if (homeFragment == null) homeFragment = new HomeFragment();
        addFragment(R.id.home_content, homeFragment);
        imgHome.setBackgroundResource(R.drawable.home_press);
        tabHome.setOnClickListener(this);
        tabSearch.setOnClickListener(this);
//        tabHome.setOnClickListener(this);
//        tabHome.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,R.string.open_navigation_drawer,R.string.close_navigation_drawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_home:
                if (homeFragment == null) homeFragment = new HomeFragment();
                setTabBackground();
                imgHome.setBackgroundResource(R.drawable.home_press);
                addFragment(R.id.home_content, homeFragment);
                break;
            case R.id.tab_search:
                if (searchFragment == null)searchFragment = new SearchFragment();
                setTabBackground();
                imgSearch.setBackgroundResource(R.drawable.search_press);
                addFragment(R.id.home_content, searchFragment);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        return true;
    }

    public void setToolbar() {
        toolbar.setTitle("Xplay");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setOnMenuItemClickListener(this);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LocationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setTabBackground(){
        imgHome.setBackgroundResource(R.drawable.home_normal);
        imgSearch.setBackgroundResource(R.drawable.search_normal);
        imgFriend.setBackgroundResource(R.drawable.group_normal);
        imgMine.setBackgroundResource(R.drawable.user_normal);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        //点击完之后将抽屉关闭
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
