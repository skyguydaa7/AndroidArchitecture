package com.lbbento.androidarchitecture.data.source;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.lbbento.androidarchitecture.data.Discussion;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by lbbento on 21/06/2016.
 */

public class DiscussionsRepository implements DiscussionsDataSource {

    private static DiscussionsRepository INSTANCE = null;

    private final DiscussionsDataSource mDiscussionsRemoteDataSource;

    private final DiscussionsDataSource mDiscussionsLocalDataSource;

    // Prevent direct instantiation.
    private DiscussionsRepository(@NonNull DiscussionsDataSource discussionsRemoteDataSource,
                            @NonNull DiscussionsDataSource discussionsLocalDataSource) {
        mDiscussionsRemoteDataSource = checkNotNull(discussionsRemoteDataSource);
        mDiscussionsLocalDataSource = checkNotNull(discussionsLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param discussionsRemoteDataSource the backend data source
     * @param discussionsLocalDataSource  the device storage data source
     * @return the {@link DiscussionsRepository} instance
     */
    public static DiscussionsRepository getInstance(DiscussionsDataSource discussionsRemoteDataSource,
                                              DiscussionsDataSource discussionsLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DiscussionsRepository(discussionsRemoteDataSource, discussionsLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(DiscussionsDataSource, DiscussionsDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Gets discussions from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     * <p/>
     * Note: {@link GetDiscussionsCallback#onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    @Override
    public void getDiscussions(@NonNull final GetDiscussionsCallback callback) {
        checkNotNull(callback);

        // Load from server
        mDiscussionsRemoteDataSource.getDiscussions(new GetDiscussionsCallback() {
            @Override
            public void onDiscussionsLoaded(List<Discussion> discussions) {
                if (discussions != null)
                    refreshLocalDataSource(discussions);
                callback.onDiscussionsLoaded(null);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    /**
     * Gets discussions from local data source (sqlite) unless the table is new or empty. In that case it
     * uses the network data source. This is done to simplify the sample.
     * <p/>
     * Note: {@link GetDiscussionCallback#onDataNotAvailable()} is fired if both data sources fail to
     * get the data.
     */
    @Override
    public void getDiscussion(@NonNull final String discussionId, @NonNull final GetDiscussionCallback callback) {
        checkNotNull(discussionId);
        checkNotNull(callback);

        // Load from server
        mDiscussionsRemoteDataSource.getDiscussion(discussionId, new GetDiscussionCallback() {
            @Override
            public void onDiscussionLoaded(Discussion discussion) {
                callback.onDiscussionLoaded(discussion);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void saveDiscussion(@NonNull Discussion discussion) {
        checkNotNull(discussion);
        mDiscussionsRemoteDataSource.saveDiscussion(discussion);
        mDiscussionsLocalDataSource.saveDiscussion(discussion);
    }


    private void refreshLocalDataSource(List<Discussion> discussions) {
        for (Discussion discussion : discussions) {
            mDiscussionsLocalDataSource.saveDiscussion(discussion);
        }
    }

    public interface LoadDataCallback {
        void onDataLoaded(Cursor data);

        void onDataEmpty();

        void onDataNotAvailable();

        void onDataReset();
    }
}
