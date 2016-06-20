package com.lbbento.androidarchitecture.discussions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lbbento.androidarchitecture.R;
import com.lbbento.androidarchitecture.data.Discussion;

import java.util.List;

/**
 * Created by lbbento on 20/06/2016.
 * Fragment responsible of showing list of discussions.
 */

public class DiscussionsFragment extends Fragment implements DiscussionsContract.View {

    private static final String PARAM_TITLE = "title";

    public DiscussionsFragment() {
        // public constructor
    }

    public static DiscussionsFragment newInstance(String title) {
        DiscussionsFragment f = new DiscussionsFragment();

        Bundle args = new Bundle();
        args.putSerializable(PARAM_TITLE, title);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_discussion, container, false);

        //setup adapter
        DiscussionsAdapter discussionsAdapter = new DiscussionsAdapter()

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Title
        Bundle args = getArguments();
        if (args != null) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle((String)args.getSerializable(PARAM_TITLE));
        }

    }


    @Override
    public void setLoadingIndicator(boolean active) {
        if (getView() == null) {
            return;
        }

        //TODO - LOADING INDICATOR

    }

    @Override
    public void showDiscussions(List<Discussion> discussions) {

    }

    @Override
    public void showLoadingDiscussionsError() {

    }

    @Override
    public void showNoDiscussions() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(DiscussionsContract.Presenter presenter) {

    }
}
