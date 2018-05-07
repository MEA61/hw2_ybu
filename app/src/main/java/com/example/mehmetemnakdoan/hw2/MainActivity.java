package com.example.mehmetemnakdoan.hw2;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.example.mehmetemnakdoan.hw2.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TabLayout tabLayout;
    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;
    private int[] tabIcons = {
            R.drawable.icon_food,
            R.drawable.anno_icon,
            R.drawable.news_icon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());



        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabFragment1(), "Food List");
        adapter.addFragment(new TabFragment2(), "Announcement");
        adapter.addFragment(new TabFragment3(), "News");
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }
}