package com.flaviotps.verbodavidacampogrande;



        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentStatePagerAdapter;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by flaviotps on 14/02/18.
 */

public class mFragmentManager extends FragmentStatePagerAdapter {

    List<Fragment> mFragments = new ArrayList<Fragment>();

    public mFragmentManager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void AddFragment(Fragment fragment) {
        mFragments.add(fragment);
    }
}