package com.flaviotps.verbodavidacampogrande;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by flaviotps on 14/02/18.
 */

public class FragmentMenuSecundario extends Fragment {


    private View mFragmentView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_menu_2, container, false);

        return mFragmentView;
    }
}
