package com.example.foodapp.activities.category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodapp.R;
import com.example.foodapp.activities.main.MainActivity;
import com.example.foodapp.adapters.ViewPagerCategoryAdapter;
import com.example.foodapp.models.Categories;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        viewPager = findViewById(R.id.viewPager);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);

        initActionBar();
        initIntent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        List<Categories.Category> categories = (List<Categories.Category>) intent.getSerializableExtra(MainActivity.EXTRA_CATEGORY);
        int position = intent.getIntExtra(MainActivity.EXTRA_POSITION, 0);

        ViewPagerCategoryAdapter adapter = new ViewPagerCategoryAdapter(getSupportFragmentManager(), categories);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position, true);
        adapter.notifyDataSetChanged();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
