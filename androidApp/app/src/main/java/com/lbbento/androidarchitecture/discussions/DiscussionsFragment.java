package com.lbbento.androidarchitecture.discussions;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lbbento.androidarchitecture.R;
import com.lbbento.androidarchitecture.data.Discussion;
import com.lbbento.androidarchitecture.data.Injection;
import com.lbbento.androidarchitecture.data.source.DiscussionsRepository;
import com.lbbento.androidarchitecture.data.source.LoaderProvider;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by lbbento on 20/06/2016.
 * Fragment responsible of showing list of discussions.
 */

public class DiscussionsFragment extends Fragment implements DiscussionsContract.View {


    //Presenter
    private DiscussionsContract.Presenter mPresenter;
    private DiscussionsAdapter mDiscussionsAdapter;

    //Listener - RecyclerView
    /**
     * Listener for clicks on discussions in the RecyclerView.
     */
    DiscussionsContract.DiscussionItemListener mItemListener = new DiscussionsContract.DiscussionItemListener() {

        @Override
        public void onDiscussionClick(Discussion discussion) {
            //TODO - open discussion desc
        }
    };

    public DiscussionsFragment() {
        // public constructor
    }

    public static DiscussionsFragment newInstance() {
        DiscussionsFragment f = new DiscussionsFragment();

        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_discussion, container, false);

        //setup adapter - obs cursor = null because we don't have one yet
        mDiscussionsAdapter = new DiscussionsAdapter(getContext(), null ,mItemListener);

        //setup recyclerView
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.discussionsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mDiscussionsAdapter);


        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.menu_action_discussions));


        //Presenter
        LoaderProvider loaderProvider = new LoaderProvider(getContext());

        mPresenter = new DiscussionsPresenter(
                loaderProvider,
                getLoaderManager(),
                Injection.provideDiscussionsRepository(getContext()),
                this
        );


    }


    @Override
    public void setLoadingIndicator(boolean active) {
        if (getView() == null) {
            return;
        }

        //TODO - LOADING INDICATOR

    }

    @Override
    public void showDiscussions(Cursor discussions) {
        mDiscussionsAdapter.swapCursor(discussions);
    }


    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(@NonNull DiscussionsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter); //Guava :D
    }
}
