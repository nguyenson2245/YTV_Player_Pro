package com.techBMT.YTV_Player_Pro.activity2file;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.techBMT.YTV_Player_Pro.R;
import com.techBMT.YTV_Player_Pro.adapter.TabLayoutAdapter;
import com.techBMT.YTV_Player_Pro.fragment.Contact_Fragment;
import com.techBMT.YTV_Player_Pro.fragment.Privacy_Fragment;
import com.techBMT.YTV_Player_Pro.fragment.Rate_Fragment;
import com.techBMT.YTV_Player_Pro.fragment_file.Excel_Fragment;
import com.techBMT.YTV_Player_Pro.fragment_file.PDF_Fragment;
import com.techBMT.YTV_Player_Pro.fragment_file.Ptt_Fragment;
import com.techBMT.YTV_Player_Pro.fragment_file.Word_Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_File extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    TabLayoutAdapter pageStateAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.techBMT.YTV_Player_Pro.R.layout.activity_main_file);

        initView();
        intiTabLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.outapp,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.outAPP){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void intiTabLayout() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> screens = new ArrayList<>();
        screens.add(new PDF_Fragment());
        screens.add(new Word_Fragment());
        screens.add(new Excel_Fragment());
        screens.add(new Ptt_Fragment());
        pageStateAdapter = new TabLayoutAdapter(fragmentManager, getLifecycle());
        pageStateAdapter.addScreen(screens);
        viewPager2.setOffscreenPageLimit(4);

        viewPager2.setAdapter(pageStateAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("PDF"));
        tabLayout.addTab(tabLayout.newTab().setText("WORD"));
        tabLayout.addTab(tabLayout.newTab().setText("EXCEL"));
        tabLayout.addTab(tabLayout.newTab().setText("PTT"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }


    private void initView() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewpager2);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}