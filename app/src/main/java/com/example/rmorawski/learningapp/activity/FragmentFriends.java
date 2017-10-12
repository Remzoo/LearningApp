package com.example.rmorawski.learningapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rmorawski.learningapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by r.morawski on 10.10.2017.
 */

public class FragmentFriends extends Fragment {

    private Unbinder butterKnife;

    public FragmentFriends() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        //Register butterknife
        butterKnife = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        //Unregister butterknife
        butterKnife.unbind();
    }
}
