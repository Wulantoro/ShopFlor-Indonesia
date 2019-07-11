package com.example.shopfloor.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shopfloor.Adapter.MainPagerAdapter;
import com.example.shopfloor.Adapter.SuccDocAdapter;
import com.example.shopfloor.Fragment.CriteriaFragment;
import com.example.shopfloor.Fragment.InfoFragment;
import com.example.shopfloor.Fragment.RejectFragment;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.R;

public class DetailSuccDocActivity extends AppCompatActivity {

    private Header header;
    private SuccDocAdapter adapter;
    private TextView tvNo_doc_Frag;
    private ViewPager viewPager;
    private InfoFragment infoFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_succ_doc);

        tvNo_doc_Frag = findViewById(R.id.tvNo_doc_Frag);
        viewPager = findViewById(R.id.viewPager);

        CriteriaFragment criteriaFragment = new CriteriaFragment();
        adapter = new SuccDocAdapter(this);
        header = getIntent().getParcelableExtra("key_succ");




        initView();
    }

    private void initView() {

        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        //setting tablayout
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mainPagerAdapter.addFragments(new InfoFragment(), getString(R.string.info));
        mainPagerAdapter.addFragments(new CriteriaFragment(), getString(R.string.criteria));
        mainPagerAdapter.addFragments(new RejectFragment(), getString(R.string.reject));
        viewPager.setAdapter(mainPagerAdapter);
    }
}
