package com.lbbento.androidarchitecture.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lbbento.androidarchitecture.R;

/**
 * Created by lbbento on 20/06/2016.
 * Fragment responsible of showing home fragment.
 */

public class HomeFragment extends Fragment{

    private static final String PARAM_TITLE = "title";

    public HomeFragment() {
        // public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment f = new HomeFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.menu_action_home));


    }

}
