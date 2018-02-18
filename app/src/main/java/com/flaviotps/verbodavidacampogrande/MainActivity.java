package com.flaviotps.verbodavidacampogrande;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    List<ImageView> dots;
    private mFragmentManager fragmentManager;
    private ViewPager pager;
    private Menu toolbarMenu;


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


    public boolean onCreateOptionsMenu(Menu menu) {

        // TODO Auto-generated method stub
        getMenuInflater().inflate(R.menu.menu, menu);
        //SubMenu subMenu = (SubMenu) menu.getItem(1).getSubMenu();
        //   subMenu.getItem(1).setVisible(false);
        toolbarMenu = menu;
        if (activityWebView.cachedWebView == null) {
            toolbarMenu.findItem(R.id.play_pause).setVisible(false);
            toolbarMenu.findItem(R.id.stop).setVisible(false);

        } else {
            toolbarMenu.findItem(R.id.play_pause).setVisible(true);
            toolbarMenu.findItem(R.id.stop).setVisible(true);
            if (activityWebView.isPlaying) {
                toolbarMenu.findItem(R.id.play_pause).setIcon(R.drawable.pause);
            } else {
                toolbarMenu.findItem(R.id.play_pause).setIcon(R.drawable.play);
            }

        }

        toolbarMenu.findItem(R.id.play).setVisible(false);

        supportInvalidateOptionsMenu();
        invalidateOptionsMenu();


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.play_pause:
                if (activityWebView.isPlaying) {
                    activityWebView.stopWebViewAudio();
                    item.setIcon(R.drawable.play);
                } else {
                    activityWebView.playWebViewAudio();
                    item.setIcon(R.drawable.pause);
                }
                return true;

            case R.id.stop:
                activityWebView.RestartWebViewAudio();
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }


    }


    private void setupViewPager(ViewPager viewPager) {

        fragmentManager = new mFragmentManager(getSupportFragmentManager());

        fragmentManager.AddFragment(new FragmentMenuPrincipal());
        fragmentManager.AddFragment(new FragmentMenuSecundario());
        viewPager.setAdapter(fragmentManager);
        viewPager.addOnPageChangeListener(this);


    }

    @Override
    protected void onResume() {
        supportInvalidateOptionsMenu();
        invalidateOptionsMenu();
        super.onResume();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        ChangePagerDots(position);
    }


    private void ChangePagerDots(int position){

        for(int i = 0; i<dots.size(); i++){
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
