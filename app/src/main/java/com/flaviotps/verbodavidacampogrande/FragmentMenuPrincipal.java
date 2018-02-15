package com.flaviotps.verbodavidacampogrande;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flaviotps on 14/02/18.
 */

public class FragmentMenuPrincipal  extends Fragment implements View.OnClickListener{

    private View mFragmentView;
    private List<View> buttons = new ArrayList<View>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_menu_1, container, false);



        for(int i=0;i<buttons.size();i++){
            buttons.get(i).setOnClickListener(this);
        }
        return mFragmentView;
    }






    private void ChangeViewColor(View v,int res){
        v.setBackgroundColor(getResources().getColor(res));
    }





    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.audio_video:

                break;


            case R.id.eventos:

                break;


            case R.id.noticias:

                break;


            case R.id.mensagem:

                break;


            case R.id.doe:

                break;

        }

    }
}
