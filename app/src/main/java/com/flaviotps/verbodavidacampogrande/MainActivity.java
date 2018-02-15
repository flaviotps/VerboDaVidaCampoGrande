package com.flaviotps.verbodavidacampogrande;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{


    private mFragmentManager fragmentManager;
    private ViewPager pager;
    List<ImageView> dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.pager);
        dots = new ArrayList<ImageView>();
        dots.add((ImageView) findViewById(R.id.dot1));
        dots.add((ImageView) findViewById(R.id.dot2));
        setupViewPager(pager);
    }


    private void setupViewPager(ViewPager viewPager) {

        fragmentManager = new mFragmentManager(getSupportFragmentManager());

        fragmentManager.AddFragment(new FragmentMenuPrincipal());
        fragmentManager.AddFragment(new FragmentMenuSecundario());
        viewPager.setAdapter(fragmentManager);
        viewPager.addOnPageChangeListener(this);


    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
            ChangePagerDots(position);
        }


        private void ChangePagerDots(int position){

            for(int i=0;i<dots.size();i++){
                if(i==position) {
                    dots.get(i).setImageResource(R.drawable.tab_indicator_selected);
                }else {
                    dots.get(i).setImageResource(R.drawable.tab_indicator_default);
                }
            }
        }




    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
