package com.flaviotps.verbodavidacampogrande;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by flaviotps on 14/02/18.
 */

public class FragmentMenuPrincipal extends Fragment implements CardView.OnClickListener {

    private View mFragmentView;

    private CardView mNoticiasButton, mVerdoDaVidaButton, mEventosButton, mMensagemButton, mAudioButton, mVideoButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_menu_1, container, false);

        mNoticiasButton = mFragmentView.findViewById(R.id.noticias);
        mVerdoDaVidaButton = mFragmentView.findViewById(R.id.verbo);
        mEventosButton = mFragmentView.findViewById(R.id.eventos);
        mMensagemButton = mFragmentView.findViewById(R.id.mensagem);
        mAudioButton = mFragmentView.findViewById(R.id.audio);
        mVideoButton = mFragmentView.findViewById(R.id.videos);


        mNoticiasButton.setOnClickListener(this);
        mVerdoDaVidaButton.setOnClickListener(this);
        mEventosButton.setOnClickListener(this);
        mMensagemButton.setOnClickListener(this);
        mAudioButton.setOnClickListener(this);
        mVideoButton.setOnClickListener(this);

        return mFragmentView;
    }

    private void ChangeActivity(int TARGET) {
        Bundle mBundle = new Bundle();
        mBundle.putInt("ACTIVITY_ID", TARGET);
        Intent mIntent = new Intent(getActivity(), activityWebView.class);
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.noticias:
                ChangeActivity(activityWebView.ACTIVITY_NOTICIAS);
                break;

            case R.id.verbo:
                ChangeActivity(activityWebView.ACTIVITY_VERBO);
                break;

            case R.id.eventos:
                ChangeActivity(activityWebView.ACTIVITY_EVENTOS);
                break;

            case R.id.mensagem:
                ChangeActivity(activityWebView.ACTIVITY_MENSAGENS);
                break;


            case R.id.audio:
                ChangeActivity(activityWebView.ACTIVITY_AUDIOS);
                break;


            case R.id.videos:
                ChangeActivity(activityWebView.ACTIVITY_VIDEOS);
                break;
        }

    }
}
