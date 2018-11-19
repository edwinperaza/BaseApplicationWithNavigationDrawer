package com.moriahdp.baseapplication.games;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moriahdp.baseapplication.R;
import com.moriahdp.baseapplication.baseclasses.BaseFragment;

public class GamesFragment extends BaseFragment {

    private View mRoot;

    public static GamesFragment newInstance() {
        Bundle args = new Bundle();
        GamesFragment fragment = new GamesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View onCreateEventView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_games, container, false);
        //TODO init Model View Presenter
        return mRoot;
    }

}
