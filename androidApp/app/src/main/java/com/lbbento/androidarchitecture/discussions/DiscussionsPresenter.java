package com.lbbento.androidarchitecture.discussions;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.google.common.base.Preconditions;
import com.lbbento.androidarchitecture.data.Discussion;
import com.lbbento.androidarchitecture.data.source.DiscussionsDataSource;
import com.lbbento.androidarchitecture.data.source.DiscussionsRepository;
import com.lbbento.androidarchitecture.data.source.LoaderProvider;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

/**
 * Created by lbbento on 20/06/2016.
 * Responsible for managing all data before showing on the screen.
 */

public class DiscussionsPresenter implements DiscussionsContract.Presenter, DiscussionsRepository.LoadDataCallback, DiscussionsDataSource.GetDiscussionsCallback, LoaderManager.LoaderCallbacks<Cursor> {

    public final static int DISCUSSIONS_LOADER = 1;

    private final DiscussionsContract.View mDiscussionsView;

    @NonNull
    private final DiscussionsRepository mDiscussionsRepository;

    @NonNull
    private final LoaderManager mLoaderManager;

    @NonNull
    private final LoaderProvider mLoaderProvider;


    public DiscussionsPresenter(@NonNull LoaderProvider loaderProvider, @NonNull LoaderManager loaderManager, @NonNull DiscussionsRepository discussionsRepository, @NonNull DiscussionsContract.View discussionsView) {
        mLoaderProvider = checkNotNull(loaderProvider, "loaderProvider provider cannot be null");
        mLoaderManager = checkNotNull(loaderManager, "loaderManager provider cannot be null");
        mDiscussionsRepository = checkNotNull(discussionsRepository, "discussionRepo provider cannot be null");
        mDiscussionsView = checkNotNull(discussionsView, "discussionView cannot be null!");
        mDiscussionsView.setPresenter(this);
    }


    @Override
    public void start() {

        loadDiscussions();
    }

    /**
     * Load Discussions - Get fresh data from API
     */
    @Override
    public void loadDiscussions() {
        mDiscussionsView.setLoadingIndicator(true);
        mDiscussionsRepository.getDiscussions(this);
    }

    @Override
    public void onDataLoaded(Cursor data) {
        mDiscussionsView.setLoadingIndicator(false);
        // Show the list of discussions
        mDiscussionsView.showDiscussions(data);
    }

    @Override
    public void onDataEmpty() {
        mDiscussionsView.setLoadingIndicator(false);
        // TODO - Show a message indicating there are no discussions
    }

    @Override
    public void onDiscussionsLoaded(List<Discussion> discussions) {
        if (mLoaderManager.getLoader(DISCUSSIONS_LOADER) == null) {
            mLoaderManager.initLoader(DISCUSSIONS_LOADER, null, this);
        } else {
            mLoaderManager.restartLoader(DISCUSSIONS_LOADER, null,  this);
        }
    }

    @Override
    public void onDataReset() {
        mDiscussionsView.showDiscussions(null);
    }

    @Override
    public void onDataNotAvailable() {
        mDiscussionsView.setLoadingIndicator(false);
        //TODO - Show message that it wasnt possible to load the discussions
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return mLoaderProvider.createDiscussionsLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            if (data.moveToLast()) {
                onDataLoaded(data);
            } else {
                onDataEmpty();
            }
        } else {
            onDataNotAvailable();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        onDataReset();
    }



    @Override
    public void openDiscussionDetails(@NonNull Discussion requestedDiscussion) {
        //TODO open discussion desc.

    }

}
