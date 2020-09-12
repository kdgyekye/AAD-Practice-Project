package com.example.Leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class Main2Activity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MainPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = findViewById(R.id.viewpager);
        adapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout= findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.menu_submit)
                .getActionView()
                .findViewById(R.id.btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Main2Activity.this, SubmitActivity.class));
                    }
                });

        return true;
    }
}
